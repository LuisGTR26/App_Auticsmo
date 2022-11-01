package com.example.prototipo_tea_1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prototipo_tea_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Binding
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

    }

}