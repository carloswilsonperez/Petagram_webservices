package com.example.macbookair.petagram.restapi;

/**
 * Created by MacBookAir on 03/09/16.
 */
public class ConstantesRestApi {public static final String ROOT_URL_VERSION = "https://api.instagram.com/v1/";
    public static final String ACCESS_TOKEN = "3807707578.d916a8c.d2559d3d7f294791af36da0990f226da";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";

    //https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN - Obtiene la Media mas reciente deL dueño del token -- utilizado para el Timeline
    public static final String DIR_GET_SELF_RECENT_MEDIA = "users/self/media/recent/";
    public static final String URL_GET_SELF_RECENT_MEDIA = ROOT_URL_VERSION + DIR_GET_SELF_RECENT_MEDIA + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN - Obtiene la Media mas reciente del dueño del user_ID especificado, en este caso de
    // "khalyluna" (mi cuenta), "courseramascotas" y "raulandroid10" que son los que autorizaron Sandbox -- lo usamos para generar el Timeline y las fotos de perfil fragment
    public static final String DIR_USERS = "users/";
    public static final String DIR_RECENT_MEDIA = "media/recent/";
    public static final String URL_PRE_USER_RECENT_MEDIA = ROOT_URL_VERSION + DIR_USERS;
    public static final String DIR_USER1 = "3715901350/"; // primer usuario que autorizo sandbox para obtener el ID usamos el endpoint https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
    public static final String DIR_USER2 = "3737484850/"; // segundo usuario que autorizo sandbox para obtener el ID usamos el endpoint https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
    public static final String URL_POST_USER_RECENT_MEDIA = DIR_RECENT_MEDIA + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_USER1_RECENT_MEDIA = URL_PRE_USER_RECENT_MEDIA + DIR_USER1 + URL_POST_USER_RECENT_MEDIA;
    public static final String URL_GET_USER2_RECENT_MEDIA = URL_PRE_USER_RECENT_MEDIA + DIR_USER2 + URL_POST_USER_RECENT_MEDIA;

    //https://api.instagram.com/v1/users/self/media/liked?access_token=ACCESS-TOKEN - Obtiene las imagenes a las que el dueño del token en este caso de la aplicacion, lo usaremos para
    // simular los valores en el Activity de mascotas favoritas
    public static final String DIR_GET_SELF_LIKED_MEDIA = "users/self/media/liked/";
    public static final String URL_GET_SELF_LIKED_MEDIA = ROOT_URL_VERSION + DIR_GET_SELF_LIKED_MEDIA + KEY_ACCESS_TOKEN + ACCESS_TOKEN;



}