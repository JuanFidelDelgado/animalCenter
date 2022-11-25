 package com.example.animalcenter.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.animalcenter.R
import com.google.firebase.auth.FirebaseAuth

 class HomeFragment : Fragment() {

     lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         val cardPer=view.findViewById<CardView>(R.id.cardPerfil)
         cardPer.setOnClickListener {
             findNavController().navigate(R.id.action_homeFragment_to_perfilFragment)
         }

         val cardMas=view.findViewById<CardView>(R.id.cardMascotas)
         cardMas.setOnClickListener {
             findNavController().navigate(R.id.action_homeFragment_to_mascotasFragment)
         }

         val cardCit=view.findViewById<CardView>(R.id.cardCitas)
         cardCit.setOnClickListener {
             findNavController().navigate(R.id.action_homeFragment_to_citasFragment)
         }

         val cardMap=view.findViewById<CardView>(R.id.cardMapa)
         cardMap.setOnClickListener {
             findNavController().navigate(R.id.action_homeFragment_to_mapasFragment)
         }


     }


}