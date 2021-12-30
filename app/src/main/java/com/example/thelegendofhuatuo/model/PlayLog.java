package com.example.thelegendofhuatuo.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thelegendofhuatuo.SqlHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class PlayLog {
    private static final String dbName = "huatuo";
    private static final int dbVersion = 1;
    private static String dbTable = "Log";
    private static SQLiteDatabase db;
    private static SqlHelper sqlHelper;

    public static void openDatabase(Context context) {
        sqlHelper = new SqlHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    public static void AddLog(int money) {
        if (db == null)
            return;

        ContentValues contentvalues = new ContentValues();
        contentvalues.put("date", getDateTime());
        contentvalues.put("money", String.valueOf(money));
        db.insert(dbTable, null, contentvalues);
    }

    public static void clearAllLog() {
        if (db == null)
            return;

        sqlHelper.clearAllData(db);
    }

    public static LogData getAllLog() {
        if (db == null)
            return null;

        LogData logData = new LogData();
        if (logData == null)
            return null;

        Cursor c = db.rawQuery("SELECT * FROM " + dbTable, null);
        if (c.getCount() <= 0)
            return null;

        logData.length = c.getCount();
        logData.id = new int[c.getCount()];
        logData.dateTime = new String[c.getCount()];
        logData.money = new String[c.getCount()];

        c.moveToFirst();

        for (int i = 0; i < c.getCount(); i++) {
            logData.id[i] = c.getInt(0);
            logData.dateTime[i] = c.getString(1);
            logData.money[i] = c.getString(2);
            c.moveToNext();
        }

        return logData;
    }

    private static String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8") );
        return sdf.format(new Date());
    }
}
