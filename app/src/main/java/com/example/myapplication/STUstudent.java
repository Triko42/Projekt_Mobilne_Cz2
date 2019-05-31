package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * The type STUstudent.
 */
public class STUstudent {

    /**
     * The database helper.
     */
    STUhelper dbHelper;
    /**
     * The preHelper.
     */
    PREhelper preHelper;
    /**
     * The marHelper.
     */
    MARhelper marHelper;
    /**
     * The student database.
     */
    SQLiteDatabase dbStudent;

    private String Student_RECORS[];
    private String Presistance_RECORS[];
    private String Mark_RECORS[];

    /**
     * Instantiates a new STUstudent.
     * @param context the context
     */
    public STUstudent(Context context) {
        dbHelper = new STUhelper(context);
        preHelper = new PREhelper(context);
        marHelper=new MARhelper(context);
    }

    /**
     * Open SQLException method.
     * @throws SQLException the sql exception
     */
    public void open() throws SQLException {
        dbStudent = dbHelper.getWritableDatabase();
        preHelper.onCreate(dbStudent);
        dbHelper.onCreate(dbStudent);
        marHelper.onCreate(dbStudent);
    }

    /**
     * Close database.
     */
    public void close() {
        dbStudent.close();
    }

    /**
     * Add student long.
     * @param name     the name
     * @param lastName the lastName
     * @return the long
     */
    public long addStudent(String name, String lastName) {
        ContentValues cv = new ContentValues();
        cv.put(STUhelper.STU_NAME, name);
        cv.put(STUhelper.STU_LAST_NAME, lastName);
        return dbStudent.insert(STUhelper.TABLE_TYPE, null, cv);
    }

    /**
     * Gets student.
     * @return the student
     */
    public Cursor getStudent() {
        //ContentValues cv = new ContentValues();
       // cv.put(STUhelper.STU_NAME, "sads");
        //cv.put(STUhelper.STU_LAST_NAME, "asdad");
       // dbStudent.insert(STUhelper.TABLE_TYPE, null, cv);
        return dbStudent.query(STUhelper.TABLE_TYPE, Student_RECORS, null, null, null, null, STUhelper.STU_LAST_NAME + " ASC");
    }

    /**
     * Gets student for subject.
     * @param subject the subject
     * @return the student for subject
     */
    public Cursor getStudentForSubject(String subject) {
        String[] whereArgs = new String[] {
                subject,
        };
        return dbStudent.query(STUhelper.TABLE_TYPE, Student_RECORS, STUhelper.STU_SUBJECT + " = ?", whereArgs, null, null, STUhelper.STU_LAST_NAME + " ASC");
    }

    /**
     * Delete student boolean.
     * @param id the id
     * @return the boolean
     */
    public boolean deleteStudent(String id) {

        return dbStudent.delete(STUhelper.TABLE_TYPE, STUhelper.STU_ID + "=?", new String[]{id}) > 0;
    }

    /**
     * Add pres rec boolean.
     * @param stud_id the stud id
     * @param sub     the sub
     * @param name    the name
     * @param surname the surname
     * @return the boolean
     */
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

    /**
     * Gets all pres.
     * @return the all pres
     */
    public Cursor getAllPres() {
        return dbStudent.query(PREhelper.TABLE_TYP, Presistance_RECORS, null, null, null, null, null);
    }

    /**
     * Gets pres for subject.
     * @param subject the subject
     * @return the pres for subject
     */
    public Cursor getPresForSubject(String subject) {
        String[] whereArgs = new String[] {
                subject,
        };
        return dbStudent.query(PREhelper.TABLE_TYP, Presistance_RECORS, PREhelper.PRE_SUBJECT + " = ?", whereArgs, null, null,STUhelper.STU_LAST_NAME + " ASC");

    }

    /**
     * Gets pres by id.
     * @param id the id
     * @return the pres by id
     */
    public Cursor getPresById(long id) {
        String[] whereArgs = new String[] {
                String.valueOf(id),
        };
        return dbStudent.query(PREhelper.TABLE_TYP, Presistance_RECORS, PREhelper.PRE_ID + " = ?", whereArgs, null, null,STUhelper.STU_LAST_NAME + " ASC");
    }

    /**
     * Update pres int.
     * @param id   the id
     * @param args the args
     * @return the int
     */
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

    /**
     * Delete pr boolean.
     * @param id the id
     * @return the boolean
     */
    public boolean deletePr(String id) {
        return dbStudent.delete(PREhelper.TABLE_TYP, PREhelper.PRE_STU_ID + "=?", new String[]{id}) > 0;
    }


    /**
     * Add mark boolean.
     * @param stud_id the stud id
     * @param sub     the sub
     * @param name    the name
     * @param surname the surname
     * @return the boolean
     */
    public boolean addMark(long stud_id, String sub, String name, String surname) {
        ContentValues cv = new ContentValues();
        cv.put(MARhelper.MAR_STU_ID, stud_id);
        cv.put(MARhelper.MAR_IM, name);
        cv.put(MARhelper.MAR_NAZW, surname);
        cv.put(MARhelper.MAR_SUBJECT, sub);
        cv.put(MARhelper.MAR_K1, "0");
        cv.put(MARhelper.MAR_K2, "0");
        cv.put(MARhelper.MAR_EGZ,"0");
        return dbStudent.insert(MARhelper.TABLE_TYP, null, cv) != -1;
    }

    /**
     * Gets all mark.
     * @return the all mark
     */
    public Cursor getAllMark() {
        return dbStudent.query(MARhelper.TABLE_TYP, Mark_RECORS, null, null, null, null, null);
    }

    /**
     * Gets mark for subject.
     * @param subject the subject
     * @return the mark for subject
     */
    public Cursor getMarkForSubject(String subject) {
        String[] whereArgs = new String[] {
                subject,
        };
        return dbStudent.query(MARhelper.TABLE_TYP, Mark_RECORS, MARhelper.MAR_SUBJECT + " = ?", whereArgs, null, null,STUhelper.STU_LAST_NAME + " ASC");

    }

    /**
     * Gets marks by id.
     * @param id the id
     * @return the marks by id
     */
    public Cursor getMarksById(long id) {
        String[] whereArgs = new String[] {
                String.valueOf(id),
        };
        return dbStudent.query(MARhelper.TABLE_TYP, Mark_RECORS, MARhelper.MAR_ID + " = ?", whereArgs, null, null,STUhelper.STU_LAST_NAME + " ASC");
    }


    /**
     * Update mark int.
     * @param id   the id
     * @param args the args
     * @return the int
     */
    public int updateMark(long id, String[] args){
        ContentValues cv = new ContentValues();
        cv.put("k1",args[0]);
        cv.put("k2",args[1]);
        cv.put("egz",args[2]);

        return dbStudent.update(MARhelper.TABLE_TYP, cv, "_id="+id, null);
    }


    /**
     * Delete mark boolean.
     * @param id the id
     * @return the boolean
     */
    public boolean deleteMark(String id) {
        return dbStudent.delete(MARhelper.TABLE_TYP, MARhelper.MAR_STU_ID + "=?", new String[]{id}) > 0;
    }
}