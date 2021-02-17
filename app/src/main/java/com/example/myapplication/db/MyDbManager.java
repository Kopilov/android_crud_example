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
    public List<List<String>> getFromDb() {
        List<List<String>> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            List<String> row = new ArrayList<>();
            String number = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_NUMBER));
            String fio_child = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_FIO_CHILD));
            String number_lc = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_NUMBER_LC));
            row.add(number);
            row.add(fio_child);
            row.add(number_lc);

            tempList.add(row);
        }
        cursor.close();
        return tempList;
    }

    public void removeFromDb(String number_lc) {
        db.delete(MyConstants.TABLE_NAME, MyConstants.COLUMN_NUMBER_LC + " = ?", new String[]{number_lc});
    }
    //для закрытия БД
    public void closeDb() {
        myDbHelper.close();
    }
}
