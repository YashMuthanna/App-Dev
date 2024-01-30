package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOk = findViewById<Button>(R.id.btn2)
        btnOk.setOnClickListener {
            val name1 = findViewById<TextInputEditText>(R.id.input1)
            val txt = name1.text.toString().trim()
            val msg = findViewById<EditText>(R.id.editTextText)
            msg.visibility = View.VISIBLE
            val a = "Hello ".plus(txt)
            val editableTxt = Editable.Factory.getInstance().newEditable(a)
            msg.text = editableTxt
        }
    }
}
