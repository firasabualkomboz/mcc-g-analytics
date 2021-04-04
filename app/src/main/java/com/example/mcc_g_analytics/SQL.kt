package com.example.mcc_g_analytics

import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQL (activity: Activity) :
    SQLiteOpenHelper(activity, DATABASE_NAME, null, DATABASE_VERSION) {

    private val db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(User.TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("Drop table if exists ${User.TABLE_NAME}")
        onCreate(db)
    }

    //==============================================================================================
    //DML

    fun insertPerson(pageName: String, time: String ): Boolean {
        val cv = ContentValues()
        cv.put(User.COL_NAME, pageName)
        cv.put(User.COL_TIME, time)
        return db.insert(User.TABLE_NAME, null, cv) > 0
    }

    fun getAllPerson(): ArrayList<User> {
        val data = ArrayList<User>()
        val c =
            db.rawQuery("select * from ${User.TABLE_NAME} order by ${User.COL_ID} desc", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val p = User(c.getInt(0), c.getString(1),  c.getString(2))
            data.add(p)
            c.moveToNext()
        }
        c.close()
        return data
    }



    companion object {
        val DATABASE_NAME = "Test"
        val DATABASE_VERSION = 1

    }
}