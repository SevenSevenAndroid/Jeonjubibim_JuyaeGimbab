package org.sopt.androidseminar.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.sopt.androidseminar.api.github.RetrofitService
import org.sopt.androidseminar.home.followers.ResponseGithubFollowersItem
import org.sopt.androidseminar.utils.UiState

class FollowerListViewModel : ViewModel() {

    private val _followerlist = MutableLiveData<UiState<List<ResponseGithubFollowersItem>>>()
    val followerlist : LiveData<UiState<List<ResponseGithubFollowersItem>>>
        get() = _followerlist

    @SuppressWarnings("CheckResult")
    fun showFollowers(username : String) {
        _followerlist.postValue(UiState.loading(null))

        RetrofitService.githubApi.getFollowers(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _followerlist.postValue(UiState.success(it))

            },{
                _followerlist.postValue(UiState.error(null,it.message))
                it.printStackTrace()
            })
    }
}