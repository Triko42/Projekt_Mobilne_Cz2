package com.example.projekt_mobilne_cz2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {


    STUstudent dbStudent;
    SimpleCursorAdapter scAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_change1= findViewById(R.id.button1);
        button_change1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentView(R.layout.przedmiot);
            }
        });

        final Button button_changelist= findViewById(R.id.button_student);
        button_changelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setContentView(R.layout.student_list);
                findViewById(R.id.button_add).setOnClickListener(this);
                findViewById(R.id.button_delete).setOnClickListener(this);

            }

        });



    }



    @Override
    public Loader<Cursor> onCreateLoader(int id,Bundle args){
        return new STUloader(this, dbStudent);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader,
                               Cursor data) {
        scAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        scAdapter.swapCursor(null);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                dbStudent.addStudent(
                        ((EditText)findViewById(
                                R.id.editText_imie)).getText().toString(),
                        ((EditText)findViewById(
                                R.id.editText_nazwisko)).getText().toString());
                getSupportLoaderManager().getLoader(1).forceLoad();
                break;
            case R.id.button2:
                dbStudent.deleteStudent(
                        ((EditText)findViewById(
                                R.id.editText_imie)).getText().toString());
                getSupportLoaderManager().getLoader(1).forceLoad();
                break;
        }
    }
}


