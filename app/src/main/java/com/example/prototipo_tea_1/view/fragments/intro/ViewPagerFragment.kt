package com.example.prototipo_tea_1.view.fragments.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prototipo_tea_1.databinding.FragmentViewPagerBinding
import com.example.prototipo_tea_1.view.fragments.intro.screen.Fragment_Intro
import com.example.prototipo_tea_1.view.fragments.intro.screen.Fragment_Intro_2
import com.example.prototipo_tea_1.view.fragments.intro.screen.Fragment_Intro_3
import com.example.prototipo_tea_1.view.fragments.intro.screen.Fragment_Intro_4

class ViewPagerFragment : Fragment() {

    //Binding
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Para ingresar el binding
        _binding = FragmentViewPagerBinding.inflate(inflater)
        val view = binding.root

        //Guardamos todos los fragments en un array
        val fragmentList = arrayListOf<Fragment>(
            Fragment_Intro(),
            Fragment_Intro_2(),
            Fragment_Intro_3(),
            Fragment_Intro_4()
        )

        //Guardamos los valores que necesitará el adapter
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        //Y se lo agregamos en la propiedad del adapter
        binding.viewPager.adapter = adapter

        return view

    }


}