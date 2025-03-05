package com.example.taller2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val tvNombreApellido = findViewById<TextView>(R.id.nombre_apellido)
        val tvCorreo = findViewById<TextView>(R.id.email)
        val tvNumero = findViewById<TextView>(R.id.numerocel)
        val btnEditarPerfil = findViewById<Button>(R.id.editarperfil)

        val correoActivo = obtenerCorreoSesion()

        if (correoActivo != null) {
            val sharedPref = getSharedPreferences("Usuarios", Context.MODE_PRIVATE)
            val nombre = sharedPref.getString("$correoActivo-nombre", "Nombre no encontrado")
            val apellido = sharedPref.getString("$correoActivo-apellido", "Apellido no encontrado")
            val numero = sharedPref.getString("$correoActivo-numero", "Número no encontrado")

            tvNombreApellido.text = "$nombre $apellido"
            tvCorreo.text = correoActivo
            tvNumero.text = numero
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Botón para abrir EditPerfilActivity
        btnEditarPerfil.setOnClickListener {
            val intent = Intent(this, EditPerfilActivity::class.java)
            startActivity(intent)
        }
    }

    private fun obtenerCorreoSesion(): String? {
        val sharedPref = getSharedPreferences("Sesion", Context.MODE_PRIVATE)
        return sharedPref.getString("correo_activo", null)
    }
}



