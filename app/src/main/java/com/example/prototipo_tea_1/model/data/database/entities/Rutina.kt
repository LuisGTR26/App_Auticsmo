package com.example.prototipo_tea_1.model.data.database.entities

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "rutina_table")
data class Rutina(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idRutina" )val idRutina:Int,
    @ColumnInfo(name = "titleRutina" )val titleRutina:String,
    @ColumnInfo(name = "imgRutina")val imgRutina: Bitmap,
    @ColumnInfo(name = "ambito")val ambito:String
    ): Parcelable