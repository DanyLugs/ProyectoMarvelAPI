package com.danylugo.bottomnavigationproyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class SpiderSelector extends AppCompatActivity {

    CardView card = (CardView) findViewById(R.id.card);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spider_selector);

        getSupportActionBar().hide();
    }


}
