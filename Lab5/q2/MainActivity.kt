package com.example.lab5

import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.example.lab5.R
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var sourceSpinner: Spinner
    private lateinit var destinationSpinner: Spinner
    private lateinit var datePicker: DatePicker
    private lateinit var toggleButton: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        sourceSpinner = findViewById(R.id.sourceSpinner)
        destinationSpinner = findViewById(R.id.destinationSpinner)
        datePicker = findViewById(R.id.datePicker)
        toggleButton = findViewById(R.id.toggleButton)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            // No need to call displayTicketDetails() here
        }

        val clearButton = findViewById<Button>(R.id.clearButton)
        clearButton.setOnClickListener {
            clearFields()
        }
    }

    private fun clearFields() {
        sourceSpinner.setSelection(0)
        destinationSpinner.setSelection(0)
        val calendar = Calendar.getInstance()
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null)
        toggleButton.isChecked = false
    }
}
