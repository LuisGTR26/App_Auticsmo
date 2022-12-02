package com.example.prototipo_tea_1.view.fragments.intro

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.FragmentSplashBinding
import kotlinx.android.synthetic.*

class SplashFragment : Fragment() {

    //Bindind
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Para ingresar al binding
        _binding = FragmentSplashBinding.inflate(inflater)
        val view = binding.root
        // Mostrar el nombre guardado en el sharedPreference
        showsaveData()
        //Para agregarle un tiempo de carga al intro
        Handler().postDelayed({
            //Para comprobar si ya se realizó la configuracion inicial
            if (onBoardingFinished()){
                findNavController().navigate(R.id.action_splashFragment_to_menuActivity2)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }, 3000)
        return view
    }

    fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    //Para mostrar el nombre del niño
    private fun showsaveData(){
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        //Nombre del niño
        val name = prefs.getString("name","")
        if (name != null) {
            binding.txtTitulo.text = "!BIENVENIDO ${name.toUpperCase()}!"
        }
    }
}