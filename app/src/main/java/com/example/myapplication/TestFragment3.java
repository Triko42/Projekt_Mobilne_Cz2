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
import android.widget.TextView;
import android.widget.Toast;

/**
 * The type Test fragment 3.
 */
public class TestFragment3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chooser, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       Bundle bundle = this.getArguments();

        TextView subject = view.findViewById(R.id.subject);
        final String sub = bundle.getString("key");
        subject.setText(sub);

        /*
        Button button_changelist = view.findViewById(R.id.buttonMarks1);
        button_changelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager!=null) {
                    Toast.makeText(getContext(), "klik", Toast.LENGTH_SHORT).show();

                    Bundle bundle1 = new Bundle();

                    bundle1.putString("key", sub);

                    EditMarks editMarks = new EditMarks();
                    editMarks.setArguments(bundle1);

                    Navigator.navigateTo(fragmentManager, editMarks);
                }
            }

        });
        */

        Button button_changelist2 = view.findViewById(R.id.buttonPresence1);
        button_changelist2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager!=null) {
                    Toast.makeText(getContext(), "Obecność", Toast.LENGTH_SHORT).show();

                    Bundle bundle1 = new Bundle();

                    bundle1.putString("key", sub);

                    Presistance presistance = new Presistance();
                    presistance.setArguments(bundle1);

                    Navigator.navigateTo(fragmentManager, presistance);
                }
            }

        });

        Button button_changelist3 = view.findViewById(R.id.buttonMarks1);
        button_changelist3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager!=null) {
                    Toast.makeText(getContext(), "Oceny", Toast.LENGTH_SHORT).show();

                    Bundle bundle1 = new Bundle();

                    bundle1.putString("key", sub);

                    Marks marks = new Marks();
                    marks.setArguments(bundle1);

                    Navigator.navigateTo(fragmentManager, marks);
                }
            }

        });
    }
}