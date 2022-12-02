package com.example.prototipo_tea_1.view.activitys

import android.content.SharedPreferences
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.prototipo_tea_1.R

class SettingsActivity : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }

    //override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {}


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        //Verificamos tema
        if (key == "theme"){
            val prefsT = PreferenceManager.getDefaultSharedPreferences(this)
            val editTheme = prefsT.edit()
            val prefs = sharedPreferences?.getString(key, "1")
            when(prefs?.toInt()) {
                1 -> {
                    //Cambiamos el fondo al color azul
                    editTheme.putString("theme", "blue")
                    editTheme.apply()
                }
                2 -> {
                    //Cambiamos el fondo al color rosado
                    editTheme.putString("theme", "pink")
                    editTheme.apply()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
    }
}