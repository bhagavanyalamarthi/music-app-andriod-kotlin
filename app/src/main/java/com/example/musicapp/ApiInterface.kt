package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers(
            "X-RapidAPI-Key: 9da176a479msh946e642f2bdd12bp186b1cJSON27b19b2a6460",
            "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")


        @GET("Search")
    fun getData(@Query("q") query: String): Call<MyData>
}
