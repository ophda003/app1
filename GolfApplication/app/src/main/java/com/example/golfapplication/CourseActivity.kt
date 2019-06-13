package com.example.golfapplication

import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import android.widget.Toolbar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.course_activity.*
import kotlinx.android.synthetic.main.golfcourse_item.addressTextView
import kotlinx.android.synthetic.main.golfcourse_item.emailTextView
import kotlinx.android.synthetic.main.golfcourse_item.phoneTextView
import org.json.JSONObject



class CourseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.course_activity)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val courseString = bundle.getString("courses")
            val course = JSONObject(courseString)
            addressTextView.text = course["address"].toString()
            phoneTextView.text = course["phone"].toString()
            emailTextView.text = course["email"].toString()
            textTextView.text = course["text"].toString()
            webLink.text = course["web"].toString()
            locationLink.text = course["address"].toString()
            title = course["course"].toString()
            Glide.with(this).load("http://ptm.fi/materials/golfcourses/"+course["image"]).into(imageView)
            goToMapTextView.setOnClickListener {
                val lat = course["lat"].toString().toDouble()
                val lng = course["lng"].toString().toDouble()

                val location = Uri.parse("geo:$lat,$lng")
                val mapIntent = Intent(Intent.ACTION_VIEW, location)

                val activities: List<ResolveInfo> = packageManager.queryIntentActivities(mapIntent, 0)
                val isIntentSafe: Boolean = activities.isNotEmpty()

                if (isIntentSafe) {
                    startActivity(mapIntent)
                } else {
                    Toast.makeText(this, "There is no activity to handle map intent!", Toast.LENGTH_LONG).show();
                }

            }
        }
    }
}
