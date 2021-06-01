package org.sopt.androidseminar.base

import android.content.Context
import androidx.annotation.StringRes

interface BaseView {
    fun getContext() : Context
    fun showError(error : String)
    fun showError(@StringRes errorResId : Int){
        this.showError(getContext().getString(errorResId))
    }
}