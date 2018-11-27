package com.danylugo.bottomnavigationproyecto.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danylugo.bottomnavigationproyecto.Model.Secondary;
import com.danylugo.bottomnavigationproyecto.Model.Spider;
import com.danylugo.bottomnavigationproyecto.R;

import java.util.List;

public class SecondaryListAdapter extends RecyclerView.Adapter<SecondaryListAdapter.MyViewHolder> implements View.OnClickListener {
    private List<Secondary> personajeList;
    private View.OnClickListener listener;
    private Fragment context;

    public void adiccionarLista(List<Secondary> secondarys) {
        personajeList.add(secondarys.get(0));
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView spiderName;
        public ImageView spiderCardImage;

        public MyViewHolder(View v) {
            super(v);

            spiderName = v.findViewById(R.id.sName);
            spiderCardImage = v.findViewById(R.id.imageS);
        }
    }

    public SecondaryListAdapter(List<Secondary> secondaries, Fragment context){
        personajeList = secondaries;
        this.context = context;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.secondary_card, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Secondary secondary = personajeList.get(i);
        myViewHolder.spiderName.setText(secondary.getName());

        Glide.with(context)
                .load(secondary.getImage())
                .into(myViewHolder.spiderCardImage);
    }

    @Override
    public int getItemCount() {
        return personajeList.size();
    }
}