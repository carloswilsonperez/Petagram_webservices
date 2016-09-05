package com.example.macbookair.petagram.restapi;

import com.example.macbookair.petagram.restapi.modelo.MascotaResponse;
import com.example.macbookair.petagram.restapi.modelo.PerfilResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by MacBookAir on 03/09/16.
 */
public interface EndpointApi {

    @GET(ConstantesRestApi.URL_GET_SELF_RECENT_MEDIA)
    Call<MascotaResponse> getSelfRecentMedia();

    @GET(ConstantesRestApi.URL_GET_SELF_RECENT_MEDIA)
    Call<PerfilResponse> getSelfRecentMediaPerfil();

    @GET(ConstantesRestApi.URL_GET_SELF_LIKED_MEDIA)
    Call<MascotaResponse> getSelfLikedMedia();
    //no aplica para el perfil

    @GET(ConstantesRestApi.URL_GET_USER1_RECENT_MEDIA)
    Call<MascotaResponse> getUser1RecentMedia();
    @GET(ConstantesRestApi.URL_GET_USER1_RECENT_MEDIA)
    Call<PerfilResponse> getUser1RecentMediaPerfil();

    @GET(ConstantesRestApi.URL_GET_USER2_RECENT_MEDIA)
    Call<MascotaResponse> getUser2RecentMedia();
    @GET(ConstantesRestApi.URL_GET_USER2_RECENT_MEDIA)
    Call<PerfilResponse> getUser2RecentMediaPerfil();

}
