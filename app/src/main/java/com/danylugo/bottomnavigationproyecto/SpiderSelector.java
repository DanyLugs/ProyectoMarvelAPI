package com.danylugo.bottomnavigationproyecto;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.danylugo.bottomnavigationproyecto.Adapter.SpiderSelAdapter;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.Constants;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.RestApiAdapter;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.Service;
import com.danylugo.bottomnavigationproyecto.Model.Secondary;
import com.danylugo.bottomnavigationproyecto.Model.Spider;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpiderSelector extends AppCompatActivity {


    private static final String TAG = "SPIDEX";

    private List<Spider> spiders;
    private SpiderSelAdapter mAdapter;
    private RecyclerView mRecyclerView;
    ArrayList<Secondary> allies;
    ArrayList<Secondary> enemies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spider_selector);

        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        enemies = new ArrayList<>();
        Secondary enemy = new Secondary("feminazi",R.drawable.skull);
        enemies.add(enemy);

        allies = new ArrayList<>();
        Secondary ally = new Secondary("joan Guerrero",R.drawable.skull);
        allies.add(ally);

        buildRecyclerView();

        addElements();


    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recycler);
        spiders = new ArrayList<>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SpiderSelAdapter(spiders);
        mRecyclerView.setAdapter(mAdapter);

        changeActivity();
    }

    public void changeActivity(){
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpiderSelector.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



    public void addElements(){

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = restApiAdapter.getCharacterService();
        retrofit2.Call<JsonObject> call = service.getDataPersonaje(Constants.APIKEY,Constants.TS,Constants.HASH);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    assert response.body() != null;
                    JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject("data").toString());
                    JSONArray jsonArray   = jsonObject.getJSONArray("results");

                    parseCharacter(jsonArray);
                    mAdapter.adicionarListaSpiders(spiders);

                    Log.i(TAG,spiders.get(1).getName());

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, " onResponse: " + t.getMessage());
            }
        });

    }

    public void addElement(){
        ArrayList<String> spidersId = new ArrayList<>();
        spidersId.add("1009610");//Spidermans, sus id
        spidersId.add("1014873");//
        spidersId.add("1016181");
        spidersId.add("1009608");
        spidersId.add("1011426");
        spidersId.add("1011010");
        spidersId.add("1011347");
        spidersId.add("1011114");
        spidersId.add("1010727");
        spidersId.add("1012295");
        spidersId.add("1009609");
        spidersId.add("1011197");

    }

    public void parseCharacter(JSONArray jsonArray) throws JSONException{
        for(int i = 0; i<jsonArray.length();i++){
            JSONObject character = jsonArray.getJSONObject(i);
            String name = character.getString("name");
            String description = "";

            if (character.isNull("description")) {
                description = "Not avalible for this character";
            } else {
                description = character.getString("description");
            }

            JSONObject objectImage = character.getJSONObject("thumbnail");

            String path = "";
            String extension = "";

            if (objectImage.getString("path") != "") {
                path = objectImage.getString("path");
                extension = objectImage.getString("extension");
            } else {
                path = "Image";
                extension = "Not Avalible";
            }

            spiders.add(new Spider(name, description, path + "." + extension, R.drawable.spider_man_card2, enemies, allies));
        }
    }
}
