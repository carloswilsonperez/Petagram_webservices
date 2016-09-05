package com.example.macbookair.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.macbookair.petagram.fragments.IRVMascotasFragmentView;
import com.example.macbookair.petagram.pojo.Mascota;
import com.example.macbookair.petagram.restapi.EndpointApi;
import com.example.macbookair.petagram.restapi.adapter.RestApiAdapter;
import com.example.macbookair.petagram.restapi.modelo.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MacBookAir on 03/09/16.
 */
public class RVMascotasFragmentPresenter implements IRVMascotasFragmentPresenter {
    private IRVMascotasFragmentView iRVMascotasFragmentView;
    private Context context;
    public ArrayList<Mascota> mascotas;
    public ArrayList<Mascota> mascotas1;
    public ArrayList<Mascota> mascotas2;

    public RVMascotasFragmentPresenter(IRVMascotasFragmentView iRVMascotasFragmentView, Context context) {
        this.iRVMascotasFragmentView = iRVMascotasFragmentView;
        this.context = context;
        obtenerMascotasMediosRecientes();
    }

    @Override
    public void obtenerMascotasMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointApi endpointApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointApi.getSelfRecentMedia();

        mascotaResponseCall.enqueue(
                new Callback<MascotaResponse>() {

                    @Override
                    public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                        MascotaResponse mascotaResponse = response.body();
                        mascotas = mascotaResponse.getMascotas();
                        obtenerMascotasMediosRecientesUsuario1();
                    }

                    @Override
                    public void onFailure(Call<MascotaResponse> call, Throwable t) {
                        Toast.makeText(context, "Hubo un error en la conexion", Toast.LENGTH_SHORT).show();
                        Log.e("Fallo en MResponse", t.toString());
                    }
                }
        );

    }

    public void obtenerMascotasMediosRecientesUsuario1() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointApi endpointApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCallAllUsers1 = endpointApi.getUser1RecentMedia();

        mascotaResponseCallAllUsers1.enqueue(
                new Callback<MascotaResponse>() {
                    @Override
                    public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                        MascotaResponse mascotaResponse = response.body();
                        mascotas1 = mascotaResponse.getMascotas();
                        mascotas.addAll(mascotas1);
                        obtenerMascotasMediosRecientesUsuario2();
                    }

                    @Override
                    public void onFailure(Call<MascotaResponse> call, Throwable t) {
                        Toast.makeText(context, "Hubo un error en la conexion", Toast.LENGTH_SHORT).show();
                        Log.e("Fallo en MResponse", t.toString());
                    }
                }
        );

    }

    public void obtenerMascotasMediosRecientesUsuario2() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointApi endpointApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCallAllUsers2 = endpointApi.getUser2RecentMedia();

        mascotaResponseCallAllUsers2.enqueue(
                new Callback<MascotaResponse>() {
                    @Override
                    public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                        MascotaResponse mascotaResponse = response.body();
                        mascotas2 = mascotaResponse.getMascotas();
                        mascotas.addAll(mascotas2);
                        mostrarMascotasRV();
                    }

                    @Override
                    public void onFailure(Call<MascotaResponse> call, Throwable t) {
                        Toast.makeText(context, "Hubo un error en la conexion", Toast.LENGTH_SHORT).show();
                        Log.e("Fallo en MResponse", t.toString());
                    }
                }
        );

    }

    @Override
    public void mostrarMascotasRV() {
        iRVMascotasFragmentView.inicializarAdaptadorRV(iRVMascotasFragmentView.crearAdaptador(mascotas));
        iRVMascotasFragmentView.generarLinearLayoutVertical();
    }

}

