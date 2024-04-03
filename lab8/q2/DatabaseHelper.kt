package com.example.lab8q2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_COST REAL)"
        db.execSQL(query)
        db.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME, $COLUMN_COST) VALUES ('Apples', 2.99)")
        db.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME, $COLUMN_COST) VALUES ('Bananas', 1.99)")
        db.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME, $COLUMN_COST) VALUES ('Oranges', 3.49)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "grocery.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "grocery"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_COST = "cost"
    }
}

