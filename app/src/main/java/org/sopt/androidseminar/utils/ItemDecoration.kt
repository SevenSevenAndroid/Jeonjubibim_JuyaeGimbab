package org.sopt.androidseminar.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration(private val divHeight: Int?, private val divWidth: Int?): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (divHeight != null) {
            outRect.top = divHeight
            outRect.bottom = divHeight
        }

        if (divWidth != null) {
            outRect.left = divWidth
            outRect.right = divWidth
        }
    }
}