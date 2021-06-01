package com.example.androidisback

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidisback.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    companion object {
    const val TAG_NOMBRE = "Nombre"
    const val TAG_EDAD = "Edad"
    const val TAG_NOTA = "Nota"
    const val TAG_ALTURA = "Altura"
    }

    lateinit var binding : ActivityMainBinding
    var contador = 1

    var listaPersonas = mutableListOf(
        Persona ("Javier",edad=(20..35).random(),(0..10).random(), 1.73F),
        Persona ("Laia",edad=(20..35).random(),(0..10).random(), 1.72F),
        Persona ("Alex",edad=(20..35).random(),(0..10).random(), 1.90F),
        Persona ("Elisa",edad=(20..35).random(),(0..10).random(), 1.63F),
        Persona ("Daniel",edad=(20..35).random(),(0..10).random(), 1.70F),
    )

    val persona = Persona("Nueva Persona",edad=(20..35).random(),(0..10).random(),1.45F)

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tv1.text = "Aquí va la lista"
        binding.tv2.text = "Qui va como se ordena"
        Log.w("Javi", "onCreate ${contador++}")

        cargarPreferencias()?.let {
            binding.tv1.setText(it.nombre + it.altura + it.edad + it.nota)
        }

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
        listaPersonas.forEach {
            binding.tv1.text = it.toString()
        }
    }

    private fun cargarPreferencias() : Persona {
        val sharedPreference = getPreferences(Context.MODE_PRIVATE)
        var nombre = sharedPreference.getString(TAG_NOMBRE, "")
        var edad = sharedPreference.getInt(TAG_EDAD,0)
        var nota = sharedPreference.getInt(TAG_NOTA,0)
        var altura = sharedPreference.getFloat(TAG_ALTURA,0F)

        nombre?.let {
            return Persona(it,edad,nota,altura)
        } ?: run{
            return Persona("Esta persona no tiene nombre",edad,nota,altura)
        }
    }

    private fun guardarPreferencias(persona: Persona) {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        with(sharedPreferences.edit()){
            putString(TAG_NOMBRE, persona.nombre)
            putInt(TAG_EDAD,persona.edad)
            putInt(TAG_NOTA,persona.nota)
            putFloat(TAG_ALTURA,persona.altura)
            commit()
        }
    }

    data class Persona (var nombre : String, var edad : Int, var nota : Int, var altura : Float) {
        override fun toString(): String {
            return "\n Soy $nombre, tengo $edad años, he sacado un $nota de media y mido $altura metros\n"
        }
    }

}

