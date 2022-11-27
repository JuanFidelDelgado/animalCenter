package com.example.animalcenter.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcenter.R
import com.example.animalcenter.model.mascotas
import com.example.animalcenter.view.adapter.MascotasAdapter
import com.example.animalcenter.view.adapter.OnMascotasItemClickListener
import com.example.animalcenter.viewmodel.mascotasViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MascotasFragment : Fragment(), OnMascotasItemClickListener {
    lateinit var recyclerMas: RecyclerView
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var adapter: MascotasAdapter
    lateinit var btnAdicionarMascota: Button
    val database: FirebaseFirestore= FirebaseFirestore.getInstance()
    private val viewModel by lazy {ViewModelProvider(this).get(mascotasViewModel::class.java)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view= inflater.inflate(R.layout.fragment_mascotas, container, false)
        recyclerMas= view.findViewById(R.id.recyclerviewmas)
        adapter=MascotasAdapter(requireContext(), this)
        recyclerMas.layoutManager=LinearLayoutManager(context)
        recyclerMas.adapter=adapter
        observeData()
        return view
    }

    fun observeData(){
        viewModel.mascotasData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn=view.findViewById<BottomNavigationView>(R.id.navigationBarMascotas)
        btn.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home -> findNavController().navigate(R.id.action_mascotasFragment_to_homeFragment)
                R.id.perfil -> findNavController().navigate(R.id.action_mascotasFragment_to_perfilFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_mascotasFragment_to_mapasFragment)
                R.id.cerrarsesion->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_mascotasFragment_to_loginActivity)
                    true
                }
            }
        }
        // Aqui voy a adicionar la funcionalidad el boton adicionarMascota
        btnAdicionarMascota= view.findViewById(R.id.btnAdicionarMascota)
        btnAdicionarMascota.setOnClickListener {
            findNavController().navigate(R.id.action_mascotasFragment_to_registrarMascotaActivity)
        }

    }


    override fun onItemClick(mascotas: mascotas, position: Int) {
        val edad:String=mascotas.edad
        val nombre:String=mascotas.nombre
        val dato= hashMapOf(
            "edad" to edad,
            "nombre" to nombre
        )
        database.collection("citas")
            .document()
            .set(dato)
            .addOnSuccessListener {
                Toast.makeText(context, "Paciente enviado a consulta", Toast.LENGTH_SHORT).show()
            }
    }

}