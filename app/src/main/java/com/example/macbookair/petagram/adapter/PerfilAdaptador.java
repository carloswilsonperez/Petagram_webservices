package com.example.macbookair.petagram.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.macbookair.petagram.pojo.MascotaPerfil;
import com.example.macbookair.petagram.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MacBookAir on 22/08/16.
 */
public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder> {

    ArrayList<MascotaPerfil> miMascota = new ArrayList<MascotaPerfil>();
    Activity activity;

    public PerfilAdaptador(ArrayList<MascotaPerfil> miMascota, Activity activity){
        this.miMascota = miMascota;
        this.activity = activity;
    }

    //Aqui esta el ViewHolder
    //va a Inflar (rellenar) el layout y lo pasara al viewholder para que el obtenga los views
    @Override
    public PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil, parent, false); //Esta linea asocia con el layout que estara reciclando

        return new PerfilViewHolder(v);
    }

    //Asocia cada elemento de la lista con cada View
    @Override
    public void onBindViewHolder(PerfilViewHolder perfilViewHolder, int position) {
        MascotaPerfil pMascota = miMascota.get(position);
        Picasso.with(activity).load(pMascota.getUrlFoto()).placeholder(R.drawable.dog1).into(perfilViewHolder.imgFoto);
        perfilViewHolder.tvLikesCV.setText(String.valueOf(pMascota.getLikes()));


    }

    @Override
    public int getItemCount() { //Tiene la cantidad de elementos que contiene mi lista
        if (miMascota != null){
        return miMascota.size();}
        else{
            return 0;
        }
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvLikesCV;

        public PerfilViewHolder(View itemView) {
            super(itemView);

            imgFoto     =(ImageView) itemView.findViewById(R.id.imgFoto);
            tvLikesCV=(TextView) itemView.findViewById(R.id.tvLikesCV);

        }
    }
}
