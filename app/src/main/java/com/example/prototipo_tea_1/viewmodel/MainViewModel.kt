package com.example.prototipo_tea_1.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.prototipo_tea_1.model.data.database.RutinaDatabase
import com.example.prototipo_tea_1.model.data.database.entities.Procedimiento
import com.example.prototipo_tea_1.model.data.database.entities.Rutina
import com.example.prototipo_tea_1.model.data.database.entities.RutinaWithProcedimiento
import com.example.prototipo_tea_1.model.repository.RutinaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository: RutinaRepository
    private val readAllData: Flow<List<Rutina>>
    init {
        val rutinaDao = RutinaDatabase.getDatabase(application).rutinaDao()
        repository = RutinaRepository(rutinaDao)
        readAllData = repository.readAllData

    }

    /** ----------------------- OBTENER ----------------------- **/
    //Me muestra las rutinas de un ambito
    fun mostrarRutinaAmbito(ambito: String): LiveData<List<Rutina>> {
        return repository.getRutinaAmbito(ambito)
    }

    //Muesta los pasos de la rutina
    fun mostrarPasosRutina(idR: Int): LiveData<List<RutinaWithProcedimiento>> {
        return repository.getPasoRutina(idR)
    }

    //Muesta el id de la rutina
    fun mostrarIdRutina(name:String):Int{
        return repository.getIdRutina(name)
    }

    /** ----------------------- INSERTAR ----------------------- **/
    fun addRutina(rutina: Rutina){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRutina(rutina)
        }
    }

    fun addPaso(procedimiento: Procedimiento){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPaso(procedimiento)
        }
    }
    /** ----------------------- ACTUALIZAR ----------------------- **/
    fun actualizarRutina(rutina: Rutina){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRutina(rutina)
        }
    }

    fun actualizarPaso(procedimiento: Procedimiento){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePaso(procedimiento)
        }
    }
    /** ----------------------- ELIMINAR ----------------------- **/
    fun borrarRutina(rutina: Rutina){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRutina(rutina)
        }
    }

    fun borrarPaso(procedimiento: Procedimiento){
       viewModelScope.launch(Dispatchers.IO){
           repository.deletePaso(procedimiento)
       }
    }

   /*
    fun addRutina(rutina: Rutina) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRutina(rutina)
        }
    }
   */

    //Creacion de rutinas devolviendo el id
    /*
    fun addRutina2(rutina: Rutina) = liveData {
        //you can also emit your customized object here.
        emit("Insertando...")
        try {
            val response = repository.addRutina(rutina)
            emit(response)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(e.message)
        }
    }
    */

}
