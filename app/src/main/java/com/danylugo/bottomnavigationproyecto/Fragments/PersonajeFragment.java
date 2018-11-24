package com.danylugo.bottomnavigationproyecto.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.danylugo.bottomnavigationproyecto.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonajeFragment extends Fragment {

    public PersonajeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_personaje, container, false);;
        // Inflate the layout for this fragment
        return v;
    }

}
