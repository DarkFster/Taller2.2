package com.example.taller2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etCorreo = findViewById<EditText>(R.id.correo)
        val etContraseña = findViewById<EditText>(R.id.contra)
        val etNombre = findViewById<EditText>(R.id.nombre)
        val etApellido = findViewById<EditText>(R.id.apellido)
        val etNumero = findViewById<EditText>(R.id.numero)
        val btnRegistrar = findViewById<Button>(R.id.acabarregistro)

        btnRegistrar.setOnClickListener {
            val correo = etCorreo.text.toString().trim()
            val contraseña = etContraseña.text.toString()
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val numero = etNumero.text.toString()

            if (correo.isEmpty() || contraseña.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || numero.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = getSharedPreferences("Usuarios", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("$correo-contraseña", contraseña)
                putString("$correo-nombre", nombre)
                putString("$correo-apellido", apellido)
                putString("$correo-numero", numero)
                apply()
            }

            Toast.makeText(this, "Registro exitoso. Inicia sesión.", Toast.LENGTH_SHORT).show()

            // 🔹 Ir a LoginActivity después del registro
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}


