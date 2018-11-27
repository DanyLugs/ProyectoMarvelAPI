package com.danylugo.bottomnavigationproyecto.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.Constants;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.RestApiAdapter;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.Service;
import com.danylugo.bottomnavigationproyecto.Model.Spider;
import com.danylugo.bottomnavigationproyecto.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonajeFragment extends Fragment {

    private String id;
    private Spider spider;

    private TextView name;
    private TextView tvStatus;
    private TextView tvComics;
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
        tvStatus = v.findViewById(R.id.status);
        tvComics = v.findViewById(R.id.comics);
        description = v.findViewById(R.id.description);
        image = v.findViewById(R.id.image);


        String texto = getArguments().getString("spiderID");
        Log.i("SPIDEX",texto);
        id = texto;

        assert id != null;
        if(!id.equals("666666")) {
            getDatos(id);
        }else{
            setSpiderMoy();
        }

        return v;
    }


    public void getDatos(String id){
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
                Log.e(Constants.TAG, " onResponse: " + t.getMessage());
                Toast toast = Toast.makeText( getActivity(), "Connection Failed", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void parseCharacter(JSONArray jsonArray) throws JSONException{

        JSONObject character = jsonArray.getJSONObject(0);

        int id = character.getInt("id");
        String name = character.getString("name");

        String description = "";

        description = character.getString("description");

        JSONObject objectImage = character.getJSONObject("thumbnail");

        String path = "";
        String extension = "";

        if (objectImage.getString("path") != "") {
            path = objectImage.getString("path");
            extension = objectImage.getString("extension");
        } else {
            path = "Image";
            extension = "Not Available";
        }

        JSONObject objectComic = character.getJSONObject("comics");

        int comics = 0; comics = objectComic.getInt("available");

        spider = new Spider(id, comics, name , description, path + "." + extension);
    }

    public void setDatos(Spider spider){
        name.setText(spider.getName());
        tvStatus.setText("Status: Alive");
        tvComics.setText("Appearances in Comics: "+spider.getComics());
        if (!spider.getDescription().equals("")) {
            description.setText("Description:\n" + "\n" + spider.getDescription());
        }else{
            description.setText("Description:\n" + "\n" + "Not available for this character");
        }
        if (spider.getId() == 1011347) {
            image.setImageResource(R.drawable.spider_ham);
        }else if(spider.getId() == 1010727){
            image.setImageResource(R.drawable.superior_spider_man);
        }else{
            Glide.with(this)
                    .load(spider.getThumnail())
                    .into(image);
        }
    }

    public void setSpiderMoy(){
        name.setText("Spider Moy");
        tvStatus.setText("Dead");
        tvComics.setText("Appearances in Comics: 1");
        description.setText("En un universo alterno fuera de los comics, existe el hombre araña de la facultad de ciencias, el es Spider Moy");
        image.setImageResource(R.drawable.smoy);
    }
}
