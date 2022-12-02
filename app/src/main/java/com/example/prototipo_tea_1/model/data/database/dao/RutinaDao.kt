package com.example.prototipo_tea_1.model.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.prototipo_tea_1.model.data.database.entities.Procedimiento
import com.example.prototipo_tea_1.model.data.database.entities.Rutina
import com.example.prototipo_tea_1.model.data.database.entities.RutinaWithProcedimiento
import kotlinx.coroutines.flow.Flow

@Dao
interface RutinaDao {

    /** ----------------------- INSERTAR ----------------------- **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRutina(rutina: Rutina)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProcedimiento(procedimiento: Procedimiento)

    /** ----------------------- OBTENER ----------------------- **/
    //Obtener todas las rutinas
    @Query("SELECT * from rutina_table")
    fun getAll(): Flow<List<Rutina>>

    //Obtener todas las rutina de un ambito
    @Transaction
    @Query("SELECT * FROM rutina_table WHERE ambito = :amb")
    fun getRutinaAmbito(amb: String): LiveData<List<Rutina>>

    //Obtener todos los pasos de una rutina
    @Transaction
    @Query("SELECT * FROM rutina_table WHERE idRutina = :id")
    fun getRutinaWithProcedimientos(id: Int):  LiveData<List<RutinaWithProcedimiento>>

    //Obtener una rutina por su nombre
    @Query("SELECT idRutina FROM rutina_table WHERE titleRutina = :name")
    fun getByName(name: String): Int

    /** ----------------------- ACTUALIZAR ----------------------- **/
    @Update
    suspend fun updateRutina(rutina: Rutina)

    @Update
    suspend fun updatePaso(procedimiento: Procedimiento)

    /** ----------------------- ELIMINAR ----------------------- **/
    //TODO buscar como eliminar la rutina junto a todos sus procedimientos que tenga
    @Delete
    suspend fun deleteRutina(rutina: Rutina)

    @Delete
    suspend fun deletePaso(procedimiento: Procedimiento)
}

////Obtener todos los datos de la tabla
//    @Query("SELECT * from rutina_table")
//    fun getAll(): List<RutinaEntities>
//
//    //Obtener una rutina en especifico
//    @Query("SELECT * FROM rutina_table WHERE idRutina = :id")
//    fun getById(id: Int): RutinaEntities
//
//    //Crear una nueva rutina
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(rutinas: List<RutinaEntities>):List<Long>
//
//    @Update
//    fun update(rutina: RutinaEntities):Int
//
//    @Delete
//    fun delete(rutina: RutinaEntities):Int