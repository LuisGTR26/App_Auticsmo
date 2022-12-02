package com.example.prototipo_tea_1.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.FragmentProcedimientosBinding
import com.example.prototipo_tea_1.model.data.database.entities.Procedimiento
import com.example.prototipo_tea_1.view.activitys.MenuActivity
import com.example.prototipo_tea_1.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProcedimientosFragment : Fragment() {

    //var global
    private val args by navArgs<ProcedimientosFragmentArgs>()
    private var i:Int = 0

    //viewModel
    private lateinit var procedimientosViewModel: MainViewModel
    //Binding
    private var _binding: FragmentProcedimientosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProcedimientosBinding.inflate(inflater)
        val view = binding.root
        //Obtenemos el nombre de la rutina con los argumentos
        val idRutina = args.currentRutina.idRutina
        val ambito = args.currentRutina.ambito
        //ViewModel
        procedimientosViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        //Hacemos la consulta para todos los pasos
        val listaProcedimientos = procedimientosViewModel.mostrarPasosRutina(idRutina)
        //Creamos una variable para guardar el tamaño de la lista
        var listP = 0
        //Mostrar los datos
        listaProcedimientos.observe(viewLifecycleOwner, Observer{
                listaProcedimientos -> listaProcedimientos[0]
            listP = listaProcedimientos[0].procedimientos.size
            binding.titlePaso.text = listaProcedimientos[0].procedimientos[i].titleProcedimiento
            binding.imgPaso.setImageBitmap(listaProcedimientos[0].procedimientos[i].imgProcedimiento)
            binding.txtDetail.text = listaProcedimientos[0].procedimientos[i].description
        })

        //Cuando le de siguiente
        binding.btnNext.setOnClickListener {
            //Verificamos que apenas entro
            if(binding.btnNext.text != "TERMINAR"){
                //Verificamos que recorra dentro del limite de la lista
                if (i < listP-1){
                    i++
                    val listaProcedimientos = procedimientosViewModel.mostrarPasosRutina(idRutina)
                    //Cambiamos los datos
                    listaProcedimientos.observe(viewLifecycleOwner, Observer{
                            listaProcedimientos -> listaProcedimientos[0]
                        binding.titlePaso.text = listaProcedimientos[0].procedimientos[i].titleProcedimiento
                        binding.imgPaso.setImageBitmap(listaProcedimientos[0].procedimientos[i].imgProcedimiento)
                        binding.txtDetail.text = listaProcedimientos[0].procedimientos[i].description
                    })
                }else{
                    //Cambiamos la vista a completado
                    Toast.makeText(requireContext(), "Rutina terminada", Toast.LENGTH_SHORT).show()
                    binding.titlePaso.text = "LISTO !!!"
                    binding.imgPaso.setImageResource(R.drawable.ic_done)
                    binding.txtDetail.text = "Puede regresar al menu"
                    binding.btnNext.text = "TERMINAR"
                }
            }else{
                //Lo dirigimos al menu
                val intent = Intent(activity, MenuActivity::class.java)
                startActivity(intent)
            }
        }

        //Cuando le demos en regresar
        binding.btnBack.setOnClickListener {
            //Verificamos si esta en el ultimo paso
            if(binding.btnNext.text == "TERMINAR"){
                //Cambiamos el nombre del boton
                binding.btnNext.text = "SIGUIENTE"
                val listaProcedimientos = procedimientosViewModel.mostrarPasosRutina(idRutina)
                //Cambiamos los datos
                listaProcedimientos.observe(viewLifecycleOwner, Observer{
                        listaProcedimientos -> listaProcedimientos[0]
                    binding.titlePaso.text = listaProcedimientos[0].procedimientos[i].titleProcedimiento
                    binding.imgPaso.setImageBitmap(listaProcedimientos[0].procedimientos[i].imgProcedimiento)
                    binding.txtDetail.text = listaProcedimientos[0].procedimientos[i].description
                })
            }else{
                //Verificamos que recorra dentro del limite de la lista
                if (i < listP && i != 0){
                        i--
                        val listaProcedimientos = procedimientosViewModel.mostrarPasosRutina(idRutina)
                        //Cambiamos los datos
                        listaProcedimientos.observe(viewLifecycleOwner, Observer{
                                listaProcedimientos -> listaProcedimientos[0]
                            binding.titlePaso.text = listaProcedimientos[0].procedimientos[i].titleProcedimiento
                            binding.imgPaso.setImageBitmap(listaProcedimientos[0].procedimientos[i].imgProcedimiento)
                            binding.txtDetail.text = listaProcedimientos[0].procedimientos[i].description
                        })
                }else{
                    //Lo dirigimos al menu
                    val intent = Intent(activity, MenuActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        return view
    }
}

//val listaProcedimientos = procedimientosViewModel.mostrarPasosRutina(nameRutina)
//listaProcedimientos.value?.get(0)