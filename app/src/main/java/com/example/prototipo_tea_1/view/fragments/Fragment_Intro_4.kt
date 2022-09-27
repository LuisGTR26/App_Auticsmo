package com.example.prototipo_tea_1.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.prototipo_tea_1.R


class Fragment_Intro_4 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment__intro_4, container, false)

        //Cuando pulsen siguiente
        val nextBtn: Button = view.findViewById(R.id.btnNext)
        nextBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragment_Intro_4_to_menuFragment)
        }

        //Cuando pulsen regresar
        val backBtn: Button = view.findViewById(R.id.btnBack)
        backBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragment_Intro_4_to_fragment_Intro_3)

        }
        return view
    }

}