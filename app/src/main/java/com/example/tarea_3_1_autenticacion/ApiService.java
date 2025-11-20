package com.example.tarea_3_1_autenticacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    // LOGIN
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseData> login(
            @Field("usuario") String usuario,
            @Field("password") String password
    );

    // AGREGAR
    @FormUrlEncoded
    @POST("agregar.php")
    Call<ResponseData> agregarProducto(
            @Field("nombre") String nombre,
            @Field("precio") double precio,
            @Field("cantidad") int cantidad
    );

    // LISTAR
    @GET("listar.php")
    Call<List<Producto>> listarProductos();

    // ACTUALIZAR
    @FormUrlEncoded
    @POST("actualizar.php")
    Call<ResponseData> actualizarProducto(
            @Field("id") int id,
            @Field("nombre") String nombre,
            @Field("precio") double precio,
            @Field("cantidad") int cantidad
    );

    // ELIMINAR
    @FormUrlEncoded
    @POST("eliminar.php")
    Call<ResponseData> eliminarProducto(
            @Field("id") int id
    );
}
