package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.example.myapplication.STUstudent;

/**
 * The type PRELoader.
 */
public class PRELoader extends CursorLoader {

    /**
     * The Student database.
     */
    STUstudent dbase;
    /**
     * The Subject String.
     */
    String subject;

    /**
     * Instantiates a new PRELoader.
     * @param context the context from CursorLoader
     * @param b       the b object for dbase object
     * @param subject the subject String
     */
    public PRELoader(Context context, STUstudent b, String subject ) {
        super(context);
        dbase = b;
        this.subject = subject;
    }

    @Override
    public Cursor loadInBackground() {
        return dbase.getPresForSubject(subject);
    }
}
