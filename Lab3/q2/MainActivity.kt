package com.example.lab3q2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val num1 = findViewById<EditText>(R.id.num1)
            val txt1 = num1.text.toString()
            val num2 = findViewById<EditText>(R.id.num2)
            val txt2 = num2.text.toString()
            val num3 = findViewById<EditText>(R.id.num1)
            val txt3 = num3.text.toString()

            val res = findViewById<TextView>(R.id.res)
            val finalTxt = "Lettuce x$txt1 \n Tomato x$txt2 \n Onion x$txt3"
            res.text = Editable.Factory.getInstance().newEditable(finalTxt)

        }
    }
}