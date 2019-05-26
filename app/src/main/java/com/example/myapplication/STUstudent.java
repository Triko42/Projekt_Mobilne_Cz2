package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class STUstudent {

    STUhelper dbHelper;
    PREhelper preHelper;
    SQLiteDatabase dbStudent;

    private String Student_RECORS[];
    private String Presistance_RECORS[];

    public STUstudent(Context context) {
        dbHelper = new STUhelper(context);
        preHelper = new PREhelper(context);
    }

    public void open() throws SQLException {
        dbStudent = dbHelper.getWritableDatabase();
        preHelper.onCreate(dbStudent);
        dbHelper.onCreate(dbStudent);
    }

    public void close() {
        dbStudent.close();
    }

    public long addStudent(String imie, String nazwisko) {
        ContentValues cv = new ContentValues();
        cv.put(STUhelper.STU_IMIE, imie);
        cv.put(STUhelper.STU_NAZWISKO, nazwisko);
        return dbStudent.insert(STUhelper.TABLE_TYP, null, cv);
    }

    public Cursor getStudent() {
        ContentValues cv = new ContentValues();
        cv.put(STUhelper.STU_IMIE, "sads");
        cv.put(STUhelper.STU_NAZWISKO, "asdad");
        dbStudent.insert(STUhelper.TABLE_TYP, null, cv);
        return dbStudent.query(STUhelper.TABLE_TYP, Student_RECORS, null, null, null, null, STUhelper.STU_NAZWISKO + " ASC");
    }
    public Cursor getStudentForSubject(String subject) {
        String[] whereArgs = new String[] {
                subject,
        };
        return dbStudent.query(STUhelper.TABLE_TYP, Student_RECORS, STUhelper.STU_PRZEDMIOT + " = ?", whereArgs, null, null, STUhelper.STU_NAZWISKO + " ASC");
    }

    public boolean deleteStudent(String id) {
        return dbStudent.delete(STUhelper.TABLE_TYP, STUhelper.STU_ID + "=?", new String[]{id}) > 0;
    }

    public boolean addPresRec(long stud_id, String sub, String name, String surname) {
        ContentValues cv = new ContentValues();
        cv.put(PREhelper.PRE_STU_ID, stud_id);
        cv.put(PREhelper.PRE_IM, name);
        cv.put(PREhelper.PRE_NAZ, surname);
        cv.put(PREhelper.PRE_SUBJECT, sub);
        cv.put(PREhelper.PRE_1, "O");
        cv.put(PREhelper.PRE_2, "O");
        cv.put(PREhelper.PRE_3, "O");
        cv.put(PREhelper.PRE_4, "O");
        cv.put(PREhelper.PRE_5, "O");
        cv.put(PREhelper.PRE_6, "O");
        return dbStudent.insert(PREhelper.TABLE_TYP, null, cv) != -1;
    }

    public Cursor getAllPres() {
        return dbStudent.query(PREhelper.TABLE_TYP, Presistance_RECORS, null, null, null, null, null);
    }
    public Cursor getPresForSubject(String subject) {
        String[] whereArgs = new String[] {
                subject,
        };
        return dbStudent.query(PREhelper.TABLE_TYP, Presistance_RECORS, PREhelper.PRE_SUBJECT + " = ?", whereArgs, null, null, null);

    }
    public Cursor getPresById(long id) {
        String[] whereArgs = new String[] {
                String.valueOf(id),
        };
        return dbStudent.query(PREhelper.TABLE_TYP, Presistance_RECORS, PREhelper.PRE_ID + " = ?", whereArgs, null, null, null);
    }

    public int updatePres(long id, String[] args){
        ContentValues cv = new ContentValues();
        cv.put("o1",args[0]);
        cv.put("o2",args[1]);
        cv.put("o3",args[2]);
        cv.put("o4",args[3]);
        cv.put("o5",args[4]);
        cv.put("o6",args[5]);

        return dbStudent.update(PREhelper.TABLE_TYP, cv, "_id="+id, null);
    }

    public boolean deletePr(String id) {
        return dbStudent.delete(PREhelper.TABLE_TYP, PREhelper.PRE_ID + "=?", new String[]{id}) > 0;
    }

}