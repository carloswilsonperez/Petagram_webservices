package com.example.macbookair.petagram.pojo;

/**
 * Created by MacBookAir on 22/08/16.
 */
public class MascotaPerfil {
    private String Nombre;
    private String Id;
    private String urlFoto;
    private int Likes;


    public MascotaPerfil(String foto, String nombre, int likes) {
        this.urlFoto = foto;
        this.Nombre = nombre;
        this.Likes = likes;

    }

    public MascotaPerfil() {
        //Constructor vacio requerido para el llenado de datos desde el web Service
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getLikes() {
        return Likes;
    }

    public void setLikes(int likes) {
        Likes = likes;
    }
}
