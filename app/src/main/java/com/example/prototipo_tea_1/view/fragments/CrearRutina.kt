package com.example.prototipo_tea_1.view.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.FragmentCrearRutinaBinding
import com.example.prototipo_tea_1.model.data.database.entities.Rutina
import com.example.prototipo_tea_1.viewmodel.MainViewModel


class CrearRutina : Fragment() {

    //Var global
    var ambito: String = ""
    //Binding
    private var _binding: FragmentCrearRutinaBinding? = null
    private val binding get() = _binding!!
    //ViewModel
    private lateinit var mRutinaViewModel: MainViewModel
    //Para la camara
    companion object{
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Para ingresar el binding
        _binding = FragmentCrearRutinaBinding.inflate(inflater)
        val view = binding.root

        //ViewModel
        mRutinaViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Spinner de seleccion de ambitos
        val spinner = binding.spinAmbitos
        val ambitos = resources.getStringArray(R.array.listAmbitos)
        val adaptador = ArrayAdapter(requireContext(), R.layout.list_ambitos, ambitos)
        spinner.adapter = adaptador
        //Cuando seleccionen un ambito guardar el valor
        spinner.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                ambito = binding.spinAmbitos.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        //Cuando pulse el boton para regresar al menu
        val backBtn = binding.btnBack
        backBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_crearRutina_to_menuFragment)
        }
        //Cuando pulse el boton seleccionar imagen
        val selecBtn = binding.btnCambiar
        selecBtn.setOnClickListener {
            showPictureDialog()
        }
        //Cuando pulse el boton de terminar
        val finishBtn = binding.btnIrPasos
        finishBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    //Para agregar la rutina a la bd
    private fun insertDataToDatabase() {
        val titulo = binding.titleRutina.text.toString()
        //Verificamos que no este vacio
        if (inputCheck(titulo)){
            //Crea el objeto
            val rutina = Rutina(0, titulo, ambito)
            //Lo añadimos a la bd
            mRutinaViewModel.addRutina(rutina)
            //Desplegamos mensaje de éxito
            Toast.makeText(requireContext(), "Rutina Añadida", Toast.LENGTH_LONG).show()
            //Regresamos a la pantalla principal
            findNavController().navigate(R.id.action_crearRutina_to_crearProcedimiento)
        }else{
            //Desplegamos mensaje de error
            Toast.makeText(requireContext(), "Por favor ingrese los datos faltantes", Toast.LENGTH_LONG).show()
        }
    }

    //Para verificar si estan vacios los editText
    private fun inputCheck(titulo:String):Boolean{
        //TODO Por ahora solo verificamos el titulo
        return !(TextUtils.isEmpty(titulo))
    }


    //Despliega un cuadro de dialogo
    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(requireContext())
        val pictureDialogItems = arrayOf("Seleccinar foto de Galería", "Capturar foto con la Camara")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            //Dependiendo de la selecciona de arriba asigna la funcion 0-Galeria y 1-Camara
            when (which) {
                0 -> SelecionGaleriaFoto()
                1 -> TomarFotoCamara()
            }
        }
        pictureDialog.show()
    }
    private fun SelecionGaleriaFoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            binding.imgRutina.setImageURI(data?.data)
        }
    }
    private fun TomarFotoCamara() {
        startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){

            val intent = result.data
            val imageBitmap = intent?.extras?.get("data") as Bitmap
            val imageView = binding.imgRutina
            imageView.setImageBitmap(imageBitmap)
        }
    }

}