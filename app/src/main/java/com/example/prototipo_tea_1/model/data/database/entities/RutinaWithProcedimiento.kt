package com.example.prototipo_tea_1.model.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class RutinaWithProcedimiento(
    @Embedded val rutina: Rutina,
    @Relation(
        //Campo de rutina
        parentColumn = "titleRutina",
        //Campo de procedimiento
        entityColumn = "rutina"
    )
    val procedimientos: List<Procedimiento>
)