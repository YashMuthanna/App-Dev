package com.example.lab8q2

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var grocerySpinner: Spinner
    private lateinit var addButton: Button
    private lateinit var totalTextView: TextView
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase
    private lateinit var groceryItems: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private var totalCost = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        grocerySpinner = findViewById(R.id.grocerySpinner)
        addButton = findViewById(R.id.addButton)
        totalTextView = findViewById(R.id.totalTextView)

        databaseHelper = DatabaseHelper(this)
        database = databaseHelper.writableDatabase

        groceryItems = mutableListOf()
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, groceryItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        grocerySpinner.adapter = adapter

        loadGroceryItems()

        addButton.setOnClickListener {
            addSelectedItem()
        }

        grocerySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Handle item selection
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }

    private fun loadGroceryItems() {
        val cursor = database.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME}", null)
        groceryItems.clear()
        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME))
            val cost = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_COST))
            groceryItems.add("$name - $$cost")
        }
        adapter.notifyDataSetChanged()
        cursor.close()
    }

    private fun addSelectedItem() {
        val selectedItem = grocerySpinner.selectedItem.toString()
        val parts = selectedItem.split(" - ")
        val cost = parts[1].substring(1).toDouble() // Remove the $ sign
        totalCost += cost
        totalTextView.text = "Total Cost: $$totalCost"
    }
}
