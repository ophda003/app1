package com.example.showplacesinmap


import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)


        val queue = Volley.newRequestQueue(this)
        val url = "http://www.json-generator.com/api/json/get/bWarokkVSG?indent=2"
        // Create request and listeners
        val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Listener { response ->
                recyclerView.adapter = RVAdapter(response)
                          },
            Response.ErrorListener { error ->
                Log.d("JSON",error.toString())
            }
        )
        queue.add(jsonObjectRequest)


    }




}
