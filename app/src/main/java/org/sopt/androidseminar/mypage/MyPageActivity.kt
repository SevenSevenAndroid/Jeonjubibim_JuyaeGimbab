package org.sopt.androidseminar.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator.*
import org.sopt.androidseminar.R
import org.sopt.androidseminar.databinding.ActivityMyPageBinding
import org.sopt.androidseminar.uploadImage
import com.google.android.material.tabs.TabLayoutMediator as TabLayoutMediator1

class MyPageActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyPageBinding
    private var fragment: ImageSlideFragment? = null
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageSlideFragment = fragment
        val transaction = supportFragmentManager.beginTransaction()
        if (imageSlideFragment != null) {
            transaction.add(R.id.fragment_imageslide, imageSlideFragment)
        }
        transaction.commit()

        viewPager2 = binding.viewpagerMypage
        val tabLayout = binding.tablayoutViewpagerIndicator
        val pagerAdapter = ScreenSlideMyPageAdapter(this)
        binding.viewpagerMypage.adapter = pagerAdapter

        com.google.android.material.tabs.TabLayoutMediator(tabLayout, viewPager2) { tabLayout, position ->
            viewPager2.setCurrentItem(tabLayout.position)
        }.attach()




    }

    private inner class ScreenSlideMyPageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ImageSlideFragment(R.drawable.ic_home_img_step3)
                1 -> ImageSlideFragment(R.drawable.ic_home_img_step2)
                else -> ImageSlideFragment(R.drawable.ic_home_img_step4)
            }
        }
    }


}