package org.sopt.androidseminar.home.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.androidseminar.utils.SwipeHelper
import org.sopt.androidseminar.databinding.FragmentRepositoryBinding
import org.sopt.androidseminar.utils.ItemDecoration
import org.sopt.androidseminar.home.viewmodel.RepositoryViewModel

class RepositoryFragment : Fragment() {
    lateinit var binding: FragmentRepositoryBinding
    lateinit var followingListAdapter: FollowingListAdapter
    private val viewModel: RepositoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editextSearchUser.setText(requireActivity().intent.getStringExtra("name").toString())
        val name = binding.editextSearchUser.text.toString()
        viewModel.searchRepository(name)

        initRecyclerView()
        changeLayoutManager()
        updateRepository()
        changeItemPosition()
        searchGithubRepo()
    }

    private fun initRecyclerView() {
        followingListAdapter =
            FollowingListAdapter(object : FollowingListAdapter.OnItemClickListener {
                override fun itemClickListener(view: View, position: Int) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse(viewModel.repositories.value?.get(position)?.clone_url))
                    startActivity(intent)
                }
            })
        binding.recyclerviewRepositoryList.run {
            adapter = followingListAdapter
            binding.recyclerviewRepositoryList.addItemDecoration(ItemDecoration(10, 10))
        }

    }

    fun searchGithubRepo(){
        binding.buttonSearchUser.setOnClickListener {
            viewModel.searchRepository(binding.editextSearchUser.text.toString())
        }
    }

   private fun changeLayoutManager() {
       binding.buttonGridlayoutList.setOnClickListener{
           binding.recyclerviewRepositoryList.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
       }

       binding.buttonLinearlayoutList.setOnClickListener{
           binding.recyclerviewRepositoryList.layoutManager = LinearLayoutManager(requireContext())
       }
   }

    private fun changeItemPosition() {
        val swipeHelper = SwipeHelper(object: SwipeHelper.ItemTouchCallback{
            override fun changePosition(fromPosition: Int, toPosition: Int) {
                viewModel.swipeItems(fromPosition,toPosition)
                followingListAdapter.notifyItemMoved(fromPosition,toPosition)
            }

            override fun removeItem(position: Int) {
                viewModel.removeRepository(position)
                followingListAdapter.notifyItemRemoved(position)
            }
        })


        val itemTouchHelper = ItemTouchHelper(swipeHelper)
        itemTouchHelper.attachToRecyclerView(binding.recyclerviewRepositoryList)
    }

    private fun updateRepository(){
        viewModel.repositories.observe(viewLifecycleOwner){
            followingListAdapter.submitList(it)
        }
    }
}