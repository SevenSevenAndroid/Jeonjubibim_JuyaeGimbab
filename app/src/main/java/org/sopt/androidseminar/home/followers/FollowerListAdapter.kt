package org.sopt.androidseminar.home.followers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.androidseminar.databinding.ItemGithubFollowersBinding

class FollowerListAdapter :ListAdapter<ResponseGithubFollowersItem, FollowerListAdapter.FollowerListViewHolder>(FollowerDiffCallback){
    val followerList = mutableListOf<ResponseGithubFollowersItem>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerListAdapter.FollowerListViewHolder {
        val binding = ItemGithubFollowersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return FollowerListViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: FollowerListAdapter.FollowerListViewHolder,
        position: Int
    ) {
        holder.bind(followerList[position])
        holder.itemView.isSelected = true

    }

    override fun getItemCount(): Int =followerList.size


    inner class FollowerListViewHolder(val binding : ItemGithubFollowersBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(responseGithubFollowersItem: ResponseGithubFollowersItem){
                    Glide.with(binding.root.context).load(responseGithubFollowersItem.avatar_url).into(binding.imageviewFollowers)
                    binding.textviewFollowers.text = responseGithubFollowersItem.followers_url
                }
            }

}
