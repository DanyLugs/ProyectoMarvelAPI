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
        if(!id.equals("666666") && !id.equals("1011347")) {
            ArrayList<String> ids = getSecondarysID(id);
            getDatos(ids);
        }else{
            getDatosSpiderMoy(id);
        }


        return view;
    }

    private void getDatosSpiderMoy(String id) {

        switch (id) {
            case "1011347": //Spider-Ham
                enemies.add(new Secondary("Doctor Octopussycat", "https://static.comicvine.com/uploads/square_small/3/33023/1433783-oct_02.jpg"));
                enemies.add(new Secondary("Ducktor Doom", "https://static.comicvine.com/uploads/square_small/0/77/1255268-60526_3499_93521_1_peter_porker_the_sp_super.jpg"));
                enemies.add(new Secondary("Electro", "https://static.comicvine.com/uploads/square_small/3/33023/1433777-elec_02.jpg"));
                enemies.add(new Secondary("Flash Tombenstain Bear", "https://static.comicvine.com/uploads/square_small/13/135098/5928782-flash-bear.jpg"));
                enemies.add(new Secondary("Green Gobbler", "https://static.comicvine.com/uploads/square_small/3/33023/1433798-gg_02.jpg"));
                enemies.add(new Secondary("J. Jonah Jackal", "https://static.comicvine.com/uploads/square_small/13/135098/5076319-812786-jackal.jpg"));
                enemies.add(new Secondary("Mooseterio", "https://static.comicvine.com/uploads/square_small/11/114254/2250168-mooseterio__earth_8311_.jpg"));
                enemies.add(new Secondary("Sandmanatee", "https://static.comicvine.com/uploads/square_small/3/33023/1434233-sandmanatee_02.jpg"));
                enemies.add(new Secondary("Pork Grind", "https://static.comicvine.com/uploads/square_small/3/33023/2478955-venom_pork_grind.jpg"));
                enemies.add(new Secondary("The Pinhead Of Crime", "https://static.comicvine.com/uploads/square_small/10/107504/6120522-pinhead+of+crime.jpg"));
                break;


            default: //Spider Moy
                enemies.add(new Secondary("Dany Lugo", "https://scontent.fmex1-1.fna.fbcdn.net/v/t1.0-9/1931526_980768155306325_8598870222290075680_n.jpg?_nc_cat=104&_nc_ht=scontent.fmex1-1.fna&oh=8a333e3b76d90d850daf8b6533873572&oe=5CA46D8B"));
        }
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
        switch (id){
            case "1009610":
                enemiesId.add("1009325");
                enemiesId.add("1009276 ");
                enemiesId.add("1009479");
                enemiesId.add("1009347");
                enemiesId.add("1011128");
                enemiesId.add("1009227");
                enemiesId.add("1009287");
                enemiesId.add("1009391");
                enemiesId.add("1009404");
                enemiesId.add("1009537");
                enemiesId.add("1009558");
                enemiesId.add("1009360");
                enemiesId.add("1009464");
                enemiesId.add("1009699");
                enemiesId.add("1009234");
                enemiesId.add("1011088");
                enemiesId.add("1011426");
                enemiesId.add("1009585");
                enemiesId.add("1010773");
                break;

            case "1014873":
                enemiesId.add("1009227");
                enemiesId.add("1009281");
                enemiesId.add("1010324");
                enemiesId.add("1009276");
                enemiesId.add("1010922");
                enemiesId.add("1010931");
                enemiesId.add("1011088");
                enemiesId.add("1009517");
                enemiesId.add("1011079");
                enemiesId.add("1009314");
                enemiesId.add("1011128");
                enemiesId.add("1010990");
                break;

            case "1016181":
                enemiesId.add("1009154");
                enemiesId.add("1009185");
                enemiesId.add("1009252");
                enemiesId.add("1010922");
                enemiesId.add("1010930");
                enemiesId.add("1010842");
                enemiesId.add("1009447");
                enemiesId.add("1009464");
                enemiesId.add("1009325");
                enemiesId.add("1009507");
                enemiesId.add("1009511");
                enemiesId.add("1009537");
                enemiesId.add("1009558");
                enemiesId.add("1011079");
                break;

            case "1009608":
                enemiesId.add("1010773");
                enemiesId.add("1009164");
                enemiesId.add("1010906");
                enemiesId.add("1009203");
                enemiesId.add("1010887");
                enemiesId.add("1009276");
                enemiesId.add("1009287");
                enemiesId.add("1011420");
                enemiesId.add("1011243");
                enemiesId.add("1010362");
                enemiesId.add("1009325");
                enemiesId.add("1009571");
                enemiesId.add("1011003");
                enemiesId.add("1011128");
                enemiesId.add("1009696");
                break;

            case "1011426":
                enemiesId.add("1010906");
                enemiesId.add("1011346");
                enemiesId.add("1009227");
                enemiesId.add("1009276");
                enemiesId.add("1009287");
                enemiesId.add("1010687");
                enemiesId.add("1011288");
                enemiesId.add("1009391");
                enemiesId.add("1011088");
                enemiesId.add("1009537");
                enemiesId.add("1009157");
                enemiesId.add("1009610");
                enemiesId.add("1010687");
                enemiesId.add("1009699");
                break;

            case "1011010":
                enemiesId.add("1014985");
                enemiesId.add("1009507");
                enemiesId.add("1011128");
                enemiesId.add("1011079");
                enemiesId.add("1009389");
                enemiesId.add("1009464");
                enemiesId.add("1009227");
                enemiesId.add("1009585");
                enemiesId.add("1009334");
                enemiesId.add("1009675");
                break;


            case "1011114": //Spider-Man (Marvel Zombies)
                enemiesId.add("1009718");
                break;

            case "1010727": //Spider-Dok (Superior Spider-Man)
                enemiesId.add("1010371");
                enemiesId.add("1009585");
                enemiesId.add("1011032");
                break;


            case "1012295": //Spider-Man (Noir)
                enemiesId.add("1009391");
                enemiesId.add("1009325");
                enemiesId.add("1009699");
                enemiesId.add("1009234");
                enemiesId.add("1009276");
                break;


            case "1009609": //Spider-Girl (May Parker)
                enemiesId.add("1009227");
                enemiesId.add("1009390");
                enemiesId.add("1011247");
                enemiesId.add("1010687 ");
                enemiesId.add("1009322");
                enemiesId.add("1009347");
                enemiesId.add("1011426");
                enemiesId.add("1011088");
                enemiesId.add("1009325");
                enemiesId.add("1009391");
                break;


            case "1011197": //Scarlet Spider (Ben Reilly)
                enemiesId.add("1010766");
                enemiesId.add("1009697");
                enemiesId.add("1009227");
                enemiesId.add("1009391");
                enemiesId.add("1010930");
                enemiesId.add("1011288");
                enemiesId.add("1009380");
                enemiesId.add("1011426");
                enemiesId.add("1009404");
                enemiesId.add("1011088");
                enemiesId.add("1009464");
                enemiesId.add("1009325");
                enemiesId.add("1010861");
                enemiesId.add("1010790");
                enemiesId.add("1009566");
                enemiesId.add("1009699");
                break;

            default:

        }
        return enemiesId;
    }

}
