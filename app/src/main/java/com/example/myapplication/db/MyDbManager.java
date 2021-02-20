package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Kid;

import java.util.ArrayList;
import java.util.List;

public class MyDbManager {
    private Context context;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    public MyDbManager(Context context) {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }
    //для открывания БД
    public void openDb() {
        db = myDbHelper.getWritableDatabase();
    }
    //для открывания БД (запись)
    public void insertToDb(Kid kid) {
        db.execSQL("INSERT INTO " + MyConstants.TABLE_NAME + "(" +
                MyConstants._ID + ", " +
                MyConstants.COLUMN_NUMBER + ", " +
                MyConstants.COLUMN_F_CHILD + ", " +
                MyConstants.COLUMN_I_CHILD + ", " +
                MyConstants.COLUMN_O_CHILD + ", " +
                MyConstants.COLUMN_NUMBER_LC +
                ") values (?, (SELECT IFNULL(MAX(" + MyConstants.COLUMN_NUMBER + "), 0) + 1 FROM " + MyConstants.TABLE_NAME + "), ?, ?, ?, ?)",
                new String[]{kid.getUuid(), kid.getSurname(), kid.getName(), kid.getPatronymic(), kid.getNumberLC()});

    }
    //для открывания БД (считывание)
    public List<Kid> getFromDb() {
        List<Kid> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, MyConstants.COLUMN_HIDDEN +" = 0", null, null, null, null);
        while (cursor.moveToNext()) {
            String uuid = cursor.getString(cursor.getColumnIndex(MyConstants._ID));
            int number = cursor.getInt(cursor.getColumnIndex(MyConstants.COLUMN_NUMBER));
            String surname = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_F_CHILD));
            String name = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_I_CHILD));
            String patronymic = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_O_CHILD));
            String numberLC = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_NUMBER_LC));
            Kid kid = new Kid(uuid, number, surname, name, patronymic, numberLC);
            tempList.add(kid);
        }
        cursor.close();
        return tempList;
    }

    public void removeFromDb(String uuid) {
        db.execSQL("UPDATE " + MyConstants.TABLE_NAME + " set " + MyConstants.COLUMN_HIDDEN + " = 1 where " + MyConstants._ID + " = ?", new String[]{uuid});
    }
    //для закрытия БД
    public void closeDb() {
        myDbHelper.close();
    }
}
