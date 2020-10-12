 package com.example.sob_info;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "sob_info.db";
    public static final String TB_NAME = "USER_NAME";
    public static final String CREATE_TABLE = "create table " + TB_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " +
    "NAME TEXT NOT NULL, PHONE TEXT UNIQUE NOT NULL, EMAIL TEXT NOT NULL, DOB TEXT NOT NULL, PASSWORD TEXT NOT NULL, NEXT TEXT NOT NULL," +
            " GENDER TEXT NOT NULL, QUALIFICATION TEXT NOT NULL, STATUS TEXT NOT NULL, STATE TEXT NOT NULL ); ";

    SQLiteDatabase db;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //INSERT USER TABLE INTO DATABASE
    public boolean insertIntoUserTable(String name, String phoneNumber, String email, String dob, String password, String next, String gender, String qualification, String status, String state){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("PHONE", phoneNumber);
        values.put("EMAIL", email);
        values.put("DOB", dob );
        values.put("PASSWORD", password);
        values.put("NEXT", next);
        values.put("GENDER", gender);
        values.put("QUALIFICATION", qualification);
        values.put("STATUS", status);
        values.put("STATE", state);

        long res = db.insert(TB_NAME, null, values);

        return res != -1;
    }

    //READ USER DETAILS FROM DATABASE
    public Cursor readUser(String phoneNumber){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projections = {"NAME", "PHONE", "EMAIL", "DOB", "PASSWORD", "NEXT", "GENDER", "QUALIFICATION", "STATUS", "STATE"};
        String[] argument = {phoneNumber};
        String selection = "PHONE LIKE ?";
        Cursor cursor = db.query(TB_NAME, projections, selection,  argument, null, null, null);
        return cursor;

    }

}
