package com.example.prototipo_tea_1.model.data.database.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "procedimiento_table",
        foreignKeys = [
            ForeignKey(
                entity = Rutina::class,
                parentColumns = ["idRutina"],
                childColumns = ["rutina"],
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
            )
        ]
)
data class Procedimiento (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idProcedimiento" )val idProcedimiento:Long,
    @ColumnInfo(name = "titleProcedimiento" )val titleProcedimiento:String,
    @ColumnInfo(name = "descripcion")val description:String,
    @ColumnInfo(name = "imgProcedimiento")val imgProcedimiento:Bitmap,
    @ColumnInfo(name = "rutina")val rutina:Int
)
