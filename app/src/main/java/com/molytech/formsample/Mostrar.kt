package com.molytech.formsample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.molytech.formsample.databinding.ActivityMostrarBinding
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.BufferedReader

class Mostrar : AppCompatActivity() {

    private var binding: ActivityMostrarBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)

        // Leer el archivo y mostrar los datos en la tabla
        mostrarDatos()
    }

    private fun mostrarDatos() {
        val file = File(filesDir, "registro_asistentes.txt")

        if (!file.exists()) {
            Log.e("Mostrar", "El archivo no existe")
            return
        }

        try {
            val fis = FileInputStream(file)
            val isr = InputStreamReader(fis)
            val reader = BufferedReader(isr)
            var line: String?

            // Leer línea por línea y agregar las filas a la tabla
            while (reader.readLine().also { line = it } != null) {
                // Los datos están en formato "Nombres: Valor Apellidos: Valor Correo: Valor ..."
                // Dividir la línea por saltos de línea o espacios clave
                if (line != null && line!!.isNotEmpty()) {
                    val nombres = line!!.substringAfter(" ").substringBefore(" ")
                    val apellidos = line!!.substringAfter(" ").substringBefore(" ")
                    val correo = line!!.substringAfter(" ").substringBefore(" ")
                    val telefono = line!!.substringAfter(" ").substringBefore(" ")
                    val grupoSanguineo = line!!.substringAfter(" ")

                    // Crear una nueva fila y agregar las celdas
                    val tableRow = TableRow(this)

                    val nombresView = TextView(this)
                    nombresView.text = nombres
                    tableRow.addView(nombresView)

                    // Añadir la fila a la tabla
                    binding?.tableLayout?.addView(tableRow)
                }
            }
            reader.close()
            isr.close()
            fis.close()
        } catch (e: Exception) {
            Log.e("Mostrar", "Error al leer el archivo: ${e.message}")
        }
    }
}
