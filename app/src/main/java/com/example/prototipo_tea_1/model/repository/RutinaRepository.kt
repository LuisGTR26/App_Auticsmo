package com.example.prototipo_tea_1.model.repository

import androidx.lifecycle.LiveData
import com.example.prototipo_tea_1.model.data.database.dao.RutinaDao
import com.example.prototipo_tea_1.model.data.database.entities.Procedimiento
import com.example.prototipo_tea_1.model.data.database.entities.Rutina
import com.example.prototipo_tea_1.model.data.database.entities.RutinaWithProcedimiento
import kotlinx.coroutines.flow.Flow

class RutinaRepository(private val rutinaDao: RutinaDao) {

    /** ----------------------- OBTENER ----------------------- **/
    //Obtengo todas las rutinas
    val readAllData: Flow<List<Rutina>> = rutinaDao.getAll()

    //Obtengo todas las rutinas de un ambito
    fun getRutinaAmbito(amb: String): LiveData<List<Rutina>> {
        return rutinaDao.getRutinaAmbito(amb)
    }

    //Obtengo todos los pasos de una rutina
    fun getPasoRutina(idR: Int): LiveData<List<RutinaWithProcedimiento>> {
        return rutinaDao.getRutinaWithProcedimientos(idR)
    }

    //Obtengo id de rutina por el nombre
    fun getIdRutina(name:String):Int{
        return rutinaDao.getByName(name)
    }
    /** ----------------------- INSERTAR ----------------------- **/
    suspend fun addRutina(rutina: Rutina){
        rutinaDao.addRutina(rutina)
    }

    suspend fun addPaso(procedimiento: Procedimiento){
        rutinaDao.addProcedimiento(procedimiento)
    }
    /** ----------------------- ACTUALIZAR ----------------------- **/
    suspend fun updateRutina(rutina: Rutina){
        rutinaDao.updateRutina(rutina)
    }

    suspend fun updatePaso(procedimiento: Procedimiento){
        rutinaDao.updatePaso(procedimiento)
    }
    /** ----------------------- ELIMINAR ----------------------- **/
    suspend fun deleteRutina(rutina: Rutina){
        rutinaDao.deleteRutina(rutina)
    }

    suspend fun deletePaso(procedimiento: Procedimiento){
        rutinaDao.deletePaso(procedimiento)
    }

}