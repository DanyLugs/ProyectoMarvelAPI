package com.danylugo.bottomnavigationproyecto;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class SpiderSelector extends AppCompatActivity {

    private CardView card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spider_selector);

        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        card = (CardView) findViewById(R.id.card);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card:
                Intent intent = new Intent(SpiderSelector.this, MainActivity.class);
                startActivity(intent);
            break;
        }
    }

}
