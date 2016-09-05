package com.example.macbookair.petagram;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbookair.petagram.adapter.MascotaAdaptador;
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


//RecyclerView con 5 Items

public class MascotasFavoritas extends AppCompatActivity implements IRVMascotasFragmentView {
    ArrayList<Mascota> mascotasFav;

    private RecyclerView listaMascotas;
    public MascotaAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        miActionBar.setLogo(R.drawable.dog_footprint_24);
        setSupportActionBar(miActionBar);
        TextView tituloAppBar = (TextView) findViewById(R.id.tvTituloApp); //Para cambiar el titulo del AppBar al nombre del activity
        tituloAppBar.setText(R.string.ab_titulo_Favoritos);

        //Inicia codigo boton de subir, ya declaramos la clase en el manifest como hija
        // Obtenemos support ActionBar correspondiente a esta toolbar
        ActionBar miUpButton = getSupportActionBar();
        // Activamos el Boton de subir
        miUpButton.setDisplayHomeAsUpEnabled(true);
        //Creamos el recyclerView
        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotasFav);

        obtenerMascotasMediosRecientes();
        //inicializarAdaptadorRV();


    }

    //Comienza codigo para activar el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toolbar tb = (Toolbar) findViewById(R.id.miActionBar);
        tb.inflateMenu(R.menu.menufavoritos);
        tb.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return onOptionsItemSelected(item);
                    }
                });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mContacto:
                Intent intentContacto = new Intent(MascotasFavoritas.this, ContactoActivity.class);
                startActivity(intentContacto);
                finish();
                return true;

            case R.id.mAcercaDe:
                // En caso que el usuario haga click en el menu, ir al Activity de Acerca de...
                Intent intentADA = new Intent(MascotasFavoritas.this, AcercaDeActivity.class);
                startActivity(intentADA);
                finish();
                return true;

            case R.id.mConfigurarCuenta:
                // En caso que el usuario haga click en el menu, ir al Activity de Acerca de...
                Intent intentCCA = new Intent(MascotasFavoritas.this, AcercaDeActivity.class);
                startActivity(intentCCA);
                finish();
                return true;

            default:
                // Si llegamos aqui, la entrada del usuario no fue reconocida.
                // Invocamos a la superclase para que la maneje.
                return super.onOptionsItemSelected(item);

        }
    }

    public void obtenerMascotasMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointApi endpointApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointApi.getSelfLikedMedia();

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotasFav = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(MascotasFavoritas.this, "Hubo un error en la conexion", Toast.LENGTH_LONG).show();
                Log.e("Fallo en MResponse", t.toString());
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){

        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(MascotasFavoritas.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        adaptador = new MascotaAdaptador(mascotasFav, this);
        listaMascotas.setAdapter(adaptador);
    }

    public void mostrarMascotasRV() {
        inicializarAdaptadorRV(crearAdaptador(mascotasFav));
        generarLinearLayoutVertical();
    }
}