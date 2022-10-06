package com.example.prototipo_tea_1.model.data.database.dao

import androidx.room.*
import com.example.prototipo_tea_1.model.data.database.entities.RutinaEntities

@Dao
interface RutinaDao {

    //Obtener todos los datos de la tabla
    @Query("SELECT * from rutina_table")
    suspend fun getAll(): List<RutinaEntities>

    //Obtener una rutina en especifico
    @Query("SELECT * FROM rutina_table WHERE id = :id")
    suspend fun findById(id: Int): RutinaEntities

    //Crear una nueva rutina
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(rutinas: List<RutinaEntities>)

    @Update
    suspend fun update(person: RutinaEntities)

    @Delete
    suspend fun delete(person: RutinaEntities)
}