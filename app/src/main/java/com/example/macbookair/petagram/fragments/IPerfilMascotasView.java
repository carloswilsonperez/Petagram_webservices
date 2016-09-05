package com.example.macbookair.petagram.fragments;

import com.example.macbookair.petagram.adapter.PerfilAdaptador;
import com.example.macbookair.petagram.pojo.MascotaPerfil;

import java.util.ArrayList;

/**
 * Created by MacBookAir on 04/09/16.
 */
public interface IPerfilMascotasView {
    public void generarGridLayout();

    public PerfilAdaptador crearAdaptador(ArrayList<MascotaPerfil> miMascota);

    public void inicializarAdaptadorRV(PerfilAdaptador adaptador);
}
