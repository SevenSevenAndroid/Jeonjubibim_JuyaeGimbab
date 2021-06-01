package org.sopt.androidseminar.home.viewmodel

import android.app.UiAutomation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.sopt.androidseminar.api.github.RetrofitService
import org.sopt.androidseminar.home.following.ResponseGithubFollowingItem
import org.sopt.androidseminar.utils.UiState

class FollowingListViewModel: ViewModel(){
    private val _followingList = MutableLiveData<UiState<List<ResponseGithubFollowingItem>>>()
    val followingList : LiveData<UiState<List<ResponseGithubFollowingItem>>>
    get() = _followingList

    @SuppressWarnings("CheckResult")
    fun showFollowing(username : String){
        _followingList.postValue(UiState.loading(null))

        RetrofitService.githubApi.getFollowing(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _followingList.postValue(UiState.success(it))
            },{
                _followingList.postValue(UiState.error(null, it.message))
                it.printStackTrace()
            })

    }
}