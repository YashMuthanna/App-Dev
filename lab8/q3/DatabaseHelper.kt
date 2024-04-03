package com.example.lab8q3

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_MOVIE_NAME TEXT, " +
                "$COL_YEAR INTEGER, " +
                "$COL_RATING INTEGER)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addReview(movieName: String, year: Int, rating: Int): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_MOVIE_NAME, movieName)
        contentValues.put(COL_YEAR, year)
        contentValues.put(COL_RATING, rating)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    val reviews: Cursor
        get() {
            val db = this.writableDatabase
            return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        }

    companion object {
        private const val DATABASE_NAME = "MovieReview.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "reviews"
        const val COL_ID = "id"
        const val COL_MOVIE_NAME = "movie_name"
        const val COL_YEAR = "year"
        const val COL_RATING = "rating"
    }
}
