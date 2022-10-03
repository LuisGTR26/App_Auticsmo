package com.example.prototipo_tea_1.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import com.example.prototipo_tea_1.R

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        //Cuando pulse el boton personal
        val personalBtn: CardView = view.findViewById(R.id.cardPersonal)
        personalBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_personalFragment)
        }

        //Cuando pulse el boton familiar
        val familiarBtn: CardView = view.findViewById(R.id.cardFamiliar)
        familiarBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_familiarFragment)
        }

        //Cuando pulse el boton social
        val socialBtn: CardView = view.findViewById(R.id.cardSocial)
        socialBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_socialFragment)
        }

        return view
    }


}