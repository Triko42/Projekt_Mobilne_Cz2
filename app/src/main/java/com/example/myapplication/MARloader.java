package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.example.myapplication.STUstudent;

public class MARloader extends CursorLoader {


    STUstudent dbase;
    String subject;

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
