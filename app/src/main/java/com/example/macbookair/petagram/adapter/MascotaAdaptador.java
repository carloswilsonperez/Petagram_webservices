package com.example.macbookair.petagram.adapter;

/**
 * Created by MacBookAir on 15/08/16.
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbookair.petagram.pojo.Mascota;
import com.example.macbookair.petagram.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


//Clase para dar forma a los items del RecyclerView


public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }



    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false); //Esta linea asocia con el layout que estara reciclando

        return new MascotaViewHolder(v);
    }

    //Asocia cada elemento de la lista con cada View
    @Override
    public void onBindViewHolder(MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        Picasso.with(activity).load(mascota.getUrlFoto()).placeholder(R.drawable.dog1).into(mascotaViewHolder.imgFoto);
        mascotaViewHolder.tvNombreCV.setText(mascota.getNombre());
        mascotaViewHolder.tvLikesCV.setText(String.valueOf(mascota.getLikes()));

        mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Diste Like a " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
                int liked = mascota.getLikes();
                liked = liked +1;
                mascota.setLikes(liked);
                notifyDataSetChanged();
            }

        });
    }

    @Override
    public int getItemCount() { //Tiene la cantidad de elementos que contiene mi lista
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvLikesCV;
        private ImageButton btnLike;


        public MascotaViewHolder(View itemView) {
            super(itemView);

            imgFoto     =(ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCV  =(TextView) itemView.findViewById(R.id.tvNombreCV);
            tvLikesCV=(TextView) itemView.findViewById(R.id.tvLikesCV);
            btnLike     =(ImageButton) itemView.findViewById(R.id.btnLike);

        }
    }
}

