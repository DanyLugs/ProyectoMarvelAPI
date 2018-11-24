package com.danylugo.bottomnavigationproyecto.MarvrelAPI;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Service {

    @GET(Constants.REST_ENDPOINT)
    Call<JsonObject> getDataPersonaje(@Query("apikey") String apikey, @Query("ts") String ts,@Query("hash")String hash);
}
