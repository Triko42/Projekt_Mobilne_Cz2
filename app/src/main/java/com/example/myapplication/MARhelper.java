package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MARhelper extends SQLiteOpenHelper {

    static String TABLE_TYP="Oceny";
    static String MAR_ID="_id";
    static String MAR_STU_ID="stu_id";
    static String MAR_IM="imie";
    static String MAR_NAZW="nazwisko";
    static String MAR_SUBJECT="subject";
    static String MAR_K1="k1";
    static String MAR_K2="k2";
    static String MAR_EGZ="egz";

    private static String DB_NAZWA="OCENY.db";
    private static String CREATE_TABLE_MARKS="CREATE TABLE IF NOT EXISTS "+TABLE_TYP+"("+
            MAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            MAR_STU_ID + " INTEGER,"+
            MAR_IM +" TEXT,"+
            MAR_NAZW+" TEXT,"+
            MAR_SUBJECT+" TEXT,"+
            MAR_K1 + " INTEGER," +
            MAR_K2 + " INTEGER,"+
            MAR_EGZ + " INTEGER" +")";

    private static int DB_WERSJA=1;

    public MARhelper(Context context) {
        super(context, DB_NAZWA, null, DB_WERSJA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MARKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYP);
        onCreate(db);
    }
}
