package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

public class PRELoader extends CursorLoader {

    STUstudent dbase;
    String subject;

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
