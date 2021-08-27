package org.sopt.androidseminar.utils

import android.app.Activity
import org.sopt.androidseminar.R

fun Activity.slideLeft() {
    overridePendingTransition(R.anim.slide_left,R.anim.slide_left_exit)
}

fun Activity.slideRight() {
    overridePendingTransition(R.anim.slide_right,R.anim.slide_right_exit)
}

fun Activity.slideUp() {
    overridePendingTransition(R.anim.slide_up,R.anim.slide_up_exit)
}

fun Activity.slideDown() {
    overridePendingTransition(R.anim.slide_down,R.anim.slide_down_exit)
}

