package com.example.macbookair.petagram.restapi.modelo;

import com.example.macbookair.petagram.pojo.Mascota;
import com.example.macbookair.petagram.pojo.MascotaPerfil;

import java.util.ArrayList;

/**
 * Created by MacBookAir on 03/09/16.
 */
public class MascotaResponse {

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }


}