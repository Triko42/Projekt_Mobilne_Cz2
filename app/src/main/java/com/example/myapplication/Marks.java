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

/**
 * The type Marks.
 */
public class Marks extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{


    private ListView markListView;
    private SimpleCursorAdapter maAdapter;
    private STUstudent dbStudent;
    private String subject;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.marks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        markListView = view.findViewById(R.id.markList);

        Bundle bundle = this.getArguments();

        TextView subject = view.findViewById(R.id.subject2);
        this.subject = bundle.getString("key");
        subject.setText(this.subject);

        populateListView(view);
    }

    private void populateListView(View view) {
        dbStudent = new STUstudent(view.getContext());
        dbStudent.open();

        maAdapter = new SimpleCursorAdapter(
                view.getContext(), R.layout.mark_list, null,
                new String[]{
                        MARhelper.MAR_IM,
                        MARhelper.MAR_NAZW,
                        MARhelper.MAR_K1,
                        MARhelper.MAR_K2,
                        MARhelper.MAR_EGZ,
                },
                new int[]{
                        R.id.NAZWmark1,
                        R.id.IMIEmark2,
                        R.id.oce1,
                        R.id.oce2,
                        R.id.oce3,
                }, 0);
        markListView.setAdapter(maAdapter);
        getLoaderManager().initLoader(2, null, this);


        markListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager = getFragmentManager();
                Bundle bundle1 = new Bundle();

                bundle1.putLong("id", id);
                bundle1.putString("key", subject);

                EditMarks edit = new EditMarks();
                edit.setArguments(bundle1);
//                dbStudent.close();
                Navigator.navigateTo(fragmentManager, edit);
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
        return new MARloader(this.getContext(), dbStudent, this.subject);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        maAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        maAdapter.swapCursor(null);
    }
}
