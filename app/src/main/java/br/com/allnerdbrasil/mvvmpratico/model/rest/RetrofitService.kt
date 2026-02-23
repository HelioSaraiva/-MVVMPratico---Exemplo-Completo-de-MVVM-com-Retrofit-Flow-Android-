package br.com.allnerdbrasil.mvvmpratico.model.rest

import br.com.allnerdbrasil.mvvmpratico.model.Live
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("lista-lives.json")
    suspend fun getAllLives(): List<Live>

    companion object {

        fun create(): RetrofitService {
            return Retrofit.Builder()
                .baseUrl("https://d3c0cr0sze1oo6.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)
        }
    }
}