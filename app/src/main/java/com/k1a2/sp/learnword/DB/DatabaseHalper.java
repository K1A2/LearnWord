package com.k1a2.sp.learnword.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.k1a2.sp.learnword.View.RecyclerView.WordListItem;

import java.util.ArrayList;

public class DatabaseHalper extends SQLiteOpenHelper {

    private Context context;

    public DatabaseHalper(Context context) {
        super(context, DatabaseKey.KEY_DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE `Type` ( `id` INTEGER NOT NULL, `Name` TEXT NOT NULL )";
        sqLiteDatabase.execSQL(sql);
        String insert_sql = "insert into Type values(0, '명사'), (1,'대명사'), (2, '동사'), (3, '형용사'), (4, '부사'), (5, '전치사'), (6, '접속사'), (7, '감탄사')";
        sqLiteDatabase.execSQL(insert_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int getTableCount() {
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            int count = 0;
            String SQL_GET_ALL_TABLES = "SELECT count(*) FROM sqlite_master WHERE type = 'table' AND name != 'android_metadata' AND name != 'sqlite_sequence' AND name != 'Type'";
            Cursor cursor = sqLiteDatabase.rawQuery(SQL_GET_ALL_TABLES, null);
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                count++;
            }
            sqLiteDatabase.close();
            return count;
        } catch (Exception e) {
            return -1;
        }
    }

    public ArrayList<WordListItem> getTableName() {
        try {
            ArrayList<WordListItem> array_name = new ArrayList<WordListItem>();
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String SQL_GET_ALL_TABLES = "SELECT name FROM sqlite_master WHERE type='table' AND name != 'android_metadata' AND name != 'sqlite_sequence' AND name != 'Type'";
            Cursor cursor = sqLiteDatabase.rawQuery(SQL_GET_ALL_TABLES, null);
            cursor.moveToFirst();
            int count = 0;
            while (cursor.getCount() > 0&&!(cursor.getCount() == count)) {
                Cursor row_cursor = sqLiteDatabase.rawQuery("SELECT count(*) FROM `" + cursor.getString(0) + "`", null);
                int row_count = row_cursor.getCount() - 1;
                array_name.add(new WordListItem(cursor.getString(0), row_count));
                cursor.moveToNext();
                count++;
            }
            cursor.close();
            sqLiteDatabase.close();
            return array_name;
        } catch (Exception e) {
            return null;
        }
    }

    public void createTable(String tableName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "CREATE TABLE `" + tableName + "` ( `Name` TEXT NOT NULL, `Mean` TEXT NOT NULL, `Type` INTEGER NOT NULL, PRIMARY KEY(`Name`,`Mean`) )";
        sqLiteDatabase.execSQL(sql);
    }
}
