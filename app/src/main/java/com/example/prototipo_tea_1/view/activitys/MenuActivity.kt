package com.example.prototipo_tea_1.view.activitys

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.preference.PreferenceManager
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.ActivityMenuBinding


class MenuActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Verificamos si hay color seleccionado
        changecolor()

        //setting toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        //Cuando presione un boton del navBottom
        navController = Navigation.findNavController(this, R.id.fragmentMenu)
        setupWithNavController(binding.bottomAppBar, navController)

        //Ocultamos el nabBar y el bottomNavigation en los fragmentos especificos
        navController.addOnDestinationChangedListener { _, destination, _ ->
            //Los fragmentos que se ocultara
            if (destination.id == R.id.crearRutina ||
                destination.id == R.id.crearProcedimiento ||
                destination.id == R.id.procedimientosFragment ||
                destination.id == R.id.editarRutina ){
                binding.toolbar.visibility = View.GONE
                binding.bottomAppBar.visibility = View.GONE
            }
        }
    }

    //setting menu in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_settings,menu)
        return super.onCreateOptionsMenu(menu)
    }

    //El boton settings
    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Dependiendo a donde entre
        when(item.itemId){
            R.id.settings ->{
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.exit -> {
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //Cuando presione el boton retroceso del dispositivo
    override fun onBackPressed() {
    }

    fun changecolor(){
        //Para las preferencias
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val theme = prefs.getString("theme", "")
        if (theme == "blue"){
            binding.toolbar.setBackgroundColor(Color.parseColor("#039be5"))
            binding.bottomAppBar.setBackgroundColor(Color.parseColor("#039be5"))
        }else{
            binding.toolbar.setBackgroundColor(Color.parseColor("#f8bbd0"))
            binding.bottomAppBar.setBackgroundColor(Color.parseColor("#f8bbd0"))
        }
    }
}