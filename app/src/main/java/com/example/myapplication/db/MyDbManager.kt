package com.example.myapplication.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.myapplication.Kid
import java.util.ArrayList

class MyDbManager(private val context: Context) {
    private val myDbHelper: MyDbHelper
    private var db: SQLiteDatabase? = null

    //для открывания БД
    fun openDb() {
        db = myDbHelper.writableDatabase
    }

    //для открывания БД (запись)
    fun insertToDb(kid: Kid) {
        db!!.execSQL(
"""
INSERT INTO ${MyConstants.TABLE_NAME} (
    ${MyConstants._ID},
    ${MyConstants.COLUMN_NUMBER},
    ${MyConstants.COLUMN_F_CHILD},
    ${MyConstants.COLUMN_I_CHILD},
    ${MyConstants.COLUMN_O_CHILD},
    ${MyConstants.COLUMN_NUMBER_LC}
) values (?, (SELECT IFNULL(MAX( ${MyConstants.COLUMN_NUMBER} ), 0) + 1 FROM ${MyConstants.TABLE_NAME}), ?, ?, ?, ?)
""" , arrayOf(kid.uuid, kid.surname, kid.name, kid.patronymic, kid.numberLC))
    }

    //для открывания БД (считывание)
    val fromDb: List<Kid>
        get() {
            val tempList: MutableList<Kid> = ArrayList()
            val cursor = db!!.query(MyConstants.TABLE_NAME, null, MyConstants.COLUMN_HIDDEN + " = 0", null, null, null, null)
            while (cursor.moveToNext()) {
                val uuid = cursor.getString(cursor.getColumnIndex(MyConstants._ID))
                val number = cursor.getInt(cursor.getColumnIndex(MyConstants.COLUMN_NUMBER))
                val surname = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_F_CHILD))
                val name = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_I_CHILD))
                val patronymic = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_O_CHILD))
                val numberLC = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_NUMBER_LC))
                val kid = Kid(uuid, number, surname, name, patronymic, numberLC)
                tempList.add(kid)
            }
            cursor.close()
            return tempList
        }

    fun removeFromDb(uuid: String) {
        db!!.execSQL("UPDATE ${MyConstants.TABLE_NAME} set ${MyConstants.COLUMN_HIDDEN} = 1 where ${MyConstants._ID} = ?", arrayOf(uuid))
    }

    //для закрытия БД
    fun closeDb() {
        myDbHelper.close()
    }

    init {
        myDbHelper = MyDbHelper(context)
    }
}
