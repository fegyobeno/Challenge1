package com.example.challenge1;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.challenge1.model.Animal;
import com.example.challenge1.model.AnimalsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private String selectedAnimal = null;
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
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


    // call fe1, fr2 func,
    // here to have a func that calls a mainActivity function
    // and the logic will be there

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_1, container, false);
        Button button = (Button) view.findViewById(R.id.f1_button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                ((MainActivity) getActivity()).switchToFr2();
            }
        });

        AnimalsViewModel viewModel = new ViewModelProvider(getActivity()).get(AnimalsViewModel.class);

        ImageView imageView = view.findViewById(R.id.imageView);
//
        List<String> myArraySpinner = new ArrayList<String>();

        for (Animal a: viewModel.getUiState().getValue()){
            myArraySpinner.add(a.getName());
            System.out.println(a.getName());
        }

        Spinner mySpinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, myArraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);


        Spinner spin = view.findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> spin, View v, int i, long id) {

                short temp = -1;
                if(viewModel.getUiState().getValue().get(0).getName().equals((String) spin.getSelectedItem())) {
                    temp = 0;
                } else if (viewModel.getUiState().getValue().get(1).getName().equals((String) spin.getSelectedItem())){
                    temp = 1;
                } else {
                    temp = 2;
                }

                TextView result = view.findViewById(R.id.result);
                TextView resultOwner = view.findViewById(R.id.resultOwner);
                TextView resultAge = view.findViewById(R.id.resultAge);

                result.setText("You chose: " + viewModel.getUiState().getValue().get(temp).getName());


                resultOwner.setText("Owner: " + viewModel.getUiState().getValue().get(temp).getOwner());

                resultAge.setText("Age: " + viewModel.getUiState().getValue().get(temp).getAge().toString());

                String res = (String) spin.getSelectedItem();
                Resources resources = getActivity().getResources();
                final int resourceId = resources.getIdentifier(viewModel.getUiState().getValue().get(temp).getImage(), "drawable",
                        getActivity().getPackageName());
                imageView.setImageResource(resourceId);
//                selectedAnimal = spin.getSelectedItem().toString();
//                System.out.println(selectedAnimal);
                for (Animal a: viewModel.getUiState().getValue()){
                    if (res.equals(a.getName()))
                        a.setSelected(true);
                    else a.setSelected(false);
                }
                // set chosen to viewmodel

            }
            public void onNothingSelected(AdapterView<?> parent) {} // empty
        });


        return view;
    }


}