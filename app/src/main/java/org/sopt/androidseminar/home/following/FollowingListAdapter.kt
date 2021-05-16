package org.sopt.androidseminar.home.following

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.androidseminar.databinding.ItemGithubFollowingBinding

class FollowingListAdapter :
    ListAdapter<ResponseGithubFollowingItem, FollowingListAdapter.FollowingViewHolder>(
        FollowingDiffCallback
    ) {
    val followingList = mutableListOf<ResponseGithubFollowingItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowingListAdapter.FollowingViewHolder {
        val binding = ItemGithubFollowingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return FollowingViewHolder(binding)
    }


    override fun getItemCount(): Int = followingList.size

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.onBind(followingList[position])
        holder.itemView.isSelected = true
    }


    inner class FollowingViewHolder(val binding: ItemGithubFollowingBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun onBind(responseGithubFollowingItem: ResponseGithubFollowingItem)  {
                Glide.with(binding.root.context).load(responseGithubFollowingItem.avatar_url).into(binding.imageviewFollowing)
                binding.textviewFollowingId.text = responseGithubFollowingItem.following_url
            }

    }

}