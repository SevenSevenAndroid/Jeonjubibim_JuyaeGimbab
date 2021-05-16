package org.sopt.androidseminar.home.following

import androidx.recyclerview.widget.DiffUtil

object FollowingDiffCallback : DiffUtil.ItemCallback<ResponseGithubFollowingItem>(){
    override fun areItemsTheSame(
        oldItem: ResponseGithubFollowingItem,
        newItem: ResponseGithubFollowingItem
    ): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(
        oldItem: ResponseGithubFollowingItem,
        newItem: ResponseGithubFollowingItem
    ): Boolean {
        return oldItem == newItem
    }

}