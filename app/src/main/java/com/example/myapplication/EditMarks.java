package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EditMarks extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private STUstudent dbStudent;
    private long id;
    private String subject;

    private EditText m1;
    private EditText m2;
    private EditText m3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.marks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();

        dbStudent = new STUstudent(view.getContext());
        dbStudent.open();

        m1 = view.findViewById(R.id.emark1);
        m2 = view.findViewById(R.id.emark2);
        m3 = view.findViewById(R.id.emark3);

        this.id = bundle.getLong("id");
        this.subject = bundle.getString("key");

        Cursor cursor = dbStudent.getMarksById(id);
        cursor.moveToFirst();

        String em1 = cursor.getString(cursor.getColumnIndex("m1"));
        String em2 = cursor.getString(cursor.getColumnIndex("m2"));
        String em3 = cursor.getString(cursor.getColumnIndex("m3"));

        m1.setText(em1);
        m2.setText(em2);
        m3.setText(em3);

        //TextView subject = view.findViewById(R.id.subject2);
       // subject.setText(bundle.getString("key"));

        Button button = view.findViewById(R.id.saveprebtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });

    }

    private void onAddButtonClicked() {
        String em1 = m1.getText().toString();
        String em2 = m2.getText().toString();
        String em3 = m3.getText().toString();

        String[] args = {
                em1,
                em2,
                em3,

        };

        dbStudent.updateMark(this.id, args);

        Bundle bundle1 = new Bundle();

        bundle1.putString("key", subject);
        FragmentManager fragmentManager = getFragmentManager();

        EditMarks edit2 = new EditMarks();
        edit2.setArguments(bundle1);
        dbStudent.close();
        Nawigator.navigateTo(fragmentManager, edit2);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    }
