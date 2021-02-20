//для хранения констант
package com.example.myapplication.db;

public class MyConstants {
    //название таблицы
    public static final String TABLE_NAME = "kids";
    //id каждой новой записи
    public static final String _ID = "uuid";
    //название колонок
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_F_CHILD = "surname";
    public static final String COLUMN_I_CHILD = "name";
    public static final String COLUMN_O_CHILD = "patronymic";
    public static final String COLUMN_NUMBER_LC = "number_lc";
    public static final String COLUMN_HIDDEN = "is_hidden";
    //название базы данных
    public static final String DATABASE_NAME = "database_name.db";
    //версия базы данных
    public static final int DB_VERSION = 2;
    //строка для добавления в БД
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " TEXT PRIMARY KEY," +
            COLUMN_NUMBER + " INTEGER," +
            COLUMN_F_CHILD + " TEXT," +
            COLUMN_I_CHILD + " TEXT," +
            COLUMN_O_CHILD + " TEXT," +
            COLUMN_NUMBER_LC + " TEXT, " +
            COLUMN_HIDDEN + " INTEGER DEFAULT 0)";
    //для удаления таблицы
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
