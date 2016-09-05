package com.example.macbookair.petagram.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbookair.petagram.ConfigurarCuentaActivity;
import com.example.macbookair.petagram.adapter.MascotaAdaptador;
import com.example.macbookair.petagram.pojo.Mascota;
import com.example.macbookair.petagram.pojo.MascotaPerfil;
import com.example.macbookair.petagram.R;
import com.example.macbookair.petagram.adapter.PerfilAdaptador;
import com.example.macbookair.petagram.presentador.IPerfilFragmentPresenter;
import com.example.macbookair.petagram.presentador.PerfilFragmentPresenter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements IPerfilMascotasView {

    private ArrayList<MascotaPerfil> miMascota;
    private RecyclerView listaFotosPerfil;
    private IPerfilFragmentPresenter presenter;
    private TextView tvNombre;
    public String NombreCuenta;
    ConfigurarCuentaActivity activityConfig = new ConfigurarCuentaActivity();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View miView = inflater.inflate(R.layout.fragment_perfil, container, false);
        changeAccount(miView);
        tvNombre = (TextView) miView.findViewById(R.id.NombreMiMascota);
        listaFotosPerfil = (RecyclerView) miView.findViewById(R.id.rvPerfil);
        presenter = new PerfilFragmentPresenter(this, getContext());

        return miView;
    }

    public View changeAccount(View v){
        NombreCuenta = activityConfig.getNombreCuenta();
        tvNombre = (TextView) v.findViewById(R.id.NombreMiMascota);
        tvNombre.setText(NombreCuenta);

        return v;
    }

    @Override
    public void generarGridLayout() {

        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        listaFotosPerfil.setLayoutManager(glm);
    }

    @Override
    public PerfilAdaptador crearAdaptador(ArrayList<MascotaPerfil> miMascota) {
        PerfilAdaptador perfilAdaptador = new PerfilAdaptador(miMascota, getActivity());
        return perfilAdaptador;
    }

    @Override
    public void inicializarAdaptadorRV(PerfilAdaptador adaptador) {
        listaFotosPerfil.setAdapter(adaptador);
    }


}
