package com.example.prototipo_tea_1.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.prototipo_tea_1.R


class CrearRutina : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_crear_rutina, container, false)

        //Cuando pulse el boton para regresar
        val crearBtn: Button = view.findViewById(R.id.btnBack)
        crearBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_crearRutina_to_familiarFragment)
        }

        //Cuando pulse el boton de terminar


        return view
    }

}