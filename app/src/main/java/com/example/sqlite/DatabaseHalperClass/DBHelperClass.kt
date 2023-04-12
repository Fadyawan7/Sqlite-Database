package com.example.sqlite.DatabaseHalperClass

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlite.ViewModal

class DBHelperClass(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {


        val CREATE_CONTACTS_TABLE = "CREATE TABLE $TABLE_CONTACTS ($KEY_ID INTEGER PRIMARY KEY, $KEY_NAME TEXT )"

        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "FactsDatabase"
        private val TABLE_CONTACTS = "FactsTable"
        private val KEY_ID = "_id"
        private val KEY_NAME = "name"
    }



    fun addData(text: ViewModal): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_NAME, text.Text)
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }



    @SuppressLint("Range", "Recycle")
    fun viewData(): ArrayList<ViewModal> {

        val Textlist: ArrayList<ViewModal> = ArrayList<ViewModal>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"

        val db = this.readableDatabase
        val cursor: Cursor

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        if (cursor.moveToLast()) {
            do {
                val  id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val name = cursor.getString(cursor.getColumnIndex(KEY_NAME))

                val facts = ViewModal(id,name)
                Textlist.add(facts)
            } while (cursor.moveToPrevious())
        }
        return Textlist
    }
}

