package org.sopt.androidseminar.signin.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import okhttp3.ResponseBody
import org.json.JSONObject
import org.sopt.androidseminar.signin.dto.ResponseLoginData
import org.sopt.androidseminar.api.sopt.ServiceCreator
import org.sopt.androidseminar.databinding.ActivitySignInBinding
import org.sopt.androidseminar.signin.dto.RequestLoginData
import org.sopt.androidseminar.home.view.HomeActivity
import org.sopt.androidseminar.signin.dto.SoptUserAuthStorage
import org.sopt.androidseminar.signin.dto.SoptUserInfo
import org.sopt.androidseminar.signup.SignUpActivity
import org.sopt.androidseminar.utils.enqueueUtil
import org.sopt.androidseminar.utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding

    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        binding.editextSigninId.setText(it.data?.getStringExtra("id"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtonClickEvent()
        showSignup()
        searchUserAuthStorage()
    }

    private fun initButtonClickEvent() {

        binding.btnLogin.setOnClickListener {
            val requestLoginData = RequestLoginData(
                id = binding.editextSigninId.text.toString(),
                password = binding.editextSigninPwd.text.toString()
            )

            val call: Call<ResponseLoginData> = ServiceCreator.soptService
                .postLogin(requestLoginData)

            call.enqueue(object : Callback<ResponseLoginData> {
                override fun onResponse(
                    call: Call<ResponseLoginData>,
                    response: Response<ResponseLoginData>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        Log.e("success", "로그인 성공")
                        Toast.makeText(this@SignInActivity, data?.user_nickname, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                        showSignup()

                    } else {
                        showError(response.errorBody())
                    }
                }
                override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                    Log.d("NetworkTest", "error:$t")
                }
            })
        }
    }

    fun showError(error: ResponseBody?) {
        val e = error ?: return
        val ob = JSONObject(e.string())
        Log.e("error", ob.getString("message"))
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
        // 서버로 보낼 로그인 데이터 생성
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                SIGN_UP_RESULT_CODE -> {
                    binding.editextSigninId.setText(data!!.getStringExtra("id"))
                    binding.editextSigninPwd.setText(data!!.getStringExtra("pwd"))

                }
            }
        }
    }

    companion object {
        private const val SIGN_UP_RESULT_CODE = 100
    }

}

