package com.example.projekt_mobilne_cz2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class STUhelper extends SQLiteOpenHelper {

    static String TABLE_TYP="Studenci";
    static String STU_ID="id";
    static String STU_IMIE="imie";
    static String STU_NAZWISKO="nazwisko";

    private static String DB_NAZWA="STUDENCI.db";
    private static String CREATE_TABLE_STUDENT="CREATE TABLE "+TABLE_TYP+"("+ STU_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ STU_IMIE+" TEXT,"+STU_NAZWISKO+" TEXT"+")";
    private static int DB_WERSJA=1;

    public STUhelper(Context context) {
        super(context, DB_NAZWA, null, DB_WERSJA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYP);
        onCreate(db);
    }
}
