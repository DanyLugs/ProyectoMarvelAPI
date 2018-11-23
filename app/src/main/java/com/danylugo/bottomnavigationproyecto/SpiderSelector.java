package com.danylugo.bottomnavigationproyecto;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.danylugo.bottomnavigationproyecto.MarvrelAPI.Constants;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.RestApiAdapter;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.Service;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class SpiderSelector extends AppCompatActivity {

    private CardView card;
    private Button button;
    private TextView tv;
    ArrayList<Object> character;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spider_selector);

        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        card      = (CardView) findViewById(R.id.card);
        tv        = (TextView) findViewById(R.id.tv);
        button    = (Button) findViewById(R.id.button);
        character = new ArrayList<>();

        /*RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = restApiAdapter.getCharacterService();
        retrofit2.Call<JsonObject> call = service.getDataPersonaje(Constants.APIKEY,Constants.TS,Constants.HASH);

        call.enqueue(new retrofit2.Callback<JsonObject>(){

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });*/
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
