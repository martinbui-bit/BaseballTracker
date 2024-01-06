package com.example.baseballtracker2023

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getInfo(view: View){

        val client = OkHttpClient()

        val request: Request = Request.Builder()
            .url("https://api-baseball.p.rapidapi.com/games?id=1")
            .get()
            .addHeader("X-RapidAPI-Key", "059016c823msh0fd3b21bdfb5497p1811adjsna0adf8fac25b")
            .addHeader("X-RapidAPI-Host", "api-baseball.p.rapidapi.com")
            .build()

        val call = client.newCall(request)
        call.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                println("Error: $e")
            }

            override fun onResponse(call: Call, response: Response) {
                println(response.toString())
            }
        })


    }
}