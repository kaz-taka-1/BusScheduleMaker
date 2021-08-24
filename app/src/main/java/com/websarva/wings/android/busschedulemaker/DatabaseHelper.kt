package com.websarva.wings.android.busschedulemaker

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {

        private const val DATABASE_NAME = "busschedule.db"

        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {

        val sb = StringBuilder()
        sb.append("CREATE TABLE busschedules (")
        sb.append("_id INTEGER PRIMARY KEY,")
        sb.append("title TEXT,")
        sb.append("destination TEXT")
        sb.append("bus_stop TEXT")
        sb.append("first_train_time TEXT")
        sb.append("last_train_time TEXT")
        sb.append(");")
        val sql = sb.toString()

        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
}
