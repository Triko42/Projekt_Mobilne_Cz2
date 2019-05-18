package com.example.projekt_mobilne_cz2;

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
    public void close(){ dbStudent.close(); }

    public void addStudent(String imie, String nazwisko){
        ContentValues cv = new ContentValues();
        cv.put(STUhelper.STU_IMIE, imie);
        cv.put(STUhelper.STU_NAZWISKO, nazwisko);
        dbStudent.insert(STUhelper.TABLE_TYP, null, cv);
    }
    public Cursor getStudent(){
        return dbStudent.query(STUhelper.TABLE_TYP,
                Student_RECORS, null, null, null, null,
                STUhelper.STU_IMIE);
    }
    public void deleteStudent(String id) {
        dbStudent.delete(STUhelper.TABLE_TYP,
                STUhelper.STU_ID + " = " + id, null);
    }

}
