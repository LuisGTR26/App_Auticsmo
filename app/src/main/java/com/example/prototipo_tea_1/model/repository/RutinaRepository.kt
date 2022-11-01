package com.example.prototipo_tea_1.model.repository

import androidx.lifecycle.LiveData
import com.example.prototipo_tea_1.model.data.database.dao.RutinaDao
import com.example.prototipo_tea_1.model.data.database.entities.Rutina

class RutinaRepository(private val rutinaDao: RutinaDao) {

    //Obtengo todas las rutinas
    val readAllData: LiveData<List<Rutina>> = rutinaDao.getAll()

    //Obtengo todas las rutinas de un ambito en especifico
    fun getRutinaAmbito(amb: String): LiveData<List<Rutina>> {
        return rutinaDao.getRutinaAmbito(amb)
    }

    //Agregar una rutina
    fun addRutina(rutina: Rutina){
        rutinaDao.addRutina(rutina)
    }

}