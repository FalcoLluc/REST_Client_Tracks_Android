package com.example.tracks_rest_clienteandroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Servidor {
    @GET("tracks")
    Call<List<Track>> getTracks();

    @GET("tracks/{id}")
    Call<Track> getTrack(@Path("id") String id);

    //DELETE
    @DELETE("tracks/{id}")
    Call<Void> deleteTrack(@Path("id") String id);

    //PUT
    @PUT("tracks")
    Call<Void> putTrack(@Body Track track);

    //POST
    @POST("tracks")
    Call<Track> postTrack(@Body Track track);
}
