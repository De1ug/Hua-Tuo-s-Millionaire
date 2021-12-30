package com.example.thelegendofhuatuo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper {
    private static final String dbName = "huatuo";
    private static final int dbVersion = 1;

    public SqlHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String table = "CREATE TABLE IF NOT EXISTS Log (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT NOT NULL," +
                "money TEXT NOT NULL" +
                ")";

        sqLiteDatabase.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String sql = "DROP TABLE Log";
        sqLiteDatabase.execSQL(sql);
    }

    public void clearAllData(SQLiteDatabase sqLiteDatabase) {
        final String sql = "DROP TABLE Log";
        sqLiteDatabase.execSQL(sql);

        String table = "CREATE TABLE IF NOT EXISTS Log (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT NOT NULL," +
                "money TEXT NOT NULL" +
                ")";

        sqLiteDatabase.execSQL(table);
    }
}
