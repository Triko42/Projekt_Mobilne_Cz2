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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Presistance extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private ListView presListView;
    private SimpleCursorAdapter prAdapter;
    private STUstudent dbStudent;
    private String subject;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.presistance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presListView = view.findViewById(R.id.presistnceList);

        Bundle bundle = this.getArguments();

        TextView subject = view.findViewById(R.id.subjectPr);
        this.subject = bundle.getString("key");
        subject.setText(this.subject);

        populateListView(view);
    }

    private void populateListView(View view) {
        dbStudent = new STUstudent(view.getContext());
        dbStudent.open();

        prAdapter = new SimpleCursorAdapter(
                view.getContext(), R.layout.pr_rec, null,
                new String[]{
                        PREhelper.PRE_IM,
                        PREhelper.PRE_NAZ,
                        PREhelper.PRE_1,
                        PREhelper.PRE_2,
                        PREhelper.PRE_3,
                        PREhelper.PRE_4,
                        PREhelper.PRE_5,
                        PREhelper.PRE_6
                },
                new int[]{
                        R.id.IMIEpre,
                        R.id.NAZWpre,
                        R.id.obe1,
                        R.id.obe2,
                        R.id.obe3,
                        R.id.obe4,
                        R.id.obe5,
                        R.id.obe6
                }, 0);
        presListView.setAdapter(prAdapter);
        getLoaderManager().initLoader(2, null, this);


        presListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager = getFragmentManager();
                Bundle bundle1 = new Bundle();

                bundle1.putLong("id", id);
                bundle1.putString("key", subject);

                EditPres edit = new EditPres();
                edit.setArguments(bundle1);
                dbStudent.close();
                Nawigator.navigateTo(fragmentManager, edit);
            }
        });
        refreshList();
    }


    private void refreshList() {
        getLoaderManager().getLoader(2).forceLoad();
    }
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new PRELoader(this.getContext(), dbStudent, this.subject);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        prAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        prAdapter.swapCursor(null);
    }
}
