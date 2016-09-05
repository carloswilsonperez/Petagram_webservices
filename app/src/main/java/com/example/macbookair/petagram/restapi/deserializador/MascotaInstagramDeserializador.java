package com.example.macbookair.petagram.restapi.deserializador;

import com.example.macbookair.petagram.pojo.Mascota;
import com.example.macbookair.petagram.restapi.ConstantesJsonKeys;
import com.example.macbookair.petagram.restapi.modelo.MascotaResponse;
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
 * Created by MacBookAir on 03/09/16.
 */
public class MascotaInstagramDeserializador implements JsonDeserializer<MascotaResponse> {

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(ConstantesJsonKeys.MEDIA_RESPONSE_ARRAY);


        mascotaResponse.setMascotas(deserealizarMascotaDeJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<Mascota> deserealizarMascotaDeJson(JsonArray mascotaResponseData){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size(); i++) {
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();

            JsonObject mediaLikesJson = mascotaResponseDataObject.getAsJsonObject(ConstantesJsonKeys.MEDIA_LIKES);
            int likes = mediaLikesJson.get(ConstantesJsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            JsonObject imageJson = mascotaResponseDataObject.getAsJsonObject(ConstantesJsonKeys.MEDIA_IMAGES);
            JsonObject lowResolutionJson = imageJson.getAsJsonObject(ConstantesJsonKeys.MEDIA_IMAGES_RESOLUTION);
            String urlFoto = lowResolutionJson.get(ConstantesJsonKeys.MEDIA_URL).getAsString();

            JsonObject userJson = mascotaResponseDataObject.getAsJsonObject(ConstantesJsonKeys.USER);
            String id = userJson.get(ConstantesJsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(ConstantesJsonKeys.USER_FULL_NAME).getAsString();

            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(id);
            mascotaActual.setNombre(nombreCompleto);
            mascotaActual.setUrlFoto(urlFoto);
            mascotaActual.setLikes(likes);

            mascotas.add(mascotaActual);

        }
        return mascotas;
    }
}
