package org.sopt.androidseminar.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.sopt.androidseminar.userinfo.UserInfoActivity
import org.sopt.androidseminar.databinding.ActivityHomeBinding
import org.sopt.androidseminar.mypage.MyPageActivity

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtonClick()
    }

    fun initButtonClick(){
        binding.buttonHomeMore.setOnClickListener {
            val intent = Intent(this@HomeActivity , UserInfoActivity::class.java)
            intent.putExtra("name", binding.textviewProfileId.text.toString())
            startActivity(intent)
        }
        binding.textviewHomeGallery.setOnClickListener{
            val intent = Intent(this@HomeActivity, MyPageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}