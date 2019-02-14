package com.example.vianelis.project4.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdaptor {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DBAdaptor(Context context) {
        dbHelper = new DBHelper(context);
    }

    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWritableDB() {
        db = dbHelper.getWritableDatabase();
    }

    public boolean insertData(String name, String w, String l, String t) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("name", name);
            cv.put("wins", w);
            cv.put("losses", l);
            cv.put("ties", t);

            this.openWritableDB();
            db.insert("player", null, cv);
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public void updateData(String n, boolean w, boolean l, boolean t) {
        this.openWritableDB();

        if (w) {
            db.execSQL("UPDATE player SET wins = wins+1 WHERE name = '" + n + "'");
        }

        if (l) {
            db.execSQL("UPDATE player SET losses = losses+1 WHERE name = '" + n + "'");
        }

        if (t) {
            db.execSQL("UPDATE player SET ties = ties+1 WHERE name = '" + n + "'");
        }
    }

    public boolean deleteData(String name) {
        try {
            this.openWritableDB();
            db.execSQL("DELETE FROM player WHERE name = '" + name + "'");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Cursor getRow(int id) {
        String where = "_id = " + id;
        this.openReadableDB();

        Cursor c = db.query("player", null, where, null, null, null, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getAllRows() {
        this.openReadableDB();

        Cursor c = db.query("player", null, null, null, null, null, "wins DESC");
        c.moveToFirst();
        return c;
    }

    public void closeDB() {
        if (db != null) {
            db.close();
        }
    }
}