package com.danylugo.bottomnavigationproyecto.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danylugo.bottomnavigationproyecto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonajeFragment extends Fragment {


    public PersonajeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personaje, container, false);
    }

}
