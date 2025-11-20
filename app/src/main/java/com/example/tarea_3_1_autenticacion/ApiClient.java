package com.example.tarea_3_1_autenticacion;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // CAMBIA esta URL por la IP de tu servidor (XAMPP, Laragon, hosting, etc.)
    private static final String BASE_URL = "http://192.168.0.5/PHP_Backend/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
