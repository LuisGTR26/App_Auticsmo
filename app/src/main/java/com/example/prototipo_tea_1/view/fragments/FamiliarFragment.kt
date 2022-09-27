package com.example.prototipo_tea_1.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.prototipo_tea_1.R


class FamiliarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_familiar, container, false)

        //Cuando pulsen regresar
        val nextBtn: Button = view.findViewById(R.id.btnReturn)
        nextBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_familiarFragment_to_menuFragment)
        }

        return view
    }

}