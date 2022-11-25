package com.example.animalcenter.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcenter.R
import com.example.animalcenter.view.adapter.CitasAdapter
import com.example.animalcenter.viewmodel.citasViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class citasFragment : Fragment() {

    lateinit var recyclerCitas: RecyclerView
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var adapter: CitasAdapter
    private val viewModel by lazy {ViewModelProvider(this).get(citasViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        firebaseAuth=Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view= inflater.inflate(R.layout.fragment_citas, container, false)
        recyclerCitas=view.findViewById(R.id.recyclerviewcitas)
        adapter= CitasAdapter(requireContext())
        recyclerCitas.layoutManager=LinearLayoutManager(context)
        recyclerCitas.adapter=adapter
        observeData()
        return view
    }

    fun observeData(){
        viewModel.citasData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn=view.findViewById<BottomNavigationView>(R.id.navigationBarCitas)
        btn.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home -> findNavController().navigate(R.id.action_citasFragment_to_homeFragment)
                R.id.perfil -> findNavController().navigate(R.id.action_citasFragment_to_perfilFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_citasFragment_to_mapasFragment)
                R.id.cerrarsesion->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_citasFragment_to_loginActivity)
                    true
                }
            }
        }
    }

}