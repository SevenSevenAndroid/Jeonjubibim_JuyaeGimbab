package org.sopt.androidseminar.utils

import androidx.recyclerview.widget.RecyclerView

object ItemDecorationRemover {
    fun <T:RecyclerView> T.removeItemDecorations(){
        while(itemDecorationCount >0)
            removeItemDecorationAt(0)
    }
}