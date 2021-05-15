package org.sopt.androidseminar.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.androidseminar.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActivityResult()
    }

    private fun initActivityResult(){
        binding.buttonSignup.setOnClickListener(){
            val signupName = binding.editextSignupName.text
            val signupID = binding.editextSignupId.text
            val signupPwd = binding.editextSignupPwd.text

            if(signupID.isNullOrBlank() || signupName.isNullOrBlank() || signupPwd.isNullOrBlank()){
                Toast.makeText(this@SignUpActivity, "빈칸이 있는지 확인해주세요!", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent()
                intent.putExtra("id",signupID.toString())
                intent.putExtra("pwd",signupPwd.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

}