package org.sopt.androidseminar.home.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.sopt.androidseminar.api.RetrofitService
import org.sopt.androidseminar.home.dto.GithubRepo
import org.sopt.androidseminar.home.dto.RepositoryResponseModelItem
import org.sopt.androidseminar.home.dto.ResponseGithubRepository
import org.sopt.androidseminar.home.view.RepositoryFragment

class RepositoryViewModel : ViewModel() {

    private val _repositories = MutableLiveData<ResponseGithubRepository>()
    val repositories: LiveData<ResponseGithubRepository>
        get() = _repositories

//    fun changeRepositories(list: List<RepositoryResponseModelItem>) {
//        _repositories.value = list
//    }

    fun removeRepository(position: Int) {
        _repositories.value?.removeAt(position)
    }


    @SuppressLint("CheckResult")
    fun searchRepository(name : String) {
        RetrofitService.githubApi.getRepos(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                _repositories.postValue(list)
            }, {
                it.printStackTrace()
            })
    }

    fun swipeItems(fromPosition: Int, toPosition: Int) {
        val datas = _repositories.value
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                datas?.set(i, datas.set(i+1, datas[i]))
            }
        } else {
            for (i in fromPosition until toPosition + 1) {
                datas?.set(i, datas.set(i-1, datas[i]))
            }
        }
    }

}