// include this line in manifest <uses-permission android:name="android.permission.INTERNET" />
package com.example.app1

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btnOpenUrl)
        btn1.setOnClickListener {
            val txt1 = findViewById<EditText>(R.id.editTextUrl)
            val str = txt1.text.toString().trim()
            openUrl(str)
        }
    }


    private fun openUrl(url: String) {
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)

        intent.setPackage("com.android.chrome")

        try {
            startActivity(intent)
        } catch (e: Exception) {
            // Log the exception
            // e.printStackTrace()
            showToast("Error opening the URL")
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
