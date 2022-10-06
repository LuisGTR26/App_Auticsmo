package com.example.prototipo_tea_1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.model.data.database.RutinaDatabase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val app = applicationContext as RutinaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        //Para mantener las operaciones de la bd en otro proceso
        lifecycleScope.launch{
            val rutinas = app.room.rutinaDao().getAll()
            Log.d("", "onCreate: ${rutinas.size} rutinas")
        }
    }

}