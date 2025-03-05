package com.example.taller2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditPerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editperfil)

        val etNombre = findViewById<EditText>(R.id.name)
        val etEmail = findViewById<EditText>(R.id.email2)
        val etNumero = findViewById<EditText>(R.id.number)
        val btnGuardar = findViewById<Button>(R.id.editarperfil)

        val correoActivo = obtenerCorreoSesion()

        if (correoActivo != null) {
            val sharedPref = getSharedPreferences("Usuarios", Context.MODE_PRIVATE)
            val nombre = sharedPref.getString("$correoActivo-nombre", "")
            val numero = sharedPref.getString("$correoActivo-numero", "")

            etNombre.setText(nombre)
            etEmail.setText(correoActivo) // No debe editarse el email
            etEmail.isEnabled = false     // Deshabilita la edición del email
            etNumero.setText(numero)
        }

        btnGuardar.setOnClickListener {
            val nuevoNombre = etNombre.text.toString().trim()
            val nuevoNumero = etNumero.text.toString().trim()

            if (correoActivo != null) {
                val sharedPref = getSharedPreferences("Usuarios", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("$correoActivo-nombre", nuevoNombre)
                    putString("$correoActivo-numero", nuevoNumero)
                    apply()
                }
            }

            // Regresa a PerfilActivity
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun obtenerCorreoSesion(): String? {
        val sharedPref = getSharedPreferences("Sesion", Context.MODE_PRIVATE)
        return sharedPref.getString("correo_activo", null)
    }
}
