package com.example.macbookair.petagram.restapi.deserializador;

import com.example.macbookair.petagram.pojo.MascotaPerfil;
import com.example.macbookair.petagram.restapi.ConstantesJsonKeys;
import com.example.macbookair.petagram.restapi.modelo.PerfilResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by MacBookAir on 04/09/16.
 */
public class PerfilInstagramDeserializador implements JsonDeserializer<PerfilResponse> {
    @Override
    public PerfilResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

    Gson gson = new Gson();
    PerfilResponse perfilResponse = gson.fromJson(json, PerfilResponse.class);
    JsonArray perfilResponseData = json.getAsJsonObject().getAsJsonArray(ConstantesJsonKeys.MEDIA_RESPONSE_ARRAY);


    perfilResponse.setMiMascota(deserealizarMascotaDeJson(perfilResponseData));
    return perfilResponse;
}

    private ArrayList<MascotaPerfil> deserealizarMascotaDeJson(JsonArray perfilResponseData){
        ArrayList<MascotaPerfil> mascotas = new ArrayList<>();
        for (int i = 0; i < perfilResponseData.size(); i++) {
            JsonObject perfilResponseDataObject = perfilResponseData.get(i).getAsJsonObject();

            JsonObject mediaLikesJson = perfilResponseDataObject.getAsJsonObject(ConstantesJsonKeys.MEDIA_LIKES);
            int likes = mediaLikesJson.get(ConstantesJsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            JsonObject imageJson = perfilResponseDataObject.getAsJsonObject(ConstantesJsonKeys.MEDIA_IMAGES);
            JsonObject lowResolutionJson = imageJson.getAsJsonObject(ConstantesJsonKeys.MEDIA_IMAGES_RESOLUTION);
            String urlFoto = lowResolutionJson.get(ConstantesJsonKeys.MEDIA_URL).getAsString();

            JsonObject userJson = perfilResponseDataObject.getAsJsonObject(ConstantesJsonKeys.USER);
            String id = userJson.get(ConstantesJsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(ConstantesJsonKeys.USER_FULL_NAME).getAsString();

            MascotaPerfil mascotaActualPerfil = new MascotaPerfil();
            mascotaActualPerfil.setId(id);
            mascotaActualPerfil.setNombre(nombreCompleto);
            mascotaActualPerfil.setUrlFoto(urlFoto);
            mascotaActualPerfil.setLikes(likes);

            mascotas.add(mascotaActualPerfil);

        }
        return mascotas;
    }
}