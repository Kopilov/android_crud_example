package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.logging.Logger;

public class MyDbHelper extends SQLiteOpenHelper {
    private final Context mContext;

    public MyDbHelper(@Nullable Context context) {
        super(context, MyConstants.DATABASE_NAME, null, MyConstants.DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MyConstants.TABLE_STRUCTURE);
        Logger.getLogger("MyDbHelper").info("База создана");
        Toast.makeText(mContext, "База создана", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MyConstants.DROP_TABLE);
        Logger.getLogger("MyDbHelper").info("Таблица удалена");
        Toast.makeText(mContext, "Таблица удалена", Toast.LENGTH_SHORT).show();
    }
}
