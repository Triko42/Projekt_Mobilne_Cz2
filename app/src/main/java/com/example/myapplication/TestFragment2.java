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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class TestFragment2 extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.student_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        populateListView(view);

//              TextView label = view.findViewById(R.id.label);
////        label.setText("FRAGMENT 2");
////
////
////        Button button_changelist = view.findViewById(R.id.admin_panel);
////        button_changelist.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                    Toast.makeText(getContext(), "KLIKŁEM w 2", Toast.LENGTH_SHORT).show();
////            }
////
////        });
    }

    STUstudent dbStudent;
    SimpleCursorAdapter scAdapter;

    private void populateListView(View view) {
        dbStudent = new STUstudent(view.getContext());
        dbStudent.open();
        view.findViewById(R.id.button_add).setOnClickListener(this);
        view.findViewById(R.id.button_delete).setOnClickListener(this);
        scAdapter=new SimpleCursorAdapter(
                view.getContext(), R.layout.studenci, null,
                new String[]{STUhelper.STU_ID,
                        STUhelper.STU_IMIE, STUhelper.STU_NAZWISKO},
                new int[]{R.id.IDstu, R.id.IMIEstu, R.id.NAZWstu}, 0);
        ((ListView)view.findViewById(R.id.lista1)).setAdapter(
                scAdapter);
        getLoaderManager().initLoader(1, null, this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_add:
                dbStudent.addStudent(
                        ((EditText)view.findViewById(
                                R.id.editText_imie)).getText().toString(),
                        ((EditText)view.findViewById(
                                R.id.editText_nazwisko)).getText().toString());
                getLoaderManager().getLoader(1).forceLoad();

                break;
            case R.id.button_delete:
                dbStudent.deleteStudent(
                        ((EditText)view.findViewById(
                                R.id.editText_imie)).getText().toString());
                getLoaderManager().getLoader(1).forceLoad();
                break;
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new STUloader(this.getContext(), dbStudent);
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
}
