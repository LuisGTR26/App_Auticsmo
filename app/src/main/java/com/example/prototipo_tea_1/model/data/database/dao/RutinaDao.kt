package com.example.prototipo_tea_1.model.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.prototipo_tea_1.model.data.database.entities.Procedimiento
import com.example.prototipo_tea_1.model.data.database.entities.Rutina
import com.example.prototipo_tea_1.model.data.database.entities.RutinaWithProcedimiento

@Dao
interface RutinaDao {

    //Obtener todos los datos de la tabla
    @Query("SELECT * from rutina_table")
    fun getAll(): LiveData<List<Rutina>>

    //Obtener todas las rutina de un ambito en especifico
    @Transaction
    @Query("SELECT * FROM rutina_table WHERE ambito = :amb")
    fun getRutinaAmbito(amb: String): LiveData<List<Rutina>>

    //Obtener todos los pasos de una rutina
    @Transaction
    @Query("SELECT * FROM rutina_table WHERE titleRutina = :name")
    fun getRutinaWithProcedimientos(name: String):  LiveData<List<RutinaWithProcedimiento>>

    //Get pasos por name de rutina from table procedimiento 2da FORMA
    @Transaction
    @Query("SELECT * FROM procedimiento_table WHERE rutina = :nameP")
    fun getPasosRutina(nameP: String): LiveData<List<Procedimiento>>

    //Obtener una rutina en especifico
    @Query("SELECT * FROM rutina_table WHERE idRutina = :id")
    fun getById(id: Int): Rutina

    //Crear una nueva rutina
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRutina(rutina: Rutina)

    //Crear un nuevo procedimiento
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProcedimiento(procedimiento: Procedimiento)

    //Actualizar
    @Update
    fun update(rutina: Rutina):Int

    //Eliminar
    @Delete
    fun delete(rutina: Rutina):Int

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