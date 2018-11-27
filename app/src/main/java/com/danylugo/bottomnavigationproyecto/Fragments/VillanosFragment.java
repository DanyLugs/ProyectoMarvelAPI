package com.danylugo.bottomnavigationproyecto.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danylugo.bottomnavigationproyecto.Adapter.SecondaryListAdapter;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.Constants;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.RestApiAdapter;
import com.danylugo.bottomnavigationproyecto.MarvrelAPI.Service;
import com.danylugo.bottomnavigationproyecto.Model.Secondary;
import com.danylugo.bottomnavigationproyecto.R;

import com.danylugo.bottomnavigationproyecto.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class VillanosFragment extends Fragment {

    private List<Secondary> enemies;
    private RecyclerView enemiesRecycler;
    private SecondaryListAdapter mAdapterE;

    private String id;

    public VillanosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_villanos, container, false);
        enemies = new ArrayList<>();
        enemiesRecycler= (RecyclerView) view.findViewById(R.id.recyclerV);
        enemiesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        String texto = getArguments().getString("spiderID");
        Log.i("SPIDEX",texto);
        id = texto;

        mAdapterE =new SecondaryListAdapter(enemies,this);
        enemiesRecycler.setAdapter(mAdapterE);

        assert id != null;
        if(!id.equals("666666")) {
            ArrayList<String> ids = getSecondarysID(id);
            getDatos(ids);
        }else{
            getDatosSpiderMoy();
        }


        return view;
    }

    private void getDatosSpiderMoy() {
        //aqui agreguen las de spider moy :V
        enemies.add(new Secondary("Dany Lugo","https://scontent.fmex1-1.fna.fbcdn.net/v/t1.0-9/1931526_980768155306325_8598870222290075680_n.jpg?_nc_cat=104&_nc_ht=scontent.fmex1-1.fna&oh=8a333e3b76d90d850daf8b6533873572&oe=5CA46D8B"));
    }

    public void getDatos(final ArrayList<String> ids){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = restApiAdapter.getCharacterService();
        for( int i = 0; i< ids.size(); i++){
            String id = ids.get(i);
            retrofit2.Call<JsonObject> call = service.getDataPersonajeById(id, Constants.APIKEY, Constants.TS, Constants.HASH);

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    try {
                        assert response.body() != null;
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject("data").toString());
                        JSONArray jsonArray = jsonObject.getJSONArray("results");

                        parseCharacter(jsonArray);
                        mAdapterE.adiccionarLista(enemies);

                        enemies.remove(0);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e(Constants.TAG, " onResponse: " + t.getMessage());
                }
            });
        }
    }

    public void parseCharacter(JSONArray jsonArray) throws JSONException{

        JSONObject character = jsonArray.getJSONObject(0);

        String name = character.getString("name");

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

        enemies.add(new Secondary(name ,path + "." + extension));
    }

    public ArrayList<String> getSecondarysID(String id) {
        ArrayList<String> enemiesId = new ArrayList<>();
        switch (id) {
            case "1009610":
                enemiesId.add("1009610");
                enemiesId.add("1014873");
                break;
            default:
                enemiesId.add("1016181");
                enemiesId.add("1009608");
                break;
        }
        return enemiesId;
    }

}
