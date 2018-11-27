package com.danylugo.bottomnavigationproyecto.Fragments;


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
import com.danylugo.bottomnavigationproyecto.Model.Spider;
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
public class AliadosFragment extends Fragment {

    private List<Secondary> allies;
    private RecyclerView alliesRecycler;
    private SecondaryListAdapter mAdapterA;

    private  String id;


    public AliadosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aliados, container, false);
        allies = new ArrayList<>();
        alliesRecycler= (RecyclerView) view.findViewById(R.id.recyclerA);
        alliesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        String texto = getArguments().getString("spiderID");
        Log.i("SPIDEX",texto);
        id = texto;

        mAdapterA =new SecondaryListAdapter(allies,this);
        alliesRecycler.setAdapter(mAdapterA);

        assert id != null;
        if(!id.equals("666666")) {
            ArrayList<String> ids = getAlliesID(id);
            getDatos(ids);
        }else{
            getDatosSpiderMoy();
        }

        return view;
    }

    private void getDatosSpiderMoy() {
        //aqui agreguen las de spider moy :V
        allies.add(new Secondary("Joan Guerrero","https://scontent.fmex1-1.fna.fbcdn.net/v/t1.0-9/23319326_1483952015016277_3992862400160235205_n.jpg?_nc_cat=108&_nc_ht=scontent.fmex1-1.fna&oh=ed8393c936da7707c70ef80df2d287dc&oe=5C742B3D"));
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
                        mAdapterA.adiccionarLista(allies);

                        allies.remove(0);

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

        allies.add(new Secondary(name ,path + "." + extension));
    }

    public ArrayList<String> getAlliesID(String id) {
        // aqui los de los demas
        ArrayList<String> alliesId = new ArrayList<>();
        switch (id) {
            case "1009610":
                alliesId.add("1009708");
                alliesId.add("1009372");
                alliesId.add("1009490");
                alliesId.add("1009663");
                alliesId.add("1009545");
                alliesId.add("1010784");
                alliesId.add("1010325");
                alliesId.add("1009489");
                break;
            case "1014873":
                alliesId.add("1009281");
                alliesId.add("1009608");
                alliesId.add("1009288");
                alliesId.add("1009517");
                break;
            case "1016181":
                alliesId.add("1009471");
                alliesId.add("1009189");
                alliesId.add("1009220");
                alliesId.add("1010828");
                alliesId.add("1009257");
                alliesId.add("1009619");
                alliesId.add("1009368");
                alliesId.add("1009338");
                alliesId.add("1009708");
                alliesId.add("1009471");
                alliesId.add("1009297");
                break;

            default:
                alliesId.add("1016181");
                alliesId.add("1009608");
                break;
        }
        return alliesId;
    }
}

