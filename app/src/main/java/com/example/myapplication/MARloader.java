package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.example.myapplication.STUstudent;

/**
 * The type MARloader.
 */
public class MARloader extends CursorLoader {


    /**
     * The Dbase.
     */
    STUstudent dbase;
    /**
     * The Subject.
     */
    String subject;

    /**
     * Instantiates a new MARloader.
     *
     * @param context the context
     * @param b       the b
     * @param subject the subject
     */
    public MARloader(Context context, STUstudent b, String subject ) {
        super(context);
        dbase = b;
        this.subject = subject;
    }

    @Override
    public Cursor loadInBackground() {
        return dbase.getMarkForSubject(subject);
    }
}
