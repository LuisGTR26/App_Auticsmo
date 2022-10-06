package com.example.prototipo_tea_1.model.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ambito_table")
data class AmbitoEntities(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id" )val id:Int = 0,
    @ColumnInfo(name = "ambito" )val ambito:String )