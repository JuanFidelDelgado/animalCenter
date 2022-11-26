package com.example.animalcenter.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.animalcenter.R
import com.google.firebase.firestore.FirebaseFirestore

class RegistrarMascotaActivity : AppCompatActivity() {

    val firebaseFirestore: FirebaseFirestore= FirebaseFirestore.getInstance()
    lateinit var btnRegistrarMascota: Button
    lateinit var nombre: EditText
    lateinit var especie: EditText
    lateinit var raza: EditText
    lateinit var edad: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_mascota)

        nombre=findViewById(R.id.txtNombreMascotaRegistro)
        especie=findViewById(R.id.txtEspecieRegistro)
        raza=findViewById(R.id.txtRazaRegistro)
        edad=findViewById(R.id.txtEdadRegistro)
        btnRegistrarMascota=findViewById(R.id.btnRegistrarMascota)
        val mascota= hashMapOf(
            "nombre" to nombre,
            "especie" to especie,
            "raza" to raza,
            "edad" to edad
        )
        btnRegistrarMascota.setOnClickListener {
                firebaseFirestore.collection("mascotas").document().set(mascota).addOnSuccessListener {
                Toast.makeText(baseContext, "Mascota registrada", Toast.LENGTH_SHORT).show()
            }

        }
    }
}