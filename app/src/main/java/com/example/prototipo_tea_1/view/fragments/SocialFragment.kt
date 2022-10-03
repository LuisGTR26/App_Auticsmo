package com.example.prototipo_tea_1.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.prototipo_tea_1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SocialFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_social, container, false)

        //Cuando pulsen regresar
        val backBtn: Button = view.findViewById(R.id.btnReturn)
        backBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_socialFragment_to_menuFragment)
        }

        //Cuando pulse el boton para crear una rutina
        val crearBtn: FloatingActionButton = view.findViewById(R.id.btnAdd)
        crearBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_socialFragment_to_crearRutina)
        }

        return view
    }

}