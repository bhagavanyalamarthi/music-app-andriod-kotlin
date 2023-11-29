package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var myrecyclerview: RecyclerView
    lateinit var myadapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myrecyclerview = findViewById(R.id.recyclerview)

        // Create Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create API interface using the Retrofit instance
        val apiInterface = retrofit.create(ApiInterface::class.java)

        // Make API call
        val retrofitData = apiInterface.getData(query = "eminem")

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                // If the API is successful, this method is executed
                val dataList = response.body()?.data!!

                myadapter = MyAdapter(this@MainActivity, dataList   )
                myrecyclerview.adapter = myadapter
                myrecyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("TAG:onResponse", "onResponse:" + response.body())
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                // If API call is a failure, this method is executed
                Log.d("TAG:onFailure", "onFailure:" + t.message)
            }
        })
    }
}
