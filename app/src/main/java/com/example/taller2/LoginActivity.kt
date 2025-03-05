package com.example.taller2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etCorreo = findViewById<EditText>(R.id.et_username)
        val etContraseña = findViewById<EditText>(R.id.et_contraseña)
        val btnIngresar = findViewById<Button>(R.id.ingresa)
        val tvRegistro = findViewById<TextView>(R.id.registro)
        val tvRecuperar = findViewById<TextView>(R.id.recuperar) // 🔹 Referencia al TextView de recuperación

        btnIngresar.setOnClickListener {
            val correo = etCorreo.text.toString().trim()
            val contraseña = etContraseña.text.toString()

            if (correo.isEmpty() || contraseña.isEmpty()) {
                Toast.makeText(this, "Ingrese su correo y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (validarUsuario(correo, contraseña)) {
                guardarSesionActiva(correo)
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                // 🔹 Enviar a PerfilActivity con el correo
                val intent = Intent(this, PerfilActivity::class.java).apply {
                    putExtra("correo", correo)
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegistro.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        // 🔹 Agregar funcionalidad al TextView "recuperar"
        tvRecuperar.setOnClickListener {
            startActivity(Intent(this, RecuContraActivity::class.java))
        }
    }

    private fun validarUsuario(correo: String, contraseña: String): Boolean {
        val sharedPref = getSharedPreferences("Usuarios", Context.MODE_PRIVATE)
        val contraseñaGuardada = sharedPref.getString("$correo-contraseña", null)
        return contraseñaGuardada == contraseña
    }

    private fun guardarSesionActiva(correo: String) {
        val sharedPref = getSharedPreferences("Sesion", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("correo_activo", correo)
            apply()
        }
    }
}


