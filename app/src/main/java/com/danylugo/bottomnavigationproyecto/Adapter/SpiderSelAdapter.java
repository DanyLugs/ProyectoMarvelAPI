package com.danylugo.bottomnavigationproyecto.Adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.danylugo.bottomnavigationproyecto.Model.Spider;
import com.danylugo.bottomnavigationproyecto.R;

import java.util.List;

public class SpiderSelAdapter extends RecyclerView.Adapter<SpiderSelAdapter.MyViewHolder> implements View.OnClickListener {
    private List<Spider> personajeList;
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

    public SpiderSelAdapter(List<Spider> spiders){
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
        Spider spider = personajeList.get(i);
        myViewHolder.spiderName.setText(spider.getName());

        myViewHolder.spiderCardImage.setBackground(spider.getImageCard());
    }

    @Override
    public int getItemCount() {
        return personajeList.size();
    }
}
