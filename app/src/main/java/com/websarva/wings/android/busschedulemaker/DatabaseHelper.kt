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

        val sb2 = StringBuilder()
        sb2.append("CREATE TABLE timetable (")
        sb2.append("_id INTEGER PRIMARY KEY,")
        sb2.append("1 TEXT,")
        sb2.append("2 TEXT,")
        sb2.append("3 TEXT,")
        sb2.append("4 TEXT,")
        sb2.append("5 TEXT,")
        sb2.append("6 TEXT,")
        sb2.append("7 TEXT,")
        sb2.append("8 TEXT,")
        sb2.append("9 TEXT,")
        sb2.append("10 TEXT,")
        sb2.append("11 TEXT,")
        sb2.append("12 TEXT,")
        sb2.append("13 TEXT,")
        sb2.append("14 TEXT,")
        sb2.append("15 TEXT,")
        sb2.append("16 TEXT,")
        sb2.append("17 TEXT,")
        sb2.append("18 TEXT,")
        sb2.append("19 TEXT,")
        sb2.append("20 TEXT,")
        sb2.append("21 TEXT,")
        sb2.append("22 TEXT,")
        sb2.append("23 TEXT,")
        sb2.append("24 TEXT,")
        sb.append(");")
        val sql2 = sb2.toString()

        db.execSQL(sql)
        db.execSQL(sql2)

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
}

