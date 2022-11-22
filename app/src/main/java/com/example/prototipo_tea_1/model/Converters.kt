package com.example.prototipo_tea_1.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {

    @TypeConverter
    //Esta funcion realiza la conversion del bitmap n Bytearrat con la cual se puede almacenar en la base de datos.
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    //Con esta funcion se convierte del bytearray a bitmap para poder desplegar la imagen que se selecciona o toma con la camara.
    fun toBitmap(byteArray:ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }
}