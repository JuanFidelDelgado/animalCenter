package com.example.animalcenter.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import com.example.animalcenter.R
import com.google.firebase.firestore.FirebaseFirestore

class AgendarCitaActivity : AppCompatActivity() {

    val firebaseFirestore: FirebaseFirestore= FirebaseFirestore.getInstance()
    lateinit var nombreMascota: EditText
    lateinit var fechaCita: EditText
    lateinit var horaCita: EditText
    lateinit var btnAgendarCita: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agendar_cita)

        nombreMascota=findViewById(R.id.txtNombreMascota)
        fechaCita=findViewById(R.id.txtFechaCita)
        horaCita=findViewById(R.id.txtHoraCita)
        btnAgendarCita=findViewById(R.id.btnAgendarCita)
        val cita= hashMapOf(
            "nombreMascota" to nombreMascota,
            "fechaCita" to fechaCita,
            "horaCita" to horaCita

        )
        btnAgendarCita.setOnClickListener {
            firebaseFirestore.collection("citas").document().set(cita).addOnSuccessListener {
                Toast.makeText(baseContext, "Cita Agendada", Toast.LENGTH_SHORT).show()
            }

        }
    }
}