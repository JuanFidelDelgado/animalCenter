package com.example.animalcenter.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.animalcenter.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecuperarActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    lateinit var btnRecuperar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar)
        firebaseAuth=Firebase.auth
        btnRecuperar=findViewById(R.id.btnRecuperar)
        val email=findViewById<EditText>(R.id.txtEmailRecuperar)
        btnRecuperar.setOnClickListener {
            cambioContrasena(email.text.toString())
        }
    }

    private fun cambioContrasena(email:String){
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this){
                Task->if(Task.isSuccessful) {
                Toast.makeText(baseContext, "Verifique la bandeja de entrada de su email", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    Toast.makeText(baseContext, "El correo ingresado no tiene cuenta creada", Toast.LENGTH_SHORT).show()
                }
            }
        }
}