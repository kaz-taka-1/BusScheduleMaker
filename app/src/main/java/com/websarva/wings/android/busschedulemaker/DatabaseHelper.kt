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
        sb.append("destination TEXT,")
        sb.append("busStop TEXT,")
        sb.append("firstTrainTime TEXT,")
        sb.append("lastTrainTime TEXT")
        sb.append(");")
        val sql = sb.toString()
        db.execSQL(sql)

        val sb2 = StringBuilder()
        sb2.append("CREATE TABLE timetable (")
        sb2.append("_id INTEGER PRIMARY KEY,")
        sb2.append("o1 TEXT,")
        sb2.append("o2 TEXT,")
        sb2.append("o3 TEXT,")
        sb2.append("o4 TEXT,")
        sb2.append("o5 TEXT,")
        sb2.append("o6 TEXT,")
        sb2.append("o7 TEXT,")
        sb2.append("o8 TEXT,")
        sb2.append("o9 TEXT,")
        sb2.append("o10 TEXT,")
        sb2.append("o11 TEXT,")
        sb2.append("o12 TEXT,")
        sb2.append("o13 TEXT,")
        sb2.append("o14 TEXT,")
        sb2.append("o15 TEXT,")
        sb2.append("o16 TEXT,")
        sb2.append("o17 TEXT,")
        sb2.append("o18 TEXT,")
        sb2.append("o19 TEXT,")
        sb2.append("o20 TEXT,")
        sb2.append("o21 TEXT,")
        sb2.append("o22 TEXT,")
        sb2.append("o23 TEXT,")
        sb2.append("o24 TEXT")
        sb2.append(");")
        val sql2 = sb2.toString()


        db.execSQL(sql2)

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
}

