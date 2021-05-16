package org.sopt.androidseminar.userinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.androidseminar.R
import org.sopt.androidseminar.databinding.ActivityUserInfoBinding
import org.sopt.androidseminar.home.repository.RepositoryFragment

class UserInfoActivity : AppCompatActivity() {
    lateinit var binding : ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val followingListFragment = RepositoryFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.user_info_fragment, followingListFragment)
        transaction.commit()
    }
}