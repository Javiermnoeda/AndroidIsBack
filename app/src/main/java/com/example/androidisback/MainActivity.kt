package com.example.androidisback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidisback.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    var contador = 1

    var listaPersonas = mutableListOf(
        Persona ("Javier",edad=(20..35).random(),(0..10).random(), Random.nextDouble(1.50,2.05)),
        Persona ("Laia",edad=(20..35).random(),(0..10).random(), Random.nextDouble(1.50,2.05)),
        Persona ("Alex",edad=(20..35).random(),(0..10).random(), Random.nextDouble(1.50,2.05)),
        Persona ("Elisa",edad=(20..35).random(),(0..10).random(), Random.nextDouble(1.50,2.05)),
        Persona ("Daniel",edad=(20..35).random(),(0..10).random(), Random.nextDouble(1.50,2.05)),
    )
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tv1.text = "Aquí va la lista"
        binding.tv2.text = "Qui va como se ordena"
        Log.w("Javi", "onCreate ${contador++}")

        mostrarListaPersonas()
    }

    override fun onStart() {
        super.onStart()
        Log.w("Javi", "onStart ${contador++}")

    }

    override fun onResume() {
        super.onResume()
        Log.w("Javi", "onResume ${contador++}")

    }

    override fun onRestart() {
        super.onRestart()
        Log.w("Javi", "onRestart ${contador++}")

    }

    override fun onPause() {
        Log.w("Javi", "onPause ${contador++}")
        super.onPause()
    }

    override fun onStop() {
        Log.w("Javi", "onStop ${contador++}")
        super.onStop()
    }

    override fun onDestroy() {
        Log.w("Javi", "onDestroy ${contador++}")
        super.onDestroy()
    }

    fun mostrarListaPersonas(){
        val opcion = Random.nextInt(0,4)

        when (opcion){
            0 -> listaPersonas.sortBy {binding.tv2.text = "Lista ordenada por nombre de menor a mayor"
                it.nombre }
            1 -> listaPersonas.sortByDescending { binding.tv2.text= "Lista ordenada por nombre de mayor a menor"
                it.nombre}
            2 -> listaPersonas.sortByDescending { binding.tv2.text= "Lista ordenada por altura de mayor a menor"
                it.altura}
            3-> listaPersonas.sortBy { binding.tv2.text = "Lista ordenada por edad de menor a mayor"
                it.edad }
            else -> println("La opcion recibida no sirve")
        }
        binding.tv1.text = listaPersonas.toString()
    }


}

data class Persona (var nombre : String, var edad : Int, var nota : Int, var altura : Double) {
    override fun toString(): String {
        return "\n Soy $nombre, tengo $edad años, he sacado un $nota de media y mido $altura metros"
    }
}