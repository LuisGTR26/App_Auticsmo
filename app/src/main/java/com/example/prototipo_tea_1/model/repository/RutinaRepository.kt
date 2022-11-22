package com.example.prototipo_tea_1.model.repository

import androidx.lifecycle.LiveData
import com.example.prototipo_tea_1.model.data.database.dao.RutinaDao
import com.example.prototipo_tea_1.model.data.database.entities.Procedimiento
import com.example.prototipo_tea_1.model.data.database.entities.Rutina
import com.example.prototipo_tea_1.model.data.database.entities.RutinaWithProcedimiento

class RutinaRepository(private val rutinaDao: RutinaDao) {

    //Obtengo todas las rutinas
    val readAllData: LiveData<List<Rutina>> = rutinaDao.getAll()

    //Obtengo todas las rutinas de un ambito en especifico
    fun getRutinaAmbito(amb: String): LiveData<List<Rutina>> {
        return rutinaDao.getRutinaAmbito(amb)
    }

    //Obtengo todos los pasos de una rutina en especifico
    fun getPasoRutina(name: String): LiveData<List<RutinaWithProcedimiento>> {
        return rutinaDao.getRutinaWithProcedimientos(name)
    }

    //Pasos de la rutina 2DA FORMA
    fun obtenerPasosRutina(nameP: String): LiveData<List<Procedimiento>>{
        return rutinaDao.getPasosRutina(nameP)
    }

    //Agregar una rutina
    fun addRutina(rutina: Rutina){
        rutinaDao.addRutina(rutina)
    }

    //Agregar un procedimiento
    fun addProcedimiento(procedimiento: Procedimiento){
        rutinaDao.addProcedimiento(procedimiento)
    }

}