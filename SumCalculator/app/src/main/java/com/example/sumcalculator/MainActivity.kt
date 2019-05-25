package com.example.sumcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculateSum (@Suppress("UNUSED_PARAMETER")view: View){
        val a = firstNumber.text.toString().toInt()
        val b = secondNumber.text.toString().toInt()
        val result = a + b
        resultTextView.text = resources.getString(R.string.result) + " $result"


    }
}
