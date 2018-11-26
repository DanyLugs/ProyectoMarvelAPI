package com.danylugo.bottomnavigationproyecto.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.Constants;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.RestApiAdapter;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.Service;
import com.danylugo.bottomnavigationproyecto.Model.Spider;
import com.danylugo.bottomnavigationproyecto.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonajeFragment extends Fragment {

    private  static String TAG = "SPIDERBOOK";
    private String id;
    private Spider spider;

    private TextView name;
    private TextView status;
    private TextView description;
    private ImageView image;

    public PersonajeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_personaje, container, false);;
        // Inflate the layout for this fragment

        name = v.findViewById(R.id.dName);
        status = v.findViewById(R.id.status);
        description = v.findViewById(R.id.description);
        image = v.findViewById(R.id.image);


        String texto = getArguments().getString("spiderID");
        Log.i("SPIDEX",texto);
        id=texto;

        if(texto!="666666") {
            getDatos(id);
        }else{
            getSpiderMoy();
        }

        return v;
    }

    private void getSpiderMoy() {
        name.setText("Spider Moy");
        status.setText("Dead");
        description.setText("En un universo alterno fuera de los comics, existe el hombre araña de la facultad de ciencias, el es Spider Moy");
    }

    public  void getDatos(String id){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = restApiAdapter.getCharacterService();
        retrofit2.Call<JsonObject> call = service.getDataPersonajeById(id,Constants.APIKEY,Constants.TS,Constants.HASH);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    assert response.body() != null;
                    JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject("data").toString());
                    JSONArray jsonArray   = jsonObject.getJSONArray("results");

                    parseCharacter(jsonArray);
                    setDatos(spider);

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

    public void parseCharacter(JSONArray jsonArray) throws JSONException{

        JSONObject character = jsonArray.getJSONObject(0);

        int id = character.getInt("id");
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

        spider = new Spider(id, name , description, path + "." + extension);
    }

    public void setDatos(Spider spider){
        name.setText(spider.getName());
        status.setText("Ok");
        description.setText(spider.getDescription());
        Glide.with(this)
                .load(spider.getThumnail())
                .into(image);


    }

}
