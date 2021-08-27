package org.sopt.androidseminar.home.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import org.sopt.androidseminar.api.reqres.ReqresCreator
import org.sopt.androidseminar.api.reqres.dto.RequestReqresUsers
import org.sopt.androidseminar.api.reqres.dto.ResponseReqresUsers
import org.sopt.androidseminar.userinfo.UserInfoActivity
import org.sopt.androidseminar.databinding.ActivityHomeBinding
import org.sopt.androidseminar.home.followers.FollowerListAdapter
import org.sopt.androidseminar.home.followers.ResponseGithubFollowersItem
import org.sopt.androidseminar.home.following.FollowingListAdapter
import org.sopt.androidseminar.home.viewmodel.FollowerListViewModel
import org.sopt.androidseminar.home.viewmodel.FollowingListViewModel
import org.sopt.androidseminar.home.viewmodel.ReqresListViewModel
import org.sopt.androidseminar.mypage.MyPageActivity
import org.sopt.androidseminar.utils.slideLeft
import org.sopt.androidseminar.utils.slideRight
import org.sopt.androidseminar.utils.slideUp
import retrofit2.Call
import retrofit2.Callback

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    lateinit var reqresListAdapter: ReqresListAdapter
    lateinit var followerListAdapter: FollowerListAdapter
    lateinit var followingListAdapter : FollowingListAdapter
    private val viewModel by viewModels<ReqresListViewModel>()
    private val followerViewModel by viewModels<FollowerListViewModel>()
    private val followingViewModel by viewModels<FollowingListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getFriendsList(2)
        followerViewModel.showFollowers("jooyae")
        followingViewModel.showFollowing("jooyae")

        initButtonClick()
        showReqresList()
        showFollowerList()
        showFollowingList()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        slideUp()
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

    fun showReqresList(){
        reqresListAdapter = ReqresListAdapter()
        binding.recyclerviewReqresUsers.adapter = reqresListAdapter

        viewModel.reqreslist.observe(this){
            viewModel.reqreslist.value?.forEach {
                reqresListAdapter.reqresList.add(it)
                reqresListAdapter.notifyDataSetChanged()
            }
        }
    }

    fun showFollowerList(){
        followerListAdapter = FollowerListAdapter()
        binding.recyclerviewGithubFollowers.adapter = followerListAdapter

        followerViewModel.followerlist.observe(this){
            followerViewModel.followerlist.value?.data?.forEach{
                followerListAdapter.followerList.add(it)
                followerListAdapter.notifyDataSetChanged()

            }
        }
    }

    fun showFollowingList(){
        followingListAdapter = FollowingListAdapter()
        binding.recyclerviewGithubFollowing.adapter = followingListAdapter

        followingViewModel.followingList.observe(this) {
            followingViewModel.followingList.value?.data?.forEach {
                followingListAdapter.followingList.add(it)
                followingListAdapter.notifyDataSetChanged()
            }
        }
    }
}