package com.danylugo.bottomnavigationproyecto.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danylugo.bottomnavigationproyecto.Model.Personaje;
import com.danylugo.bottomnavigationproyecto.R;

import java.util.ArrayList;

public class PersonajeAdapter extends RecyclerView.Adapter<PersonajeAdapter.PersonajeViewHolder> {

    public ArrayList<Personaje> personajes;
    Context context;

    public PersonajeAdapter(ArrayList<Personaje> personajes, Context context) {
        this.personajes = personajes;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonajeAdapter.PersonajeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_spider_selector, viewGroup,false);
        return new PersonajeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonajeAdapter.PersonajeViewHolder personajeViewHolder, int i) {
        Personaje personaje = personajes.get(i);
    }

    @Override
    public int getItemCount() {
        return personajes.size();
    }

    public class PersonajeViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPersonaje;
        TextView namePersonaje;
        TextView descriptionPersonaje;

        public PersonajeViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPersonaje = (ImageView) itemView.findViewById(R.id.imgPersonaje);
            namePersonaje = (TextView) itemView.findViewById(R.id.namePersonaje);
            descriptionPersonaje = (TextView) itemView.findViewById(R.id.descriptionPersonaje);
        }
    }
}
