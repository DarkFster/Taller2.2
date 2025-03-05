package com.example.taller2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RecuContraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recucontra)

        val etCorreo = findViewById<EditText>(R.id.correo2)
        val btnEnviarSolicitud = findViewById<Button>(R.id.soli)

        btnEnviarSolicitud.setOnClickListener {
            val correo = etCorreo.text.toString().trim()

            if (correo.isEmpty()) {
                Toast.makeText(this, "Ingrese un correo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (verificarCorreoRegistrado(correo)) {
                Toast.makeText(this, "Se envió la solicitud correctamente", Toast.LENGTH_SHORT).show()
                regresarALogin()
            } else {
                Toast.makeText(this, "El correo no está registrado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verificarCorreoRegistrado(correo: String): Boolean {
        val sharedPref = getSharedPreferences("Usuarios", Context.MODE_PRIVATE)
        return sharedPref.contains("$correo-contraseña")
    }

    private fun regresarALogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}

