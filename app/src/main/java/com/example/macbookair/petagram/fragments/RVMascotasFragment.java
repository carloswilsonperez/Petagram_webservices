package com.example.macbookair.petagram.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macbookair.petagram.pojo.Mascota;
import com.example.macbookair.petagram.R;
import com.example.macbookair.petagram.adapter.MascotaAdaptador;
import com.example.macbookair.petagram.presentador.IRVMascotasFragmentPresenter;
import com.example.macbookair.petagram.presentador.RVMascotasFragmentPresenter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RVMascotasFragment extends Fragment implements IRVMascotasFragmentView {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IRVMascotasFragmentPresenter presenter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View miView = inflater.inflate(R.layout.fragment_rvmascotas, container, false);
        listaMascotas = (RecyclerView) miView.findViewById(R.id.rvMascotas);
        presenter = new RVMascotasFragmentPresenter(this, getContext());

        // Inflamos el layout para este fragment
        return miView;


    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}
