//для хранения констант
package com.example.myapplication.db;

public class MyConstants {
    //название таблицы
    public static final String TABLE_NAME = "kids";
    //id каждой новой записи
    public static final String _ID = "uuid";
    //название колонок
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_FIO_CHILD = "fio_child";
    public static final String COLUMN_NUMBER_LC = "number_lc";
    //название базы данных
    public static final String DATABASE_NAME = "database_name.db";
    //версия базы данных
    public static final int DB_VERSION = 2;
    //строка для добавления в БД
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," +
            COLUMN_NUMBER + " TEXT," +
            COLUMN_FIO_CHILD + " TEXT," +
            COLUMN_NUMBER_LC + " TEXT)";
    //для удаления таблицы
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
