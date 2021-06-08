package org.sopt.androidseminar.signin.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailabilityLight
import com.google.firebase.auth.FacebookAuthProvider
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

        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(this)

        FacebookSdk.setApplicationId(getString(org.sopt.androidseminar.R.string.facebook_app_id))
        FacebookSdk.sdkInitialize(this)

        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true)
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
        }
        setContentView(binding.root)
        showSignup()
        setButtonEvent()
        searchUserAuthStorage()
        facebookLogin()
        checkgooglePlayServices()
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
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private const val SIGN_UP_RESULT_CODE = 100
    }

}

