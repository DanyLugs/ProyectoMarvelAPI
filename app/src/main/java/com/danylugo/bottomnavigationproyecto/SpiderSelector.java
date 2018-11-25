package com.danylugo.bottomnavigationproyecto;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.danylugo.bottomnavigationproyecto.Adapter.SpiderSelAdapter;
import com.danylugo.bottomnavigationproyecto.Model.Secondary;
import com.danylugo.bottomnavigationproyecto.Model.Spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpiderSelector extends AppCompatActivity {

    private List<Spider> spiders;
    private SpiderSelAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spider_selector);

        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buildRecyclerView();
    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recycler);
        spiders = new ArrayList<>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SpiderSelAdapter(spiders);
        mRecyclerView.setAdapter(mAdapter);

        changeActivity();
        addElements();
    }

    public void changeActivity(){
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spider spidy = spiders.get(mRecyclerView.getChildAdapterPosition(view));

                Intent intent = new Intent(SpiderSelector.this, MainActivity.class);

                //Bundle bundle = new Bundle();
                //bundle.putSerializable("spiderV",spidy);
                //intent.putExtras(bundle);

                startActivity(intent);

                Toast.makeText(getApplicationContext(),
                        "Selección: "+spidy.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addElements(){
        String uri = "@drawable/spider_man_card2";
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable imagen = getDrawable(imageResource);

        String uri2 = "@drawable/skull";
        int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
        Drawable imagen2 = getDrawable(imageResource2);

        ArrayList<Secondary> enemies = new ArrayList<>();
        Secondary enemy = new Secondary("feminazi",R.drawable.skull);
        enemies.add(enemy);

        ArrayList<Secondary> allies = new ArrayList<>();
        Secondary ally = new Secondary("joan Guerrero",R.drawable.skull);
        allies.add(ally);

        Spider a = new Spider("Spider-Moy", "Acosa","Vivo", imagen2 , imagen, enemies,allies);
        spiders.add(a);
        a = new Spider("Spider-Oski", "Le gusta el degenere sexual","Muerto", imagen2, imagen,enemies,allies);
        spiders.add(a);
        a = new Spider("Spider-Punk", "Le gusta el degenere sexual", "Vivo", imagen2 , imagen,enemies,allies);
        spiders.add(a);
        a = new Spider("Spider-Ham", "Le gusta el degenere sexual", "Vivo", imagen2 , imagen,enemies,allies);
        spiders.add(a);
        a = new Spider("Ultimate Spider-Oski", "Le gusta el degenere sexual", "Vivo", imagen2 , imagen,enemies,allies);
        spiders.add(a);
        a = new Spider("Spider-Oski 2099", "Le gusta el degenere sexual", "Vivo", imagen2 , imagen,enemies,allies);
        spiders.add(a);
        a = new Spider("Spider-Oski Zombie", "Le gusta el degenere sexual", "Vivo", imagen2 , imagen,enemies,allies);
        spiders.add(a);
    }
}
