package com.example.prototipo_tea_1.view.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.databinding.ActivityMenuBinding
import com.example.prototipo_tea_1.view.fragments.FamiliarFragment
import com.example.prototipo_tea_1.view.fragments.PersonalFragment
import com.example.prototipo_tea_1.view.fragments.SocialFragment

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        //Cuando presione un boton del navBottom
        navController = Navigation.findNavController(this, R.id.fragmentMenu)
        setupWithNavController(binding.bottomNavigationView, navController)
    }

    //Cuando presione el boton retroceso del dispositivo
    override fun onBackPressed() {
    }
}