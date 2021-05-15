package org.sopt.androidseminar.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import org.sopt.androidseminar.R
import org.sopt.androidseminar.databinding.FragmentImageSlideBinding


class ImageSlideFragment(val image : Int) : Fragment() {
    lateinit var binding : FragmentImageSlideBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageSlideBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageviewItemMypage.setImageResource(image)


    }
    override fun onDestroyView() {
        super.onDestroyView()
    }

}