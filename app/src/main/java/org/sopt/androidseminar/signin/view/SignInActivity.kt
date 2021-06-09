package org.sopt.androidseminar.signin.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailabilityLight
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.R
import org.sopt.androidseminar.api.sopt.ServiceCreator
import org.sopt.androidseminar.databinding.ActivitySignInBinding
import org.sopt.androidseminar.home.view.HomeActivity
import org.sopt.androidseminar.signin.dto.RequestLoginData
import org.sopt.androidseminar.signin.dto.ResponseLoginData
import org.sopt.androidseminar.signin.dto.SoptUserAuthStorage
import org.sopt.androidseminar.signin.dto.SoptUserInfo
import org.sopt.androidseminar.signup.SignUpActivity
import org.sopt.androidseminar.utils.enqueueUtil
import org.sopt.androidseminar.utils.showToast
import retrofit2.Call

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    private var callbackManager: CallbackManager? = null
    private lateinit var googleSignInClient: GoogleSignInClient
    var auth : FirebaseAuth ?= null

    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        binding.editextSigninId.setText(it.data?.getStringExtra("id"))
        binding.editextSigninPwd.setText(it.data?.getStringExtra("password"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        callbackManager = CallbackManager.Factory.create()
        auth = FirebaseAuth.getInstance()

        setContentView(binding.root)
        checkFacebookSDK()
        showSignup()
        setButtonEvent()
        searchUserAuthStorage()
        facebookLogin()
        startGoogleLogin()
        checkgooglePlayServices()
    }

    private fun checkFacebookSDK(){
        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(this)

        FacebookSdk.setApplicationId(getString(org.sopt.androidseminar.R.string.facebook_app_id))
        FacebookSdk.sdkInitialize(this)

        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true)
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
        }
    }
    fun startGoogleLogin(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(org.sopt.androidseminar.R.string.client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)
        binding.googleButton.setOnClickListener {
            signIn()
        }
    }
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_REQUEST_CODE)
    }


    private fun checkgooglePlayServices() : Boolean{
        val status =  GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(this)
        return if (status != ConnectionResult.SUCCESS) {
            Log.e("error","error")
            false
        }  else {
            Log.e("updated", "updated")
            true
        }
    }

    fun showSignup(){
        binding.textviewSignin.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity:: class.java)
            startActivityForResult(intent, SIGN_UP_RESULT_CODE)

        }
    }

    private fun searchUserAuthStorage() {
        with(SoptUserAuthStorage.getInstance(this)) {
            if (hasUserData()) {
                requestLogin(getUserData().let { RequestLoginData(it.id, it.password) })
            }
        }
    }

    private fun setButtonEvent() {
        with(binding) {
            btnLogin.setOnClickListener { loginButtonClickEvent() }
            textviewSignin.setOnClickListener { startSignUpForResult() }
        }
    }

    private fun loginButtonClickEvent() {
        val requestLoginData = RequestLoginData(
            id = binding.editextSigninId.text.toString(),
            password = binding.editextSigninPwd.text.toString()
        )
        requestLogin(requestLoginData)
    }

    private fun requestLogin(requestLoginData: RequestLoginData) {
        val call: Call<ResponseLoginData> = ServiceCreator.soptService
            .postLogin(requestLoginData)
        call.enqueueUtil(
            onSuccess = { response ->
                val data = response.data
                showToast(data?.user_nickname.toString())
                with(SoptUserAuthStorage.getInstance(this)) {
                    saveUserData(requestLoginData.let { SoptUserInfo(it.id, it.password) })
                }
                startHomeActivity()
            }
        )

    }
    private fun startHomeActivity() {
        startActivity(
            Intent(this, HomeActivity::class.java)
        )
        finish()
    }
    private fun startSignUpForResult() {
        signUpActivityLauncher.launch(
            Intent(this, SignUpActivity::class.java)
        )
    }

    private fun facebookLogin() {
        LoginManager.getInstance()
            .logInWithReadPermissions(this, listOf("email", "public_profile"))

        LoginManager.getInstance()
            .registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    if (result?.accessToken != null) {
                        // facebook 계정 정보를 firebase 서버에게 전달(로그인)
                        val accessToken = result.accessToken
                        firebaseAuthWithFacebook(result?.accessToken)
                    } else {
                        Log.d("Facebook", "Fail Facebook Login")
                    }
                }

                override fun onCancel() {
                    //취소가 된 경우 할일
                }

                override fun onError(error: FacebookException?) {
                    //에러가 난 경우 할일
                }
            })
    }

    private fun firebaseAuthWithFacebook(accessToken: AccessToken?) {
        // AccessToken 으로 Facebook 인증
        val credential = FacebookAuthProvider.getCredential(accessToken?.token!!)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "로그인 성공")
                    val user = auth!!.currentUser
                    loginSuccess()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }
    private fun loginSuccess(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }


    companion object {
        private const val SIGN_UP_RESULT_CODE = 100
        private const val GOOGLE_REQUEST_CODE = 99
    }

}

