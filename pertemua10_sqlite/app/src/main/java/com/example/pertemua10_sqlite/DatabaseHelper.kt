package com.example.pertemua10_sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME = "myDb"
        val DATABASE_VERSION = 1
    }
    override fun onCreate(sql: SQLiteDatabase?) {
        sql?.execSQL(DatabaseContract.Penduduk.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(sql: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        sql?.execSQL(DatabaseContract.Penduduk.SQL_DELETE_TABLE)
        onCreate(sql)
    }

}