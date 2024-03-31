package com.example.lab5

import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.TimePicker
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.example.lab5.R
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var sourceSpinner: Spinner
    private lateinit var destinationSpinner: Spinner
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var toggleButton: ToggleButton
    private lateinit var opText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        sourceSpinner = findViewById(R.id.sourceSpinner)
        destinationSpinner = findViewById(R.id.destinationSpinner)
        datePicker = findViewById(R.id.datePicker)
        timePicker = findViewById(R.id.timePicker1)
        toggleButton = findViewById(R.id.toggleButton)
        opText = findViewById(R.id.opText)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            displayTicketDetails()
        }

        val clearButton = findViewById<Button>(R.id.clearButton)
        clearButton.setOnClickListener {
            clearFields()
        }
    }

    private fun displayTicketDetails() {
        val source = sourceSpinner.selectedItem.toString()
        val destination = destinationSpinner.selectedItem.toString()
        val day = datePicker.dayOfMonth
        val month = datePicker.month + 1 // Month is zero-based
        val year = datePicker.year
        val journeyDate = "$day/$month/$year"
        val hour = timePicker.hour
        val minute = timePicker.minute
        val journeyTime = String.format("%02d:%02d", hour, minute)
        val tripType = if (toggleButton.isChecked) "Round-trip" else "One-way"

        // Construct the text to be displayed
        val displayText = "Source: $source\nDestination: $destination\nDate: $journeyDate\nTime: $journeyTime\nTrip Type: $tripType"

        // Set the text to the EditText
        opText.setText(displayText)
    }

    private fun clearFields() {
        sourceSpinner.setSelection(0)
        destinationSpinner.setSelection(0)
        val calendar = Calendar.getInstance()
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null)
        toggleButton.isChecked = false
        timePicker.hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        timePicker.minute = Calendar.getInstance().get(Calendar.MINUTE)
        opText.setText("") // Clear the EditText
    }
}
