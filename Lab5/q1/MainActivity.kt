package com.example.lab5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var selectedItem: String? = null // Declare selectedItem outside onCreate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Move setContentView() here

        val submit = findViewById<Button>(R.id.submitButton)
        val RC = findViewById<EditText>(R.id.vehicleRC)
        val Number = findViewById<EditText>(R.id.vehicleNumber)

        // access the items of the list
        val languages = resources.getStringArray(R.array.vehicles)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    selectedItem = resources.getStringArray(R.array.vehicles)[position]

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        var string1 = ""

        submit.setOnClickListener {
            val details = findViewById<TextView>(R.id.textView3)
            string1 = "VehicleType: $selectedItem \n RC Number: ${RC.text} \n Vehicle Num: ${Number.text}"
            details.text = string1
        }

        val submit2 = findViewById<Button>(R.id.submitButton2)
        submit2.setOnClickListener {
            Toast.makeText(this,
                string1,
                Toast.LENGTH_LONG).show()
        }
    }
}
