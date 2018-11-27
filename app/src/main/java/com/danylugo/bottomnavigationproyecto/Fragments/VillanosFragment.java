package com.danylugo.bottomnavigationproyecto.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import com.danylugo.bottomnavigationproyecto.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VillanosFragment extends Fragment {

    private List<Secondary> enemies;
    private RecyclerView enemiesRecycler;
    private SecondaryListAdapter mAdapterE;

    private String id;

    public VillanosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_villanos, container, false);
        enemies = new ArrayList<>();
        enemiesRecycler= (RecyclerView) view.findViewById(R.id.recyclerV);
        enemiesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        String texto = getArguments().getString("spiderID");
        Log.i("SPIDEX",texto);
        id = texto;

        addElements();

        mAdapterE =new SecondaryListAdapter(enemies);
        enemiesRecycler.setAdapter(mAdapterE);

        return view;
    }


    public void addElements(){

        Secondary enemy = new Secondary("feminazi", R.drawable.skull);
        enemies.add(enemy);
    }
}
