package com.example.lab8

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var displayTextView: TextView
    private lateinit var editIdEditText: EditText
    private lateinit var editNameEditText: EditText
    private lateinit var editPhoneEditText: EditText
    private lateinit var editEmailEditText: EditText
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.nameEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        emailEditText = findViewById(R.id.emailEditText)
        displayTextView = findViewById(R.id.displayTextView)
        editIdEditText = findViewById(R.id.editIdEditText)
        editNameEditText = findViewById(R.id.editNameEditText)
        editPhoneEditText = findViewById(R.id.editPhoneEditText)
        editEmailEditText = findViewById(R.id.editEmailEditText)

        // Initialize the database
        database = openOrCreateDatabase("ContactBook", MODE_PRIVATE, null)
        database.execSQL("CREATE TABLE IF NOT EXISTS contacts (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT, email TEXT)")

        displayContacts()
    }

    fun saveContact(view: View) {
        val name = nameEditText.text.toString().trim()
        val phone = phoneEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }
        val contentValues = ContentValues().apply {
            put("name", name)
            put("phone", phone)
            put("email", email)
        }
        database.insert("contacts", null, contentValues)
        clearFields()
        displayContacts()
    }

    private fun clearFields() {
        nameEditText.text.clear()
        phoneEditText.text.clear()
        emailEditText.text.clear()
        editIdEditText.text.clear()
        editNameEditText.text.clear()
        editPhoneEditText.text.clear()
        editEmailEditText.text.clear()
    }

    private fun displayContacts() {
        val cursor = database.rawQuery("SELECT * FROM contacts", null)
        val builder = StringBuilder()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val phone = cursor.getString(2)
            val email = cursor.getString(3)
            builder.append("ID: $id\n")
            builder.append("Name: $name\n")
            builder.append("Phone: $phone\n")
            builder.append("Email: $email\n\n")
        }
        cursor.close()
        displayTextView.text = builder.toString()
    }

    fun editContact(view: View) {
        val id = editIdEditText.text.toString().trim().toIntOrNull()
        val name = editNameEditText.text.toString().trim()
        val phone = editPhoneEditText.text.toString().trim()
        val email = editEmailEditText.text.toString().trim()
        if (id == null || name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }
        val contentValues = ContentValues().apply {
            put("name", name)
            put("phone", phone)
            put("email", email)
        }
        database.update("contacts", contentValues, "id=?", arrayOf(id.toString()))
        clearFields()
        displayContacts()
    }
}


