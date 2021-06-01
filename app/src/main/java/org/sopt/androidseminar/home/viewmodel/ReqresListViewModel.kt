package org.sopt.androidseminar.home.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.sopt.androidseminar.api.github.RetrofitService
import org.sopt.androidseminar.api.reqres.ReqresCreator
import org.sopt.androidseminar.api.reqres.dto.BaseUser
import org.sopt.androidseminar.api.reqres.dto.ResponseReqresUsers
import retrofit2.Retrofit

class ReqresListViewModel : ViewModel() {
    private val _reqreslist = MutableLiveData<List<ResponseReqresUsers>>()
    val reqreslist :LiveData<List<ResponseReqresUsers>>
        get() = _reqreslist


    @SuppressLint("CheckResult")
    fun getFriendsList(page : Int) {
        ReqresCreator.reqresApi.getUsersList(2)
            .map {
                it.data?.map {
                    ResponseReqresUsers(
                        it.firstName,
                        it.email,
                        it.avatar
                    )
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       _reqreslist.postValue(it)
            },{
                it.printStackTrace()
            })
    }

}

