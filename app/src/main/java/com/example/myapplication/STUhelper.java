package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * The type STUhelper.
 */
public class STUhelper extends SQLiteOpenHelper {

    /**
     * The Table name.
     */
    static String TABLE_TYPE ="Studenci";
    /**
     * The Students id.
     */
    static String STU_ID="_id";
    /**
     * The Students name.
     */
    static String STU_NAME ="imie";
    /**
     * The Stu nazwisko.
     */
    static String STU_LAST_NAME ="nazwisko";
    /**
     * The Stu przedmiot.
     */
    static String STU_SUBJECT ="przedmiot";

    private static String DB_NAME ="STUDENCI.db";
    private static String CREATE_TABLE_STUDENT="CREATE TABLE IF NOT EXISTS "+ TABLE_TYPE +"("+ STU_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ STU_NAME +" TEXT,"+ STU_LAST_NAME +" TEXT"+")";
    private static int DB_VER =1;

    /**
     * Instantiates a new STUhelper.
     * @param context the context
     */
    public STUhelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    /**
     * onCreate override SQLite method
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    /**
     * onUpgrade override SQLite method
     * @param db
     * @param oldV
     * @param newV
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE);
        onCreate(db);
    }
}
