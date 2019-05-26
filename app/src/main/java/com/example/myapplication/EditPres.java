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

public class EditPres extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private STUstudent dbStudent;
    private long id;
    private String subject;

    private EditText o1;
    private EditText o2;
    private EditText o3;
    private EditText o4;
    private EditText o5;
    private EditText o6;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.editpre, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();

        dbStudent = new STUstudent(view.getContext());
        dbStudent.open();

         o1 = view.findViewById(R.id.eob1);
         o2 = view.findViewById(R.id.eob2);
         o3 = view.findViewById(R.id.eob3);
         o4 = view.findViewById(R.id.eob4);
         o5 = view.findViewById(R.id.eob5);
         o6 = view.findViewById(R.id.eob6);
        this.id = bundle.getLong("id");
        this.subject = bundle.getString("key");
        //subject.setText(this.subject);
        Cursor cursor = dbStudent.getPresById(id);
        cursor.moveToFirst();
        String ob1 = cursor.getString(cursor.getColumnIndex("o1"));
        String ob2 = cursor.getString(cursor.getColumnIndex("o2"));
        String ob3 = cursor.getString(cursor.getColumnIndex("o3"));
        String ob4 = cursor.getString(cursor.getColumnIndex("o4"));
        String ob5 = cursor.getString(cursor.getColumnIndex("o5"));
        String ob6 = cursor.getString(cursor.getColumnIndex("o6"));

        o1.setText(ob1);
        o2.setText(ob2);
        o3.setText(ob3);
        o4.setText(ob4);
        o5.setText(ob5);
        o6.setText(ob6);

        Button button = view.findViewById(R.id.saveprebtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });
    }

    private void onAddButtonClicked(){
        String ob1 = o1.getText().toString();
        String ob2 = o2.getText().toString();
        String ob3 = o3.getText().toString();
        String ob4 = o4.getText().toString();
        String ob5 = o5.getText().toString();
        String ob6 = o6.getText().toString();
        String[] args ={
                        ob1,
                        ob2,
                        ob3,
                        ob4,
                        ob5,
                        ob6,
                };

        dbStudent.updatePres(this.id, args);

        Bundle bundle1 = new Bundle();

        bundle1.putString("key", subject);
        FragmentManager fragmentManager = getFragmentManager();

        Presistance edit = new Presistance();
        edit.setArguments(bundle1);
        dbStudent.close();
        Nawigator.navigateTo(fragmentManager, edit);
        //refreshList();
    }

   // private void refreshList() {
   //     getLoaderManager().getLoader(2).forceLoad();
  // }
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
