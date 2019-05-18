package com.example.projekt_mobilne_cz2;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

public class STUloader extends CursorLoader {
    STUstudent dbase;
    public STUloader(Context context, STUstudent b) {
        super(context);
        dbase = b;
    }

    @Override
    public Cursor loadInBackground() {
        return dbase.getStudent();
    }
}
