package com.danylugo.bottomnavigationproyecto;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.danylugo.bottomnavigationproyecto.Adapter.SpiderSelAdapter;
import com.danylugo.bottomnavigationproyecto.Model.Secondary;
import com.danylugo.bottomnavigationproyecto.Model.SpiderCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpiderSelector extends AppCompatActivity {

    private List<SpiderCard> spidersCards;
    private SpiderSelAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spider_selector);

        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buildRecyclerView();
        changeActivity();
    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recycler);
        spidersCards = new ArrayList<>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SpiderSelAdapter(spidersCards);
        mRecyclerView.setAdapter(mAdapter);

        addElements();
    }

    public void changeActivity(){
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpiderSelector.this, MainActivity.class);
                String id = spidersCards.get(mRecyclerView.getChildAdapterPosition(view)).getId();
                intent.putExtra("stringId",id);
                startActivity(intent);
            }
        });
    }

    public void addElements(){
        spidersCards.add(new SpiderCard("Spider-Man","1009610",R.drawable.spider_man_card2));
        spidersCards.add(new SpiderCard("Spider-Man (2099)","1014873",R.drawable.spider_man_card2));
        spidersCards.add(new SpiderCard("Spider-Man (Miles Morales)","1016181",R.drawable.spider_man_card2));
        spidersCards.add(new SpiderCard("Spider-Woman (Jessica Drew)","1009608",R.drawable.spider_man_card2));
        spidersCards.add(new SpiderCard("Scarlet Spider (Kaine)","1011426",R.drawable.spider_man_card2));
        spidersCards.add(new SpiderCard("Spider-Man (Ultimate)","1011010",R.drawable.spider_man_card2));
        spidersCards.add(new SpiderCard("Spider-Ham (Larval Earth)","1011347",R.drawable.spider_man_card2));
        spidersCards.add(new SpiderCard("Spider-Man (Marvel Zombies)","1011114",R.drawable.spider_man_card2));
        spidersCards.add(new SpiderCard("Spider-dok","1010727",R.drawable.spider_man_card2));
        spidersCards.add(new SpiderCard("Spider-Man (Noir)","1012295",R.drawable.spider_man_card2));
        spidersCards.add(new SpiderCard("Spider-Girl (May Parker)","1009609",R.drawable.spider_man_card2));
        spidersCards.add(new SpiderCard("Scarlet Spider (Ben Reilly)","1009610",R.drawable.spider_man_card2));
        spidersCards.add(new SpiderCard("Spider Moy","666666",R.drawable.spider_man_card2));

    }

}
