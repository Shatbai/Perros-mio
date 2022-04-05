package com.example.perros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perros.adapter.PerrosAdapter
import com.example.perros.databinding.ActivityMainBinding
import com.example.perros.response.PerroResponse
import com.example.perros.service.PerrosAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: PerrosAdapter
    private lateinit var binding: ActivityMainBinding
    private val perrosPic= mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
    }
    private fun initAdapter(){
        adapter=PerrosAdapter(perrosPic)
        binding.perros.layoutManager=LinearLayoutManager(this)
        binding.perros.adapter=adapter
        buscarPerrosPorRaza("labrador")
    }

    private fun getRetroFit(): Retrofit {
        // Agregamos la liga de la API (Dog API) siempre termina con diagonal "/". En este caso como queremos buscar
        // por raza, dejamos hasta "/breed/"
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun buscarPerrosPorRaza(raza: String){
        CoroutineScope(Dispatchers.IO).launch {
            // Crea un servicio, que en este caso es la interfaz
            // Tiene :: porque debe de ser una java class
            val llamado = getRetroFit().create(PerrosAPIService::class.java).getPerrosPorRaza("$raza/images")
            // Obtiene la lista de cadenas que nos da la imagen del perro
            val perrosResponse : PerroResponse? = llamado.body()

            if(llamado.isSuccessful) {
                val imagenes: List<String> = perrosResponse?.imagenes?: emptyList()
                perrosPic.clear()
                perrosPic.addAll(imagenes)


            } else {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
            }
        }
    }
}