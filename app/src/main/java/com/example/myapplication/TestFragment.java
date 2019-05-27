package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class TestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button_changelist = view.findViewById(R.id.button1);
        button_changelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager!=null) {
                    Toast.makeText(getContext(), "Java", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    bundle.putString("key","Java");


                    TestFragment3 testFragment3 = new TestFragment3();
                    testFragment3.setArguments(bundle);

                    Nawigator.navigateTo(fragmentManager, testFragment3);
                }

            }

        });

        Button button_changelist3 = view.findViewById(R.id.button2);
        button_changelist3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager!=null) {
                    Toast.makeText(getContext(), "CAD", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    bundle.putString("key","CAD");


                    TestFragment3 testFragment3 = new TestFragment3();
                    testFragment3.setArguments(bundle);

                    Nawigator.navigateTo(fragmentManager, testFragment3);
                }

            }

        });

        Button button_changelist4 = view.findViewById(R.id.button3);
        button_changelist4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager!=null) {
                    Toast.makeText(getContext(), "Sieci komputerowe", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    bundle.putString("key","Sieci komputerowe");


                    TestFragment3 testFragment3 = new TestFragment3();
                    testFragment3.setArguments(bundle);

                    Nawigator.navigateTo(fragmentManager, testFragment3);
                }

            }

        });

        Button button_changelist5 = view.findViewById(R.id.button4);
        button_changelist5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager!=null) {
                    Toast.makeText(getContext(), "Bazy danych", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    bundle.putString("key","Bazy danych");


                    TestFragment3 testFragment3 = new TestFragment3();
                    testFragment3.setArguments(bundle);

                    Nawigator.navigateTo(fragmentManager, testFragment3);
                }

            }

        });

        Button button_changelist2 = view.findViewById(R.id.admin_panel);
        button_changelist2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager!=null) {
                    Toast.makeText(getContext(), "KLIKŁEM", Toast.LENGTH_SHORT).show();
                    Nawigator.navigateTo(fragmentManager, new AdminPanelFragment());
                }

            }

        });
    }
}
