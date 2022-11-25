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

class RegistrarActivity : AppCompatActivity() {
    lateinit var btnRegistrar:Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var nombres: EditText
    private lateinit var apellidos: EditText
    private lateinit var celular: EditText
    private lateinit var direccion: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        firebaseAuth= Firebase.auth
        database= FirebaseDatabase.getInstance()
        dbReference= database.reference.child("Usuario")

        btnRegistrar = findViewById(R.id.btnRegistrar)
        val email = findViewById<EditText>(R.id.txtEmailRegistro)
        val contrasena = findViewById<EditText>(R.id.txtContrasenaRegistro)
        nombres= findViewById(R.id.txtNombresRegistro)
        apellidos= findViewById(R.id.txtApellidosRegistro)
        celular= findViewById(R.id.txtCelularRegistro)
        direccion= findViewById(R.id.txtDireccionRegistro)

        btnRegistrar.setOnClickListener {
            crearcuenta(email.text.toString(), contrasena.text.toString())
        }

    }

    private fun crearcuenta(email:String, contrasena:String){
        val firstName:String=nombres.text.toString()
        val lastName:String=apellidos.text.toString()
        val cellphone:String=celular.text.toString()
        val adress:String=direccion.text.toString()
        firebaseAuth.createUserWithEmailAndPassword(email, contrasena)
            .addOnCompleteListener(this){
                Task->if(Task.isSuccessful){
                    val user=firebaseAuth.currentUser
                    val userdb=dbReference.child(user?.uid.toString())
                    userdb.child("firstName").setValue(firstName)
                    userdb.child("lastName").setValue(lastName)
                    userdb.child("cellphone").setValue(cellphone)
                    userdb.child("adress").setValue(adress)

                    Toast.makeText(baseContext, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    Toast.makeText(baseContext, "Error de creación", Toast.LENGTH_SHORT).show()
                }
            }
    }
}