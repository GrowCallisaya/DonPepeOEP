package com.oep.pruebaoep1.networking.clients;

import com.oep.pruebaoep1.models.Producto;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by growcallisaya on 6/4/17.
 */
public class OEPService {
    private Api mainApi;
    public Retrofit retrofit;
    public OEPService() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.102:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mainApi = retrofit.create(Api.class);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public Api getApi() {
        return mainApi;
    }
}
