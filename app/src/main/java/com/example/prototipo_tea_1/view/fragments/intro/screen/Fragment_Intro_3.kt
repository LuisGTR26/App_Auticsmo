package com.example.prototipo_tea_1.view.fragments.intro.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.FragmentIntro3Binding


class Fragment_Intro_3 : Fragment() {

    //Binding
    private var _binding: FragmentIntro3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Para ingresar el binding
        _binding = FragmentIntro3Binding.inflate(inflater)
        val view = binding.root

        val  viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        
        //Cuando den siguiente
        val nextBtn = binding.btnNext
        nextBtn.setOnClickListener {
            viewPager?.currentItem = 3
        }
        //Cuando pulsen regresar
        val backBtn = binding.btnBack
        backBtn.setOnClickListener {
            viewPager?.currentItem = 2
        }
        return view
    }


}