package org.sopt.androidseminar.home.view

import android.net.Uri
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.sopt.androidseminar.api.reqres.dto.RequestReqresUsers
import org.sopt.androidseminar.api.reqres.dto.ResponseReqresUsers
import org.sopt.androidseminar.databinding.ItemFollowUserBinding
import org.sopt.androidseminar.databinding.ItemReqresUsersBinding
import retrofit2.http.Url
import java.security.AccessController.getContext

class ReqresListAdapter : RecyclerView.Adapter<ReqresListAdapter.ReqresViewHolder> (){

    val reqresList = mutableListOf<ResponseReqresUsers>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReqresViewHolder {
       val binding = ItemReqresUsersBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,false
       )
        return ReqresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReqresViewHolder, position: Int) {
        holder.onBind(reqresList[position])
    }

    override fun getItemCount(): Int = reqresList.size

    class ReqresViewHolder(val binding: ItemReqresUsersBinding):
        RecyclerView.ViewHolder(binding.root){
            fun onBind(requestReqresUsers: ResponseReqresUsers) {
                binding.textviewFirstname.text = requestReqresUsers.first_name
                binding.textviewEmail.text = requestReqresUsers.email
                Glide.with(binding.root.context).load(requestReqresUsers.avatar).into(binding.circleimagviewAvatar)


            }
        }

}