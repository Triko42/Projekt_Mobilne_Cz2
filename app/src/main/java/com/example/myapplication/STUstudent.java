package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class STUstudent {

    STUhelper dbHelper;
    SQLiteDatabase dbStudent;

    private String Student_RECORS[];

    public STUstudent(Context context) {
        dbHelper = new STUhelper(context);
    }

    public void open() throws SQLException {
        dbStudent = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbStudent.close();
    }

    public boolean addStudent(String imie, String nazwisko) {
        ContentValues cv = new ContentValues();
        cv.put(STUhelper.STU_IMIE, imie);
        cv.put(STUhelper.STU_NAZWISKO, nazwisko);
        return dbStudent.insert(STUhelper.TABLE_TYP, null, cv) != -1;
    }

    public Cursor getStudent() {
        return dbStudent.query(STUhelper.TABLE_TYP, Student_RECORS, null, null, null, null, STUhelper.STU_NAZWISKO + " ASC");
    }

    public boolean deleteStudent(String id) {
        return dbStudent.delete(STUhelper.TABLE_TYP, STUhelper.STU_ID + "=?", new String[]{id}) > 0;
    }
}