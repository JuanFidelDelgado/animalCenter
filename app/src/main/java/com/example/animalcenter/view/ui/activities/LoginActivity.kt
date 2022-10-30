package com.example.animalcenter.view.ui.activities

import android.content.Intent
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.animalcenter.R

class LoginActivity : AppCompatActivity() {
    //Declara las variables asociadas a un objeto del activity
    lateinit var btnIngresar:Button
    lateinit var btnRegistrar:Button
    lateinit var btnRecuperar:TextView
    @RequiresApi(VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Asocia la variable declarada al botón específico
        btnIngresar=findViewById(R.id.btnIngresar)
        btnRegistrar=findViewById(R.id.btnRegistrar)
        btnRecuperar=findViewById(R.id.btnRecuperar)

        //Activa la accion
        btnIngresar.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        btnRegistrar.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        btnRecuperar.setOnClickListener {
            startActivity(Intent(this, RecuperarActivity::class.java))
        }
    }
}