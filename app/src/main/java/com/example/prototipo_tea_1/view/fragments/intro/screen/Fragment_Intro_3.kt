package com.example.prototipo_tea_1.view.fragments.intro.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import androidx.viewpager2.widget.ViewPager2
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.FragmentIntro3Binding
import kotlinx.android.synthetic.main.fragment__intro_3.*


class Fragment_Intro_3 : Fragment() {

    //Binding
    private var _binding: FragmentIntro3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Para ingresar el binding
        _binding = FragmentIntro3Binding.inflate(inflater)
        val view = binding.root

        val  viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        
        //Cuando den siguiente
        val nextBtn = binding.btnNext
        nextBtn.setOnClickListener {
            val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
            val editTema = prefs.edit()
            //Verificamos que puso el niño
            var id = binding.rgSexo.checkedRadioButtonId
            if(id == R.id.rbMan){
                editTema.putString("theme", "blue")
                editTema.apply()
                viewPager?.currentItem = 3
            }else if (id == R.id.rbWoman){
                editTema.putString("theme", "pink")
                editTema.apply()
                viewPager?.currentItem = 3
            }else{
                Toast.makeText(requireContext(), "Seleccione uno", Toast.LENGTH_SHORT).show()
            }
        }
        //Cuando pulsen regresar
        val backBtn = binding.btnBack
        backBtn.setOnClickListener {
            viewPager?.currentItem = 2
        }
        return view
    }


}