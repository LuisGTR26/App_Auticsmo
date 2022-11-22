package com.example.prototipo_tea_1.view.fragments.intro.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.FragmentIntro2Binding


class Fragment_Intro_2 : Fragment() {

    //Binding
    private var _binding: FragmentIntro2Binding? = null
    private val binding get() = _binding!!

    // Valores para guardar el nomnbre
    val MY_SHARED_PREF_NAME = "my_shared_pref"
    val NAME = "name"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Para ingresar el binding
        _binding = FragmentIntro2Binding.inflate(inflater)
        val view = binding.root

        val  viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        //Cuando den siguiente
        val nextBtn = binding.btnNext
        nextBtn.setOnClickListener {
            saveData()
            viewPager?.currentItem = 2
        }
        //Cuando pulsen regresar
        val backBtn = binding.btnBack
        backBtn.setOnClickListener {
            viewPager?.currentItem = 1
        }
        return view
    }

    private fun saveData(){
        val insertName = binding.etNombre.text.toString()
        val sharedPref = this.requireActivity().getSharedPreferences(MY_SHARED_PREF_NAME,
            Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(NAME,insertName)
        editor.apply()
    }

}