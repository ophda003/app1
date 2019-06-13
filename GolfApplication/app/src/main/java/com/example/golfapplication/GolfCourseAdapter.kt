package com.example.golfapplication

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.golfcourse_item.view.*
import org.json.JSONArray
import org.json.JSONObject

class GolfCourseAdapter(private val courses: JSONArray) : RecyclerView.Adapter<GolfCourseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.golfcourse_item, parent, false)
        return ViewHolder(view)
    }



    override fun getItemCount(): Int {
        return courses.length()
    }


    // bind data to UI View Holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course: JSONObject = courses.getJSONObject(position)
        holder.courseTextView.text = course["course"].toString()
        holder.addressTextView.text = course["address"].toString()
        holder.phoneTextView.text = course["phone"].toString()
        holder.emailTextView.text = course["email"].toString()

        Glide.with(holder.imageView.context).load("http://ptm.fi/materials/golfcourses/"+course["image"]).into(holder.imageView)

    }

    // View Holder class to hold UI views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val courseTextView: TextView = view.courseTextView
        val addressTextView: TextView = view.addressTextView
        val phoneTextView: TextView = view.phoneTextView
        val emailTextView: TextView = view.emailTextView
        val imageView: ImageView = view.imageView
        init {
            itemView.setOnClickListener {
                val intent = Intent(view.context, CourseActivity::class.java)
                intent.putExtra("courses",courses[adapterPosition].toString())
                view.context.startActivity(intent)

            }
        }


    }

}
