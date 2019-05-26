package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AdminPanelFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private EditText nameEditText;
    private EditText surnameEditText;
    private Button addButton;
    private Button removeButton;
    private ListView studentsListView;

    private STUstudent dbStudent;
    private SimpleCursorAdapter scAdapter;

    private int selectedId = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.student_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameEditText = view.findViewById(R.id.editText_imie);
        surnameEditText = view.findViewById(R.id.editText_nazwisko);
        addButton = view.findViewById(R.id.button_add);
        removeButton = view.findViewById(R.id.button_delete);
        studentsListView = view.findViewById(R.id.lista1);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRemoveButtonClicked();
            }
        });

        populateListView(view);
    }

    private void onAddButtonClicked() {
        ExtensionUtils.hideKeyboard(getActivity());
        String imie = nameEditText.getText().toString();
        String nazwisko = surnameEditText.getText().toString();
        if (!TextUtils.isEmpty(imie.trim()) && !TextUtils.isEmpty(nazwisko.trim())) {
            long stud_id = dbStudent.addStudent(imie.trim(), nazwisko.trim());

            if (stud_id != -1) {
                dbStudent.addPresRec(stud_id, "Java",imie.trim(),nazwisko.trim());

                clearEditTexts();
                showSuccessMessage();
            } else {
                showError("Nie udało się dodać studenta.");
            }
        } else {
            showError("Wprowadzone dane są nieprawidłowe.");
        }
        refreshList();
    }

    private void refreshList() {
        getLoaderManager().getLoader(1).forceLoad();
    }

    private void onRemoveButtonClicked() {
        ExtensionUtils.hideKeyboard(getActivity());
        if (dbStudent.deleteStudent(String.valueOf(selectedId))) {
            clearEditTexts();
            showSuccessMessage();
        } else {
            showError("Nie udało się usunąć wybranych danych.");
        }
        refreshList();
    }

    private void clearEditTexts() {
        nameEditText.getText().clear();
        nameEditText.clearFocus();
        surnameEditText.getText().clear();
        surnameEditText.clearFocus();
    }

    private void showSuccessMessage() {
        Toast.makeText(this.getContext(), "Wszystko gitez", Toast.LENGTH_SHORT).show();
    }

    private void showError(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void populateListView(View view) {
        dbStudent = new STUstudent(view.getContext());
        dbStudent.open();

        scAdapter = new SimpleCursorAdapter(
                view.getContext(),
                R.layout.studenci,
                null,
                new String[]{
                        //STUhelper.STU_ID,
                        STUhelper.STU_IMIE,
                        STUhelper.STU_NAZWISKO},
                new int[]{
                       // R.id.IDstu,
                        R.id.IMIEpre,
                        R.id.NAZWpre
                }, 0);
        studentsListView.setAdapter(scAdapter);
        getLoaderManager().initLoader(1, null, this);
        studentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView nameTextView = view.findViewById(R.id.IMIEpre);
                TextView surnameTextView = view.findViewById(R.id.NAZWpre);
                String name = nameTextView.getText().toString();
                String surname = surnameTextView.getText().toString();

                if (!TextUtils.isEmpty(name.trim()) && !TextUtils.isEmpty(surname.trim())) {
                    nameEditText.setText(name);
                    surnameEditText.setText(surname);
                    selectedId = (int) id;
                }
            }
        });
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new STUloader(this.getContext(), dbStudent);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        scAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        scAdapter.swapCursor(null);
    }
}