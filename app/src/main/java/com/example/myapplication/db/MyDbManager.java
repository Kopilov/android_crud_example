package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    public void insertToDb(String number, String fio_child, String number_lc) {
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.COLUMN_NUMBER, number);
        cv.put(MyConstants.COLUMN_FIO_CHILD, fio_child);
        cv.put(MyConstants.COLUMN_NUMBER_LC, number_lc);
        db.insert(MyConstants.TABLE_NAME, null, cv);

    }
    //для открывания БД (считывание)
    public List<String> getFromDb() {
        List<String> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String number = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_NUMBER));
            String fio_child = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_FIO_CHILD));
            String number_lc = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_NUMBER_LC));
            tempList.add(number);
            tempList.add(fio_child);
            tempList.add(number_lc);
        }
        cursor.close();
        return tempList;
    }
    //для закрытия БД
    public void closeDb() {
        myDbHelper.close();
    }
}
