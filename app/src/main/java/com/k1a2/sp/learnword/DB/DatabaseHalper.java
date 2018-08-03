package com.k1a2.sp.learnword.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHalper extends SQLiteOpenHelper {

    private Context context;
    private String tableName;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHalper(Context context, String tableName) {
        super(context, "word.db", null, 1);
        this.context = context;
        this.tableName = tableName;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Cursor c = sqLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type=" + "'"+ tableName +"'", null);
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
