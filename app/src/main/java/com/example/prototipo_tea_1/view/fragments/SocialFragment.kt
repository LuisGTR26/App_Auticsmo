package com.example.prototipo_tea_1.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.adapters.SocialAdapter
import com.example.prototipo_tea_1.databinding.FragmentSocialBinding
import com.example.prototipo_tea_1.viewmodel.MainViewModel


class SocialFragment : Fragment() {

    //Var global
    val NOMBRE_DE_AMBITO = "SOCIAL"
    //Binding
    private var _binding: FragmentSocialBinding? = null
    private val binding get() = _binding!!
    //ViewModel
    lateinit var mRutinaViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Para ingresar el binding
        _binding = FragmentSocialBinding.inflate(inflater)
        val view = binding.root

        //RecyclerView
        val adapter = SocialAdapter()
        val recyclerView = binding.listRutinas
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //RutinaViewModel
        mRutinaViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        //Lectura y Observador en el Recycler
        //Primero obtenemos las rutinas de familiar
        val listaRutinas = mRutinaViewModel.mostrarRutinaAmbito(NOMBRE_DE_AMBITO)
        //
        listaRutinas.observe(viewLifecycleOwner, Observer{ rutina ->
            adapter.setData(rutina)
        })

        //Cuando pulse el boton para crear una rutina
        val crearBtn = binding.btnAdd
        crearBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_socialFragment_to_crearRutina)
        }

        //Cuando pulsen regresar
        //val backBtn = binding.btnReturn
        //        backBtn.setOnClickListener {
        //            Navigation.findNavController(view).navigate(R.id.action_socialFragment_to_menuFragment)
        //        }

        return view
    }

}