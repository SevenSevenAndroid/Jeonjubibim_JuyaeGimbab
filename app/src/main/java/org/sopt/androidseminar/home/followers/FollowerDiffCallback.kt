package org.sopt.androidseminar.home.followers

import androidx.recyclerview.widget.DiffUtil

object FollowerDiffCallback : DiffUtil.ItemCallback<ResponseGithubFollowersItem>() {
    override fun areItemsTheSame(
        oldItem: ResponseGithubFollowersItem,
        newItem: ResponseGithubFollowersItem
    ): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(
        oldItem: ResponseGithubFollowersItem,
        newItem: ResponseGithubFollowersItem
    ): Boolean {
        return oldItem == newItem
    }
}