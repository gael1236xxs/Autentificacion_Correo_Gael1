package com.example.autentificacion_correo_gael

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnIngresar : Button = findViewById(R.id.Entrar)
        val txtEmail : TextView = findViewById(R.id.Correo)
        val txtpass : TextView = findViewById(R.id.Contraseña)
        val btnCrearCuenta: TextView = findViewById(R.id.CrearCuenta)
        firebaseAuth = Firebase.auth
        btnIngresar.setOnClickListener()
        {
            signIn(txtEmail.text.toString(), txtpass.text.toString())
        }
        btnCrearCuenta.setOnClickListener()
        {
           val i = Intent (this,CrearCuentaActivity::class.java)
            startActivity(i)
        }

    }

    private fun signIn(email: String, password: String)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {task ->
            if (task.isSuccessful){
                val user = firebaseAuth.currentUser
                Toast.makeText(baseContext,"Autentificacion Exitosa", Toast.LENGTH_SHORT).show()
                //aqui vamos a ir a la segunda activity
                val i = Intent(this, MainActivity2::class.java)
                startActivity(i)

            }
            else
            {
                Toast.makeText(baseContext,"Error de Email y/o Contraseña", Toast.LENGTH_SHORT).show()
            }
        }

    }
}