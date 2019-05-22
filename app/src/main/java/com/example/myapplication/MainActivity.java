package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Nawigator.navigateTo(this.getSupportFragmentManager(), new TestFragment());

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        TestFragment fragment = new TestFragment();
//        fragmentTransaction.add(R.id.details, fragment);
//        fragmentTransaction.commit();


//        Button button_change1= findViewById(R.id.button1);
//        button_change1.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.przedmiot);
//            }
//        });
//
//        Button button_changelist= findViewById(R.id.button_student);
//        button_changelist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.student_list);
//                //openDB();
//                populateListView();
//            }
//
//        });

    }

//    @Override
//    public Loader<Cursor> onCreateLoader(int id,Bundle args){
//        return new STUloader(this, dbStudent);
//
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader,
//                               Cursor data) {
//        scAdapter.swapCursor(data);
//    }
//
//    @Override
//    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
//        scAdapter.swapCursor(null);
//    }


//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.button_add:
//                dbStudent.addStudent(
//                        ((EditText)findViewById(
//                                R.id.editText_imie)).getText().toString(),
//                        ((EditText)findViewById(
//                                R.id.editText_nazwisko)).getText().toString());
//                getSupportLoaderManager().getLoader(1).forceLoad();
//
//                break;
//            case R.id.button_delete:
//                dbStudent.deleteStudent(
//                        ((EditText)findViewById(
//                                R.id.editText_imie)).getText().toString());
//                getSupportLoaderManager().getLoader(1).forceLoad();
//                break;
//        }
//    }



//    private void openDB()
//   {
//        dbStudent = new STUstudent(this);
//       dbStudent.open();
//   }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }



    private void populateListView(){
/*
        dbStudent = new STUstudent(this);
        dbStudent.open();
        Cursor cursor=dbStudent.getStudent();
        String[] fromFieldNames=new String[]{STUhelper.STU_ID, STUhelper.STU_IMIE, STUhelper.STU_NAZWISKO};
        int[] toView=new int[]{R.id.IDstu, R.id.IMIEstu, R.id.NAZWstu};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter=new SimpleCursorAdapter((getBaseContext()),R.layout.studenci,cursor,fromFieldNames,toView,0);
        ListView myList=findViewById(R.id.lista1);
        myList.setAdapter(myCursorAdapter);
*/
//        dbStudent = new STUstudent(this);
//        dbStudent.open();
//        findViewById(R.id.button_add).setOnClickListener(this);
//        findViewById(R.id.button_delete).setOnClickListener(this);
//        scAdapter=new SimpleCursorAdapter(
//                this, R.layout.studenci, null,
//                new String[]{STUhelper.STU_ID,
//                        STUhelper.STU_IMIE, STUhelper.STU_NAZWISKO},
//                new int[]{R.id.IDstu, R.id.IMIEstu, R.id.NAZWstu}, 0);
//        ((ListView)findViewById(R.id.lista1)).setAdapter(
//                scAdapter);
//        getSupportLoaderManager().initLoader(1, null, this);

    }
}

