package com.example.prototipo_tea_1.view.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.FragmentCrearProcedimientoBinding
import com.example.prototipo_tea_1.model.data.database.entities.Procedimiento
import com.example.prototipo_tea_1.view.activitys.MenuActivity
import com.example.prototipo_tea_1.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class CrearProcedimiento : Fragment() {

    //var glob
    lateinit var image: Bitmap
    var imageUri: Uri? = null
    private val args: CrearProcedimientoArgs by navArgs()
    private var nrutina = ""
    //ViewModel
    private lateinit var mPasoViewModel: MainViewModel

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
        //Ocultamos el bottom navigation y el banner superior
        val navB= requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val relL= requireActivity().findViewById<ConstraintLayout>(R.id.bannerMenu)
        navB.visibility = View.GONE
        relL.visibility = View.GONE

        //Obtenemos el nombre de la rutina para crear los procedimientos
        nrutina = args.rutina
        //ViewModel
        mPasoViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //Cuando pulse el boton seleccionar imagen
        val selecBtn = binding.btnCambiar
        selecBtn.setOnClickListener {
            showPictureDialog()
        }

        //Cuando pulse el boton para añadir un paso
        val addBtn = binding.btnAdd
        addBtn.setOnClickListener {
            insertPasoToDatabase()
        }

        //Cuando pulse el boton de terminar
        val exitBtn = binding.btnTerminar
        exitBtn.setOnClickListener {
            //Lo dirigimos al menu
            val intent = Intent(activity, MenuActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    //Para agregar la rutina a la bd
    private fun insertPasoToDatabase() {
        val titulo = binding.titlePaso.text.toString()
        val detalle = binding.detailsPaso.text.toString()
        val imgV = binding.imgPaso

        //Verificamos que no este vacio
        if (titulo.isNotEmpty() && detalle.isNotEmpty()){
            //Verificamos si tiene imagen
            if (imgV.tag != "vacio"){
                //Creamos un objeto
                val procedimiento = Procedimiento(0, titulo, detalle, image, nrutina)
                //Lo añadimos a la bd
                mPasoViewModel.addProcedimiento(procedimiento)
                //Desplegamos mensaje de éxito
                Toast.makeText(requireContext(), "Paso Añadido", Toast.LENGTH_LONG).show()
                //Limpiamos los campos
                binding.titlePaso.setText("")
                binding.detailsPaso.setText("")
                binding.imgPaso.setImageResource(R.drawable.ic_camara)
                imgV.tag = "vacio"
            }else{
                Toast.makeText(requireContext(), "Imagen Faltante", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(requireContext(), "Por favor ingrese los datos faltantes", Toast.LENGTH_LONG).show()
        }
    }

    /*--------------------------  CAMARA Y GALERIA    --------------------------------*/
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
    //Si selecciona galeria
    private fun SelecionGaleriaFoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, CrearRutina.IMAGE_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CrearRutina.IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            //Guardamos el valor de la imagen
            imageUri = data!!.data
            val imgstring = imageUri.toString()
            // Dentro de esta variable se guarda la conversion del Uri en Bitmap
            val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(),Uri.parse(imgstring))
            val imageView = binding.imgPaso
            imageView.setImageBitmap(bitmap)
            //Ponemos el valor del bitmap en la variable global para su uso en la base de datos
            image = bitmap
            imageView.tag = "no vacio"

        }
    }
    //Si selecciona tomar foto
    private fun TomarFotoCamara() {
        startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){

            val intent = result.data
            val imageBitmap = intent?.extras?.get("data") as Bitmap
            val imageView = binding.imgPaso
            imageView.setImageBitmap(imageBitmap)
            //Agregamos el valor de la imagen de Bitmap en la variable global para poder manejarlo fuera de la funcion.
            image = imageBitmap
            imageView.tag = "no vacio"
        }
    }

}