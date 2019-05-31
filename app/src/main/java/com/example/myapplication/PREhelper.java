package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * The type PREhelper.
 */
public class PREhelper extends SQLiteOpenHelper {

    /**
     * The Table attendance.
     */
    static String TABLE_TYP="Obecnosci";
    /**
     * The Pre id.
     */
    static String PRE_ID="_id";
    /**
     * The Pre student id.
     */
    static String PRE_STU_ID="stu_id";
    /**
     * The Pre name.
     */
    static String PRE_IM="imie";
    /**
     * The Pre last name.
     */
    static String PRE_NAZ="nazwisko";
    /**
     * The Pre subject.
     */
    static String PRE_SUBJECT="subject";
    /**
     * The Pre 1.
     */
    static String PRE_1="o1";
    /**
     * The Pre 2.
     */
    static String PRE_2="o2";
    /**
     * The Pre 3.
     */
    static String PRE_3="o3";
    /**
     * The Pre 4.
     */
    static String PRE_4="o4";
    /**
     * The Pre 5.
     */
    static String PRE_5="o5";
    /**
     * The Pre 6.
     */
    static String PRE_6="o6";

    private static String DB_NAZWA="OBECNOSCI.db";
    private static String CREATE_TABLE_PRES="CREATE TABLE IF NOT EXISTS "+TABLE_TYP+"" +"("+
            PRE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            PRE_STU_ID+" INTEGER," +
            PRE_IM +" TEXT,"+
            PRE_NAZ +" TEXT,"+
            PRE_SUBJECT+" TEXT,"+
            PRE_1+" TEXT," +
            PRE_2+" TEXT," +
            PRE_3+" TEXT," +
            PRE_4+" TEXT," +
            PRE_5+" TEXT," +
            PRE_6+" TEXT"+
            ")";
    private static int DB_WERSJA = 1;

    /**
     * Instantiates a new PREhelper.
     * @param context the context
     */
    public PREhelper(Context context) {
        super(context, DB_NAZWA, null, DB_WERSJA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYP);
        onCreate(db);
    }
}
