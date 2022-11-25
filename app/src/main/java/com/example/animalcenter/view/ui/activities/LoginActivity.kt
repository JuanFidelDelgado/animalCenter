package com.example.animalcenter.view.ui.activities

import android.content.Intent
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.animalcenter.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    //Declara las variables asociadas a un objeto del activity
    lateinit var btnIngresar:Button
    lateinit var btnRegistrar:Button
    lateinit var btnRecuperar:TextView

    private lateinit var firebaseAuth: FirebaseAuth

    @RequiresApi(VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth= Firebase.auth
        //Asocia la variable declarada al botón específico
        btnIngresar=findViewById(R.id.btnIngresar)
        btnRegistrar=findViewById(R.id.btnRegistrar)
        btnRecuperar=findViewById(R.id.btnRecuperar)

        val email=findViewById<EditText>(R.id.txtEmailLogin)
        val contrasena=findViewById<EditText>(R.id.txtContrasenaLogin)

        //Activa la accion
        btnIngresar.setOnClickListener {
            login(email.text.toString(), contrasena.text.toString())
        }

        btnRegistrar.setOnClickListener {
            startActivity(Intent(this, RegistrarActivity::class.java))
        }

        btnRecuperar.setOnClickListener {
            startActivity(Intent(this, RecuperarActivity::class.java))
        }
    }

    private fun login(email:String, contrasena:String){
        firebaseAuth.signInWithEmailAndPassword(email, contrasena)
            .addOnCompleteListener(this){
                Task->if(Task.isSuccessful){
                    val user=firebaseAuth.currentUser
                    Toast.makeText(baseContext, user?.uid.toString(), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    Toast.makeText(baseContext, "Usuario o contraseña errados", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
    }
}