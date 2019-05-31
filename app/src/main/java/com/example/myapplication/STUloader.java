package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

/**
 * The type STUloader.
 */
public class STUloader extends CursorLoader {
    /**
     * The database.
     */
    STUstudent dbase;

    /**
     * Instantiates a new STUloader.
     * @param context the context
     * @param b       the b
     */
    public STUloader(Context context, STUstudent b) {
        super(context);
        dbase = b;
    }

    @Override
    public Cursor loadInBackground() {
        return dbase.getStudent();
    }
}
