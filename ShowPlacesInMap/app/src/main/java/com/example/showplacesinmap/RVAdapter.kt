package com.example.showplacesinmap

import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.coordinate.view.*
import org.json.JSONArray
import org.json.JSONObject




class RVAdapter(private val coordinates: JSONArray) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.coordinate, parent, false)
        return ViewHolder(view)
    }

    // return item count in employees
    override fun getItemCount(): Int = coordinates.length()

    // bind data to UI View Holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // employee to bind UI
        val coordinate: JSONObject = coordinates.getJSONObject(position)
        // name
        holder.placeIdTextView.text = "Place id: " + coordinate["index"].toString()
        holder.latitudeTextView.text = "Latitude: " + coordinate["latitude"].toString()
        holder.longitudeTextView.text = "Longitude: " + coordinate["longitude"].toString()
        holder.goButton

    }

    // View Holder class to hold UI views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeIdTextView: TextView = view.placeIdTextView
        val latitudeTextView: TextView = view.latitudeTextView
        val longitudeTextView: TextView = view.longitudeTextView
        val goButton: Unit = view.goButton.setOnClickListener {
            val intent = Intent(view.context, MapsActivity::class.java)
            intent.putExtra("coordinate",coordinates[adapterPosition].toString())
            view.context.startActivity(intent)
        }




    }

}