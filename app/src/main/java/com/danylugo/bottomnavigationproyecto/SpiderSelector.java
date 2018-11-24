package com.danylugo.bottomnavigationproyecto;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.danylugo.bottomnavigationproyecto.MarvrelAPI.Constants;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.RestApiAdapter;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.Service;
import com.danylugo.bottomnavigationproyecto.Model.Personaje;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

public class SpiderSelector extends AppCompatActivity {

    private CardView card;
    private Button button;
    private TextView tv;
    ArrayList<Personaje> characters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spider_selector);

        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        card      = (CardView) findViewById(R.id.card);
        tv        = (TextView) findViewById(R.id.tv);
        button    = (Button) findViewById(R.id.button);
        characters = new ArrayList<>();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = restApiAdapter.getCharacterService();
        retrofit2.Call<JsonObject> call = service.getDataPersonaje(Constants.APIKEY,Constants.TS,Constants.HASH);

       call.enqueue(new retrofit2.Callback<JsonObject>(){

            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {

                    try {
                    assert response.body() != null;
                    JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject("data").toString());
                    JSONArray jsonArray   = jsonObject.getJSONArray("results");
                    parseCharacter(jsonArray);
                    tv.setText(characters.get(0).getImageCharacter());

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {

            }
        });
    }

    public void parseCharacter(JSONArray jsonArray) throws JSONException{

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject character = jsonArray.getJSONObject(i);
            String name = character.getString("name");
            String description = character.getString("description");

            if (character.isNull("description")){
                description = "Not avalible for this character";
            }else{
                description = character.getString("description");
            }

            JSONArray image = character.getJSONArray("thumbnail");

            String path = "";
            String extension = "";

            if (image.length() != 0){
                JSONObject objectImage = image.getJSONObject(0);
                path = objectImage.getString("path");
                extension = objectImage.getString("extension");
            }else{
                path = "Image";
                extension = "Not Avalible";
            }

            characters.add(new Personaje(name,description,path + "." + extension));
        }
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
