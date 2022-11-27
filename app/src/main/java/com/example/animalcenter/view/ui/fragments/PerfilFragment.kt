package com.example.animalcenter.view.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcenter.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class PerfilFragment : Fragment() {

    //lateinit var recyclerPerfil: RecyclerView
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth=Firebase.auth
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        //Esta sección es para la funcionalidad del recyclerview con el car_view_perfil
        val view=inflater.inflate(R.layout.fragment_perfil, container, false)
        val nombres=view.findViewById<EditText>(R.id.txtNombresRegistro)
        val btnEditarNombres=view.findViewById<ImageButton>(R.id.btnEditarNombres)
        nombres.isEnabled=false
        btnEditarNombres.setOnClickListener {
            if(nombres.isEnabled==false) {
                nombres.isEnabled=true
            } else if(nombres.isEnabled==true){
                nombres.isEnabled=false
            }
        }

        /*recyclerPerfil=view.findViewById(R.id.recyclerviewperfil)
        val adapter=PerfilAdapter()
        recyclerPerfil.layoutManager=LinearLayoutManager(context)
        recyclerPerfil.adapter=adapter
        */

        //Esta sección es para la funcionalidad de la cámara
        val btnCamara=view.findViewById<Button>(R.id.btnCamara)
        btnCamara.setOnClickListener {
            val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)
        }

        val btnGaleria= view.findViewById<Button>(R.id.btnGaleria)
        btnGaleria.setOnClickListener {
            val intent= Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent, 456)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView=view?.findViewById<ImageView>(R.id.fotoPerfil)
        if (requestCode==123){
            var bitmap=data?.extras?.get("data") as Bitmap
            imageView?.setImageBitmap(bitmap)
        } else if (requestCode==456){
            imageView?.setImageURI(data?.data)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm=view.findViewById<BottomNavigationView>(R.id.navigationBarPerfil)
        btm.setOnNavigationItemReselectedListener{
            when(it.itemId){
                R.id.home -> findNavController().navigate(R.id.action_perfilFragment_to_homeFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_perfilFragment_to_mapasFragment)
                R.id.cerrarsesion->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_perfilFragment_to_loginActivity)
                }
            }
        }
    }
}