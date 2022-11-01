package com.example.prototipo_tea_1.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    //Binding
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Para ingresar el binding
        _binding = FragmentMenuBinding.inflate(inflater)
        val view = binding.root

        //Cuando pulse el boton personal
        val personalBtn = binding.cardPersonal
        personalBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_personalFragment)
        }
        //Cuando pulse el boton familiar
        val familiarBtn = binding.cardFamiliar
        familiarBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_familiarFragment)
        }
        //Cuando pulse el boton social
        val socialBtn = binding.cardSocial
        socialBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_socialFragment)
        }
        //Cuando pulse el boton para crear una rutina
        val crearBtn = binding.btnAdd
        crearBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_crearRutina)
        }
        return view
    }
}