package com.example.employeesapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_employee.*
import org.json.JSONObject

class EmployeeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        val bundle: Bundle? = intent.extras;
        if (bundle != null) {
            val employeeString = bundle.getString("employee")
            val employee = JSONObject(employeeString)
            Glide.with(imageView.context).load(employee["image"]).into(imageView)
            nameTextView.text = "Name: " + employee["lastName"].toString() + " " + employee["firstName"].toString()
            emailTextView.text = "Email: " + employee["email"].toString()
            phoneTextView.text = "Phone: " + employee["phone"].toString()
            titleTextView.text = "Title: "+ employee["title"].toString()
            departmentTextView.text = "Department: " + employee["department"].toString()
        }
    }
}
