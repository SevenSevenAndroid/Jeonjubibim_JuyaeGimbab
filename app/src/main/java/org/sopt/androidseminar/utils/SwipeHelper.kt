package org.sopt.androidseminar.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeHelper(private val callback: ItemTouchCallback): ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
    ItemTouchHelper.START
) {
    interface ItemTouchCallback{
        fun changePosition(fromPosition:Int, toPosition:Int)
        fun removeItem(position:Int)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        callback.changePosition(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }


    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return ItemTouchHelper.START
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        callback.removeItem(viewHolder.adapterPosition)
    }
}