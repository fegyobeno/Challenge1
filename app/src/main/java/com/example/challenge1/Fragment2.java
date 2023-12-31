package com.example.challenge1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challenge1.model.Animal;
import com.example.challenge1.model.AnimalsViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String name;

    private Animal selectedAnimal;

    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_2, container, false);

        AnimalsViewModel viewModel = new ViewModelProvider(getActivity()).get(AnimalsViewModel.class);

        for (Animal a: viewModel.getUiState().getValue()){
            if (a.isSelected()){
                selectedAnimal = a;
                name = a.getName();
                TextView result = view.findViewById(R.id.name);

                EditText editText = (EditText) view.findViewById(R.id.name_input);
                EditText ageText = (EditText) view.findViewById(R.id.age_input);
                ageText.setText(a.getAge().toString());
                editText.setText(a.getName());//set the text in edit text

            }
        }

        Button button = (Button) view.findViewById(R.id.f2_button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((MainActivity) getActivity()).switchToFr1();
            }
        });

        Button buttonSave = (Button) view.findViewById(R.id.save);
        buttonSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) view.findViewById(R.id.name_input);
                EditText ageText = (EditText) view.findViewById(R.id.age_input);

                String name = editText.getText().toString();
                try {
                    Integer age = Integer.valueOf(ageText.getText().toString());
                    if (name.isEmpty()) {
                        Toast.makeText(getContext(), "Enter the name", Toast.LENGTH_SHORT).show();
                    } else if (age < 0) {
                        Toast.makeText(getContext(), "Value error [age >! 0]", Toast.LENGTH_SHORT).show();
                    } else {
                        // save everything
                        selectedAnimal.setName(name);
                        selectedAnimal.setAge(age);
                        Toast.makeText(getContext(), "Name -  " + name  + "\nAge - " + age, Toast.LENGTH_SHORT).show();
                    }

                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(getContext(), "Invalid integer format " + ageText.getText().toString(), Toast.LENGTH_SHORT).show();
                }



            }
        });
        return view;}
}