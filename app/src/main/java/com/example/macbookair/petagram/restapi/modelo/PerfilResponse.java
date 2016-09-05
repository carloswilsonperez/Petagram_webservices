package com.example.macbookair.petagram.restapi.modelo;

import com.example.macbookair.petagram.pojo.MascotaPerfil;

import java.util.ArrayList;

/**
 * Created by MacBookAir on 04/09/16.
 */
public class PerfilResponse {
    ArrayList<MascotaPerfil> miMascota;
    public ArrayList<MascotaPerfil> getMiMascota() {
        return miMascota;
    }

    public void setMiMascota(ArrayList<MascotaPerfil> miMascota) {
        this.miMascota = miMascota;
    }
}
