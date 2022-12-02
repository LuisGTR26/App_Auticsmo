package com.example.prototipo_tea_1.view.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.FragmentEditarRutinaBinding
import com.example.prototipo_tea_1.model.data.database.entities.Rutina
import com.example.prototipo_tea_1.view.activitys.MenuActivity
import com.example.prototipo_tea_1.viewmodel.MainViewModel


class EditarRutina : Fragment() {

    //var global
    private val args by navArgs<EditarRutinaArgs>()
    private lateinit var image: Bitmap
    private var imageUri: Uri? = null
    var ambito:String = ""

    //viewModel
    private lateinit var editProcedimientosViewModel: MainViewModel

    //binding
    private var _binding: FragmentEditarRutinaBinding? = null
    private val binding get() = _binding!!

    //Para la camara
    companion object{
        const val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditarRutinaBinding.inflate(inflater)
        val view = binding.root

        //ViewModel
        editProcedimientosViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //Asignamos los datos al fragmento
        binding.editTittleRutina.setText(args.currentRutina.titleRutina)
        binding.editImgRutina.setImageBitmap(args.currentRutina.imgRutina)

        //Para cambiar la imagen
        binding.editImgRutina.setOnClickListener {
            showPictureDialog()
        }

        //Para guardar los cambios
        binding.btnGuardar.setOnClickListener {
            guardarCambios()
        }

        //Para eliminar la rutina
        binding.btnEliminar.setOnClickListener {
            deleteRutina()
        }
        return view
    }

    private fun guardarCambios() {

        val titulo = binding.editTittleRutina.text.toString()
        val imgTag = binding.editImgRutina
        val rutina:Rutina

        if (titulo.isNotEmpty()){
            //Verificamos que tenga un minimo de palabras y un maximo igual
            if(titulo.length < 3  || titulo.length > 20){
                Toast.makeText(requireContext(),"Escribe una cantidad minima de 3 y máximo de 20 palabras",
                    Toast.LENGTH_SHORT).show()
            }else{
                //Verificamos si cambio la imagen o no
                rutina = if(imgTag.tag == "actualizado"){
                    Rutina(args.currentRutina.idRutina, titulo, image, args.currentRutina.ambito)
                }else{
                    Rutina(args.currentRutina.idRutina, titulo, args.currentRutina.imgRutina, args.currentRutina.ambito)
                }
                //Actualizamos la rutina
                editProcedimientosViewModel.actualizarRutina(rutina)
                //Desplegamos mensaje de éxito
                Toast.makeText(requireContext(), "Rutina Actualizada", Toast.LENGTH_SHORT).show()
                //Nos dirigimos al menu
                val intent = Intent(activity, MenuActivity::class.java)
                startActivity(intent)

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
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            //Guardamos el valor de la imagen
            imageUri = data!!.data
            val imgstring = imageUri.toString()
            // Dentro de esta variable se guarda la conversion del Uri en Bitmap
            val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver,
                Uri.parse(imgstring))
            val imageView = binding.editImgRutina
            imageView.setImageBitmap(bitmap)
            //Ponemos el valor del bitmap en la variable global para su uso en la base de datos
            image = bitmap
            imageView.tag = "actualizado"
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
            val imageView = binding.editImgRutina
            imageView.setImageBitmap(imageBitmap)
            //Agregamos el valor de la imagen de Bitmap en la variable global para poder manejarlo fuera de la funcion.
            image = imageBitmap
            imageView.tag = "actualizado"
        }
    }

    private fun deleteRutina() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Si"){_, _ ->
            //Eliminamos la rutina
            editProcedimientosViewModel.borrarRutina(args.currentRutina)
            Toast.makeText(requireContext(), "Se ha eliminado ${args.currentRutina.titleRutina}", Toast.LENGTH_SHORT).show()
            //Nos dirigimos al menu
            val intent = Intent(activity, MenuActivity::class.java)
            startActivity(intent)
        }
        builder.setNegativeButton("No"){_, _ -> }
        builder.setTitle("Borrar ${args.currentRutina.titleRutina}?")
        builder.setMessage("Estar seguro de borrar ${args.currentRutina.titleRutina}")
        builder.create().show()
    }
}