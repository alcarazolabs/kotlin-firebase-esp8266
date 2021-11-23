package com.example.miprimeriot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.miprimeriot.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.getReference("casa") // Quitar la referencia si solo se desea acceder al root.

        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.txtHumedad.text = snapshot.child("humedad").getValue().toString()
                binding.txtTemperatura.text  = snapshot.child("temperatura").getValue().toString()
                binding.txtCo2.text  = snapshot.child("co2").getValue().toString()

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("MainActivity", "Fallo al leer el valor.", error.toException())
            }

        })

    }
}