package com.danylugo.bottomnavigationproyecto.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danylugo.bottomnavigationproyecto.Model.SpiderCard;

import com.danylugo.bottomnavigationproyecto.R;

import java.util.List;

public class SpiderSelAdapter extends RecyclerView.Adapter<SpiderSelAdapter.MyViewHolder> implements View.OnClickListener {
    private List<SpiderCard> personajeList;
    private View.OnClickListener listener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView spiderName;
        public ImageView spiderCardImage;

        public MyViewHolder(View v) {
            super(v);

            spiderName = v.findViewById(R.id.spiderName);
            spiderCardImage = v.findViewById(R.id.imgCard);
        }
    }

    public SpiderSelAdapter(List<SpiderCard> spiders){
        personajeList = spiders;
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
                .inflate(R.layout.spider_card, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        SpiderCard spider = personajeList.get(i);
        myViewHolder.spiderName.setText(spider.getName());

        myViewHolder.spiderCardImage.setImageResource(spider.getCardImage());
    }

    @Override
    public int getItemCount() {
        return personajeList.size();
    }
}
