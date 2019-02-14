package com.example.vianelis.project4.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "player.db";
    private static final String TABLE_NAME = "player";
    private static final String COL_1 = "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL";
    private static final String COL_2 = "name TEXT NOT NULL";
    private static final String COL_3 = "wins TEXT";
    private static final String COL_4 = "losses TEXT";
    private static final String COL_5 = "ties TEXT";

    private static final String INSERT_PLAYER_ONE = "INSERT INTO player VALUES(1, 'Michael', '3', '9', '8')";
    private static final String INSERT_PLAYER_TWO = "INSERT INTO player VALUES(2, 'Sonya', '4', '9', '6')";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + COL_1 + "," + COL_2 + "," + COL_3 + "," + COL_4 + "," + COL_5 + ")");

        db.execSQL(INSERT_PLAYER_ONE);
        db.execSQL(INSERT_PLAYER_TWO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}