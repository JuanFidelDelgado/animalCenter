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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegistrarMascotaActivity : AppCompatActivity() {
    lateinit var btnRegistrarMascota: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var nombreMascota: EditText
    private lateinit var especie: EditText
    private lateinit var raza: EditText
    private lateinit var edad: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_mascota)
        firebaseAuth= Firebase.auth
        database= FirebaseDatabase.getInstance()
        dbReference= database.reference.child("Mascotas")

        btnRegistrarMascota = findViewById(R.id.btnRegistrarMascota)
        nombreMascota= findViewById(R.id.txtNombreMascotaRegistro)
        especie= findViewById(R.id.txtEspecieRegistro)
        raza= findViewById(R.id.txtRazaRegistro)
        edad= findViewById(R.id.txtFechaNacimientoRegistro)

        btnRegistrarMascota.setOnClickListener {
            registrarMascota()
        }

    }

    private fun registrarMascota(){
        val nombreMascota:String=nombreMascota.text.toString()
        val especie:String=especie.text.toString()
        val raza:String=raza.text.toString()
        val fechaNacimiento:String=edad.text.toString()
        firebaseAuth.signInAnonymously().addOnCompleteListener(this){
                    Task->if(Task.isSuccessful){
                val user=firebaseAuth.currentUser
                val userdb=dbReference.child(user?.uid.toString())
                userdb.child("nombre").setValue(nombreMascota)
                userdb.child("especie").setValue(especie)
                userdb.child("raza").setValue(raza)
                userdb.child("edad").setValue(fechaNacimiento)

                Toast.makeText(baseContext, "Mascota registrada con éxito", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                Toast.makeText(baseContext, "Error de registro", Toast.LENGTH_SHORT).show()
            }
            }
    }
}