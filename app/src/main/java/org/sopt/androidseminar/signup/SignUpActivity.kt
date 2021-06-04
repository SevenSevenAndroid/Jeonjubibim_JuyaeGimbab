package org.sopt.androidseminar.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import okhttp3.ResponseBody
import org.json.JSONObject
import org.sopt.androidseminar.api.sopt.ServiceCreator
import org.sopt.androidseminar.databinding.ActivitySignUpBinding
import org.sopt.androidseminar.signin.view.SignInActivity
import org.sopt.androidseminar.utils.slideDown
import org.sopt.androidseminar.utils.slideLeft
import org.sopt.androidseminar.utils.slideRight
import org.sopt.androidseminar.utils.slideUp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActivityResult()
        slideLeft()
        slideRight()
        slideUp()
        slideDown()
    }

    private fun initActivityResult() {

        binding.buttonSignup.setOnClickListener() {

            val requestSignupData = RequestSignupData(
                email = binding.editextSignupName.text.toString(),
                password = binding.editextSignupPassword.text.toString(),
                sex = binding.editextSignupSex.text.toString(),
                nickname = binding.editextSignupNickname.text.toString(),
                phone = binding.editextSignupPhone.text.toString(),
                birth = binding.editextSignupBirth.text.toString()

            )
            val call: Call<ResponseSignupData> = ServiceCreator.soptService
                .postSingup(requestSignupData)

            call.enqueue(object : Callback<ResponseSignupData> {
                override fun onResponse(
                    call: Call<ResponseSignupData>,
                    response: Response<ResponseSignupData>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@SignUpActivity, "회원가입 성공!!", Toast.LENGTH_SHORT).show()
                        Log.e("success", "회원가입 성공")
                        successSignupRetrofit()

                    } else {
                        showError(response.errorBody())
                    }
                }

                override fun onFailure(call: Call<ResponseSignupData>, t: Throwable) {
                    Log.d("NetworkTest", "에러메세지:$t")
                }
            })
        }
    }

    fun successSignupRetrofit() {
        val signupName = binding.editextSignupName.text
        val signupPwd = binding.editextSignupPassword.text
        val signupSex = binding.editextSignupSex.text
        val signupNickname = binding.editextSignupNickname.text
        val signupPhone = binding.editextSignupPhone.text
        val signupBirth = binding.editextSignupBirth.text

        if (signupSex.isNullOrBlank() || signupName.isNullOrBlank() || signupPwd.isNullOrBlank()
            || signupNickname.isNullOrBlank() || signupPhone.isNullOrBlank() || signupBirth.isNullOrBlank()
        ) {
            Toast.makeText(this@SignUpActivity, "빈칸이 있는지 확인해주세요!", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent()
            intent.putExtra("id", signupName.toString())
            intent.putExtra("pwd", signupPwd.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()

        }

    }

    fun showError(error: ResponseBody?) {
        val e = error ?: return
        val ob = JSONObject(e.string())
        Log.e("error", ob.getString("message"))
    }
}