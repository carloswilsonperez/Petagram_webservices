package com.example.macbookair.petagram.restapi.adapter;

import com.example.macbookair.petagram.restapi.ConstantesRestApi;
import com.example.macbookair.petagram.restapi.EndpointApi;
import com.example.macbookair.petagram.restapi.deserializador.PerfilInstagramDeserializador;
import com.example.macbookair.petagram.restapi.modelo.PerfilResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MacBookAir on 04/09/16.
 */
public class RestApiPerfilAdapter {  public EndpointApi establecerConexionRestApiInstagram (Gson gson){
    Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstantesRestApi.ROOT_URL_VERSION).addConverterFactory(GsonConverterFactory.create(gson)).build();

    return retrofit.create(EndpointApi.class);
}

    public Gson construyeGsonDeserializadorPerfilMediaRecent() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PerfilResponse.class, new PerfilInstagramDeserializador());
        return gsonBuilder.create();
    }
}