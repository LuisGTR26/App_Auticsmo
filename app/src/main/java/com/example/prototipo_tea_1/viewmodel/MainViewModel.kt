package com.example.prototipo_tea_1.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.prototipo_tea_1.model.data.database.RutinaDatabase
import com.example.prototipo_tea_1.model.data.database.entities.Rutina
import com.example.prototipo_tea_1.model.repository.RutinaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Rutina>>

    private val repository: RutinaRepository

    //Me muestra todas las rutinas
    init {
        val rutinaDao = RutinaDatabase.getDatabase(application).rutinaDao()
        repository = RutinaRepository(rutinaDao)
        readAllData = repository.readAllData

    }

    //Me muestra las rutinas de un ambito en especifico
    fun mostrarRutinaAmbito(ambitoSeleccionado: String):LiveData<List<Rutina>>{
        return repository.getRutinaAmbito(ambitoSeleccionado)
    }

    //Creacion de rutinas
    fun addRutina(rutina: Rutina){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRutina(rutina)
        }
    }
}
