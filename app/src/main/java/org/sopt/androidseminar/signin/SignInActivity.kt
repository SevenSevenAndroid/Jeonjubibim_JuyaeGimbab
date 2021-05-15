package org.sopt.androidseminar.signin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.sopt.androidseminar.signup.SignUpActivity
import org.sopt.androidseminar.databinding.ActivitySignInBinding
import org.sopt.androidseminar.home.view.HomeActivity

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtonClickEvent()

        Log.d("onCreate","onCreate 실행")
    }

    private fun initButtonClickEvent() {
        binding.btnLogin.setOnClickListener {
            val userID = binding.editextSigninId.text
            val userPWD = binding.editextSigninPwd.text
            if (userID.isNullOrBlank() || userPWD.isNullOrBlank()) {
                Toast.makeText(
                        this@SignInActivity,
                        "아이디/비밀번호를 확인해주세요!",
                        Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                        this@SignInActivity,
                        "로그인 성공",
                        Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.textviewSignin.setOnClickListener{
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivityForResult(intent, SIGN_UP_RESULT_CODE)
        }
    }

    override fun onActivityResult(requestCode : Int, resultCode: Int,data:Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                100-> {
                    binding.editextSigninId.setText(data!!.getStringExtra("id"))
                    binding.editextSigninPwd.setText(data!!.getStringExtra("pwd"))

                }
            }
        }
    }
    companion object {
        private const val SIGN_UP_RESULT_CODE = 100
    }


    override fun onStart() {
        super.onStart()
        Log.d("onResume","onResume 살행")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume","onResume 살행")
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause","onPause 실행")
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop","onStop 실행")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "onDestroy 실행")
    }

}