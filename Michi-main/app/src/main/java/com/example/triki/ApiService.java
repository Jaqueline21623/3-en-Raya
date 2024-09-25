package com.example.triki;



import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("guardar_ganador.php") // Nombre del script PHP
    Call<Void> guardarGanador(@Field("jugador1") String jugador1, @Field("jugador2") String jugador2, @Field("ganador") String ganador);
}