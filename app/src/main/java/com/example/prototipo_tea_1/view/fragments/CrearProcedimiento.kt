package com.example.prototipo_tea_1.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.FragmentCrearProcedimientoBinding
import com.example.prototipo_tea_1.databinding.FragmentCrearRutinaBinding
import com.example.prototipo_tea_1.viewmodel.MainViewModel


class CrearProcedimiento : Fragment() {

    //ViewModel
    private lateinit var mRutinaViewModel: MainViewModel

    //Binding
    private var _binding: FragmentCrearProcedimientoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Para ingresar el binding
        _binding = FragmentCrearProcedimientoBinding.inflate(inflater)
        val view = binding.root


        return view
    }


}