package com.example.macbookair.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.macbookair.petagram.fragments.IPerfilMascotasView;
import com.example.macbookair.petagram.pojo.MascotaPerfil;
import com.example.macbookair.petagram.restapi.EndpointApi;
import com.example.macbookair.petagram.restapi.adapter.RestApiPerfilAdapter;
import com.example.macbookair.petagram.restapi.modelo.PerfilResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MacBookAir on 04/09/16.
 */
public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {
    private IPerfilMascotasView iPerfilMascotasView;
    private Context context;
    public ArrayList<MascotaPerfil> miMascota;

    public PerfilFragmentPresenter(IPerfilMascotasView iPerfilMascotasView, Context context) {
        this.iPerfilMascotasView = iPerfilMascotasView;
        this.context = context;
        obtenerMascotasMediosRecientes();
    }

    @Override
    public void obtenerMascotasMediosRecientes() {
        RestApiPerfilAdapter restApiPerfilAdapter = new RestApiPerfilAdapter();
        Gson gsonMediaRecent = restApiPerfilAdapter.construyeGsonDeserializadorPerfilMediaRecent();
        EndpointApi endpointApi = restApiPerfilAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<PerfilResponse> perfilResponseCall = endpointApi.getSelfRecentMediaPerfil();

        perfilResponseCall.enqueue(
                new Callback<PerfilResponse>() {

                    @Override
                    public void onResponse(Call<PerfilResponse> call, Response<PerfilResponse> response) {
                        PerfilResponse perfilResponse = response.body();
                        miMascota = perfilResponse.getMiMascota();
                        mostrarMascotasRV();
                    }

                    @Override
                    public void onFailure(Call<PerfilResponse> call, Throwable t) {
                        Toast.makeText(context, "Hubo un error en la conexion", Toast.LENGTH_SHORT).show();
                        Log.e("Fallo en MResponse", t.toString());
                    }
                }
        );
    }

    @Override
    public void mostrarMascotasRV() {
            iPerfilMascotasView.inicializarAdaptadorRV(iPerfilMascotasView.crearAdaptador(miMascota));
            iPerfilMascotasView.generarGridLayout();
    }
}
