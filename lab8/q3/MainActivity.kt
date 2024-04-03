package com.example.lab8q3

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lab8q3.DatabaseHelper

class MainActivity : AppCompatActivity() {
    private var dbHelper: DatabaseHelper? = null
    private var adapter: ArrayAdapter<String>? = null
    private var movieNames: ArrayList<String>? = null
    private var tableLayout: TableLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        movieNames = ArrayList()
        adapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,
            movieNames!!
        )

        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

        tableLayout = findViewById(R.id.tableLayout)

        updateMovieList()

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id -> displayMovieDetails(position) }
    }

    fun addReview(view: View?) {
        // Show dialog to add a movie review
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Movie Review")
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_review, null)
        builder.setView(dialogView)

        val etMovieName = dialogView.findViewById<EditText>(R.id.etMovieName)
        val etYear = dialogView.findViewById<EditText>(R.id.etYear)
        val etRating = dialogView.findViewById<EditText>(R.id.etRating)

        builder.setPositiveButton(
            "Add"
        ) { dialog, which ->
            val movieName = etMovieName.text.toString()
            val year = etYear.text.toString().toIntOrNull() ?: 0
            val rating = etRating.text.toString().toIntOrNull() ?: 0

            if (movieName.isNotEmpty() && year > 0 && rating in 1..5) {
                val result = dbHelper?.addReview(movieName, year, rating)
                if (result == true) {
                    Toast.makeText(this@MainActivity, "Review added successfully", Toast.LENGTH_SHORT).show()
                    updateMovieList()
                } else {
                    Toast.makeText(this@MainActivity, "Failed to add review", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@MainActivity, "Please fill in all fields with valid data", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancel", null)
        builder.show()
    }

    private fun updateMovieList() {
        // Update the movie list
        movieNames?.clear()
        dbHelper?.reviews?.use { cursor ->
            while (cursor.moveToNext()) {
                val movieName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MOVIE_NAME))
                movieNames?.add(movieName)
            }
        }
        adapter?.notifyDataSetChanged()
    }

    private fun displayMovieDetails(position: Int) {
        // Display the details of the selected movie
        tableLayout?.removeAllViews()
        val movieName = movieNames?.get(position) ?: return

        dbHelper?.reviews?.use { cursor ->
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MOVIE_NAME))
                if (name == movieName) {
                    val year = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_YEAR))
                    val rating = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_RATING))

                    val row = TableRow(this)
                    val textName = TextView(this)
                    textName.text = movieName
                    row.addView(textName)

                    val textYear = TextView(this)
                    textYear.text = year.toString()
                    row.addView(textYear)

                    val textRating = TextView(this)
                    textRating.text = rating.toString()
                    row.addView(textRating)

                    tableLayout?.addView(row)
                }
            }
        }
    }
}

