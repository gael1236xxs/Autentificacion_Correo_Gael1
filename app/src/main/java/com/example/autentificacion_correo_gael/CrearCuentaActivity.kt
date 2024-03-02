package com.example.autentificacion_correo_gael

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class CrearCuentaActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_cuenta)
        val txtnombre_nuevo : TextView = findViewById(R.id.Nombre)
        val txtemail_nuevo : TextView = findViewById(R.id.CorreoNuevo)
        val txtpassword1 : TextView = findViewById(R.id.ContraseñaN1)
        val txtpassword2 : TextView = findViewById(R.id.ContraseñaN2)
        val btnCrear : Button = findViewById(R.id.CrearCuentaNueva)
        btnCrear.setOnClickListener()
        {
            var pass1 = txtpassword1.text.toString()
            var pass2 = txtpassword2.text.toString()
            if (pass1.equals(pass2))
            {
                createAccount(txtemail_nuevo.text.toString(),txtpassword1.text.toString())
            }

            else
            {
                Toast.makeText(baseContext,"Error: Los password no coinciden", Toast.LENGTH_SHORT).show()
                txtpassword1.requestFocus()
            }
        }

        firebaseAuth = Firebase.auth

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun createAccount(email: String, password: String)
    {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful)
                {
                    Toast.makeText(baseContext,"Cuenta creada corecctamente", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(baseContext,"Algo salio mal, Error: " + task.exception, Toast.LENGTH_SHORT).show()
                }
            }
    }
}