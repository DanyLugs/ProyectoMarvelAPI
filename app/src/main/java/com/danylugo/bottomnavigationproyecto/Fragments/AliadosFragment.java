package com.danylugo.bottomnavigationproyecto.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danylugo.bottomnavigationproyecto.Adapter.SecondaryListAdapter;
import com.danylugo.bottomnavigationproyecto.Model.Secondary;
import com.danylugo.bottomnavigationproyecto.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AliadosFragment extends Fragment {

    private List<Secondary> allies;
    private RecyclerView alliesRecycler;
    private SecondaryListAdapter mAdapterA;

    private  String id;

    public AliadosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aliados, container, false);
        allies = new ArrayList<>();
        alliesRecycler= (RecyclerView) view.findViewById(R.id.recyclerA);
        alliesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        String texto = getArguments().getString("spiderID");
        Log.i("SPIDEX",texto);
        id = texto;

        addElements();

        mAdapterA =new SecondaryListAdapter(allies);
        alliesRecycler.setAdapter(mAdapterA);
        return view;
    }

    public void addElements(){

        Secondary ally = new Secondary("Joan Guerrero :v", R.drawable.skull);
        allies.add(ally);
    }

}
