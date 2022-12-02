package com.example.prototipo_tea_1.view.fragments

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.FragmentCrearRutinaBinding
import com.example.prototipo_tea_1.model.data.database.entities.Rutina
import com.example.prototipo_tea_1.view.activitys.MenuActivity
import com.example.prototipo_tea_1.viewmodel.MainViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.coroutines.*


class CrearRutina : Fragment() {

    //Var global
    var ambito:String = ""
    private lateinit var image: Bitmap
    private var imageUri: Uri? = null

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
        mRutinaViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //Spinner de seleccion de ambitos
        val spinner = binding.spinAmbitos
        val ambitos = resources.getStringArray(R.array.listAmbitos)
        val adaptador = ArrayAdapter(requireContext(), R.layout.list_ambitos, ambitos)
        spinner.adapter = adaptador
        //Cuando seleccionen un ambito guardar el valor
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {

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
            //Lo dirigimos al menu
            val intent = Intent(activity, MenuActivity::class.java)
            startActivity(intent)
        }

        //Cuando pulse el boton seleccionar imagen
        binding.imgRutina.setOnClickListener {
            //Verificamos que tenga permisos de camara y almacenamiento
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CAMERA
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                showPictureDialog()
            } else {
                getPermission()

            }
        }
        //Cuando pulse el boton de terminar
        val finishBtn = binding.btnIrPasos
        finishBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }
    //Para agregar la rutina a la bd
    @OptIn(DelicateCoroutinesApi::class)
    private fun insertDataToDatabase() {
        val titulo = binding.titleRutina.text.toString()
        val imgV = binding.imgRutina

        //Verificamos que no este vacio
        if (titulo.isNotEmpty()){
            //Verificamos que tenga un minimo de palabras y un maximo igual
            if(titulo.length < 3  || titulo.length > 20){
                Toast.makeText(requireContext(),"Escribe una cantidad minima de 3 y máximo de 20 palabras",Toast.LENGTH_SHORT).show()
            }else{
                //Verificamos que tenga una imagen
                if (imgV.tag != "vacio"){
                    //Creamos un objeto
                    val rutina = Rutina(0, titulo, image, ambito)
                    //Lo añadimos a la bd
                    mRutinaViewModel.addRutina(rutina)
                    //Nos dirigimos a otra pantalla para crear los pasos
                    val direction = CrearRutinaDirections.actionCrearRutinaToCrearProcedimiento(rutina.titleRutina)
                    findNavController().navigate(direction)
                    Toast.makeText(requireContext(), "Rutina Añadida: ${rutina.titleRutina}", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(requireContext(), "Imagen Faltante", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(requireContext(), "Por favor ingrese los datos faltantes", Toast.LENGTH_SHORT).show()
        }
    }

    //Despliega un cuadro de dialogo
    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(requireContext())
        val pictureDialogItems = arrayOf("Seleccionar foto de Galería", "Capturar foto con la Camara")
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
            //Guardamos el valor de la imagen
            imageUri = data!!.data
            val imgstring = imageUri.toString()
            // Dentro de esta variable se guarda la conversion del Uri en Bitmap
            val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(),Uri.parse(imgstring))
            val imageView = binding.imgRutina
            imageView.setImageBitmap(bitmap)
            //Ponemos el valor del bitmap en la variable global para su uso en la base de datos
            image = bitmap
            imageView.tag = "no vacio"
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
            //Agregamos el valor de la imagen de Bitmap en la variable global para poder manejarlo fuera de la funcion.
            image = imageBitmap
            imageView.tag = "no vacio"
        }
    }

    //Solicitamos los permisos
    fun getPermission(){
            Dexter.withContext(requireContext())
            .withPermissions(android.Manifest.permission.CAMERA,android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report.let {
                        if (report!!.areAllPermissionsGranted()){
                            Toast.makeText(requireContext(), "Permiso Otorgado", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(requireContext(), "No es Posible Acceder a esta Funcion sin los Permisos", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).withErrorListener {
                Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
            }.check()
    }

}