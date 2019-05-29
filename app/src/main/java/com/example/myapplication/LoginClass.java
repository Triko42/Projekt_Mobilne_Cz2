package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginClass extends Fragment {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter=5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loginpanel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Name=view.findViewById(R.id.loginfield);
        Password=view.findViewById(R.id.passfield);
        Info=view.findViewById(R.id.loginview);
        Login=view.findViewById(R.id.buttonlogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });

    }

    private void onAddButtonClicked() {
        validate(Name.getText().toString(),Password.getText().toString());
    }

    private void validate(String UserName, String UserPassword){

        if((UserName.equals("Admin"))&&(UserPassword.equals("1234")))
        {
            FragmentManager fragmentManager = getFragmentManager();
            AdminPanelFragment edit = new AdminPanelFragment();
            Navigator.navigateTo(fragmentManager, edit);
        }
        else
        {
            counter--;

            Info.setText("No of attempts remaining: "+ counter);

            if(counter==0)
            {
                Login.setEnabled(false);
            }
        }
    }


    }

