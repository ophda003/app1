package com.example.showgolfcoursesinmap

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.widget.TextView
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.google.android.gms.maps.model.Marker



@Suppress("NAME_SHADOWING")
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */



    override fun onMapReady(googleMap: GoogleMap) {
        val queue = Volley.newRequestQueue(this)

        val url = "http://ptm.fi/materials/golfcourses/golf_courses.json"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val courses = response.getJSONArray("courses")
                for(i in 0 until courses.length()){
                    val course = courses.getJSONObject(i)
                    val lat = course["lat"].toString().toDouble()
                    val lng = course["lng"].toString().toDouble()
                    val type = course["type"].toString()
                    val title = course["course"].toString()
                    val address = course["address"].toString()
                    val phone = course["phone"].toString()
                    val email = course["email"].toString()
                    val web = course["web"].toString()
                    val infoWindowText = address+"\n"+phone+"\n"+email+"\n"+web



                    fun setInfoWindow(){
                        mMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {

                            override fun getInfoWindow(arg0: Marker): View? {
                                return null
                            }

                            override fun getInfoContents(marker: Marker): View {

                                val info = LinearLayout(this@MapsActivity)
                                info.orientation = LinearLayout.VERTICAL

                                val title = TextView(this@MapsActivity)
                                title.setTextColor(Color.BLACK)
                                title.gravity = Gravity.CENTER
                                title.setTypeface(null, Typeface.BOLD)
                                title.text = marker.title

                                val snippet = TextView(this@MapsActivity)
                                snippet.setTextColor(Color.GRAY)
                                snippet.text = marker.snippet

                                info.addView(title)
                                info.addView(snippet)

                                return info
                            }
                        })
                    }

                    if(type == "Kulta"){
                        mMap = googleMap

                        val location = LatLng(lat, lng)
                        mMap.addMarker(MarkerOptions()
                            .position(location)
                            .title(title)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                            .snippet(infoWindowText))
                        setInfoWindow()
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,5f))

                    }else if(type == "Kulta/Etu"){
                        mMap = googleMap

                        val location = LatLng(lat, lng)
                        mMap.addMarker(MarkerOptions()
                            .position(location)
                            .title(title)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                            .snippet(infoWindowText))
                        setInfoWindow()
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,5f))

                    }else if (type =="?"){
                        mMap = googleMap
                        val location = LatLng(lat, lng)
                        mMap.addMarker(MarkerOptions()
                            .position(location)
                            .title(title)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                            .snippet(infoWindowText))
                        setInfoWindow()
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,5f))

                    }



                }
            },
            Response.ErrorListener { error ->
                Log.d("JSON",error.toString())
            }
        )
        queue.add(jsonObjectRequest)



    }

}
