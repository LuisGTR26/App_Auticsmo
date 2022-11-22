package com.example.prototipo_tea_1.view.fragments.intro.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.FragmentIntroBinding


class Fragment_Intro : Fragment() {

    //Binding
    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Para ingresar el binding
        _binding = FragmentIntroBinding.inflate(inflater)
        val view = binding.root

        val  viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        //Cuando den siguiente
        val nextBtn = binding.btnNext
        nextBtn.setOnClickListener {
            viewPager?.currentItem = 1
        }
        //Cuando omitan
        val skipBtn = binding.btnSkip
        skipBtn.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_menuActivity2)
            onBoardingFinished()
        }
        return view
    }

    //Al dirigirse al menu
    fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}

