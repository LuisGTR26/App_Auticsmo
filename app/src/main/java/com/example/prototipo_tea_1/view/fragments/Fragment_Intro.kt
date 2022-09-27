package com.example.prototipo_tea_1.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.prototipo_tea_1.R


class Fragment_Intro : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment__intro, container, false)
        //Cuando den siguiente
        val nextBtn: Button = view.findViewById(R.id.btnNext)
        nextBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragment_Intro_to_fragment_Intro_2)
        }
        //Cuando omitan
        val skipBtn: Button = view.findViewById(R.id.btnSkip)
        skipBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragment_Intro_to_menuFragment)
        }
        return view
    }

}

