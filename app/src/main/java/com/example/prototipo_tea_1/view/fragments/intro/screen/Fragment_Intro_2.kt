package com.example.prototipo_tea_1.view.fragments.intro.screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.viewpager2.widget.ViewPager2
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.FragmentIntro2Binding


class Fragment_Intro_2 : Fragment() {

    //Binding
    private var _binding: FragmentIntro2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Para ingresar el binding
        _binding = FragmentIntro2Binding.inflate(inflater)
        val view = binding.root
        val  viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        //viewPager?.isUserInputEnabled = false
        //Cuando den siguiente
        val nextBtn = binding.btnNext
        nextBtn.setOnClickListener {
            val name = binding.etNombre.text.toString()
            //Verificamos que ponga el nombre del niño
            if (name.isNotEmpty()){
                //Que tenga un minimo de palabras
                if (name.length < 2 || name.length > 10){
                    Toast.makeText(requireContext(), "Por favor ingresa un minimo de 2 palabras y un máximo de 10", Toast.LENGTH_SHORT).show()
                }else{
                    saveData()
                    viewPager?.currentItem = 2
                    //viewPager?.isUserInputEnabled = true
                }
            }else{
                Toast.makeText(requireContext(), "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show()
            }
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
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editname = prefs.edit()
        editname.putString("name", insertName)
        editname.apply()
    }

}