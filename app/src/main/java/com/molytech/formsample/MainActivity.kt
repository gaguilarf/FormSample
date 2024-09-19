package com.molytech.formsample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.molytech.formsample.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)

        // Configurar el botón para registrar los datos
        binding?.btnRegistrar?.setOnClickListener {
            registrarDatos()
            finish()
        }
    }

    private fun registrarDatos() {
        // Obtener los valores de los campos de texto
        val nombres = binding?.nombres?.text.toString()
        val apellidos = binding?.apellidos?.text.toString()
        val correo = binding?.correo?.text.toString()
        val telefono = binding?.telefono?.text.toString()
        val grupoSanguineo = binding?.grupoSanguineo?.text.toString()

        // Validar que los campos no estén vacíos
        if (nombres.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || telefono.isEmpty() || grupoSanguineo.isEmpty()) {
            Log.e("Registro", "Todos los campos son obligatorios")
            return
        }

        // Crear el contenido para guardar en el archivo de texto
        val datos = """
            Nombres: $nombres
            Apellidos: $apellidos
            Correo: $correo
            Teléfono: $telefono
            Grupo_Sanguíneo: $grupoSanguineo
            
        """.trimIndent()

        // Guardar los datos en un archivo de texto
        try {
            val file = File(filesDir, "registro_asistentes.txt")
            val fos = FileOutputStream(file, true) // 'true' para agregar al archivo
            val osw = OutputStreamWriter(fos)
            osw.append(datos)
            osw.append("\n")  // Salto de línea
            osw.close()
            fos.close()
            Log.i("Registro", "Datos guardados correctamente en ${file.absolutePath}")
        } catch (e: Exception) {
            Log.e("Registro", "Error al guardar los datos: ${e.message}")
        }
    }
}
