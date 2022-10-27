package com.example.lab8_plataformas.ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab8_plataformas.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cargarVista()
    }


    private fun cargarVista() {
        setContentView(R.layout.activity_main_sesion_sin_iniciar)
    }

}