package com.molytech.formsample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.molytech.formsample.databinding.ActivityMainBinding
import com.molytech.formsample.databinding.ActivityMenuBinding


class Menu : AppCompatActivity() {

    private var binding: ActivityMenuBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view: View = binding!!.getRoot()
        setContentView(view)

        val btnRegistrar = binding!!.btnRegistrar
        val btnMostrar = binding!!.btnMostrar
        btnRegistrar.setOnClickListener {
            Log.d("Menu", "Registrar")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) // Inicia la actividad
        }

        btnMostrar.setOnClickListener {
            Log.d("Menu", "Mostrar")
            val intent = Intent(this, Mostrar::class.java)
            startActivity(intent) // Inicia la actividad
        }

    }
}