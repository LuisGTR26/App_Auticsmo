package com.example.prototipo_tea_1.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.prototipo_tea_1.model.data.database.RutinaDatabase
import com.example.prototipo_tea_1.model.data.database.entities.Procedimiento
import com.example.prototipo_tea_1.model.data.database.entities.Rutina
import com.example.prototipo_tea_1.model.data.database.entities.RutinaWithProcedimiento
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

    //Muesta los pasos de la rutina seleccionada
    fun mostrarPasosRutina(nombreR: String): LiveData<List<RutinaWithProcedimiento>> {
           return repository.getPasoRutina(nombreR)
    }

    //Mostrar todos los pasos de una rutina 2DA FORMA
    fun mostrarPasos(nombreP: String): LiveData<List<Procedimiento>>{
        return repository.obtenerPasosRutina(nombreP)
    }
    //Creacion de rutinas
    fun addRutina(rutina: Rutina){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRutina(rutina)
        }
    }
    //Creacion de procedimientos
    fun addProcedimiento(procedimiento: Procedimiento){
        viewModelScope.launch(Dispatchers.IO){
            repository.addProcedimiento(procedimiento)
        }
    }
}
