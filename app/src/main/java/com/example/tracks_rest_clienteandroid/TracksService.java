package com.example.tracks_rest_clienteandroid;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TracksService {
    Retrofit retrofit;
    Servidor serv;

    public TracksService(){
        //RetroFit
        retrofit =
                new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:8080/dsaApp/") //aqui posem lo de localhost bla bla bla
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        serv = retrofit.create(Servidor.class);
    }

    public void getAll(final TracksCallback callback){
        Call<List<Track>> call = serv.getTracks();

        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Track> tracks = response.body();
                    callback.onTracksLoaded(tracks);
                    // Handle the response
                    for (Track tr : tracks) {
                        Log.d("API_RESPONSE", "Track Name: " + tr.title);
                    }
                } else {
                    Log.d("API_RESPONSE", "Response not successful, code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                // Handle the error
                Log.e("API_ERROR", "API call failed", t);
                callback.onError("ERRORR onFAilure");
            }
        });
    }

    public void get(String _id){
        Call<Track> call = serv.getTrack(_id);

        call.enqueue(new Callback<Track>() {
            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Track tr = response.body();
                    // Handle the response
                    Log.d("API_RESPONSE", "Track Name: " + tr.title);
                } else {
                    Log.d("API_RESPONSE", "Response not successful, code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Track> call, Throwable t) {
                // Handle the error
                Log.e("API_ERROR", "API call failed", t);
            }
        });
    }

    public void post(String _id, String _title, String _singer){
        Track body = new Track(_id,_title,_singer);
        // Make the POST request
        Call<Track> call = serv.postTrack(body);
        call.enqueue(new Callback<Track>() {
            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {
                if (response.isSuccessful()) {
                    // Handle success
                    Log.d("API_RESPONSE", "POST SUCCESFULL");
                } else {
                    // Handle failure
                    Log.d("API_RESPONSE", "Response not successful, code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Track> call, Throwable t) {
                // Handle error
                Log.e("API_ERROR", "API call failed", t);
            }
        });
    }

    public void put(String _id, String _title, String _singer){
        Track body = new Track(_id,_title,_singer);
        // Make the POST request
        Call<Void> call = serv.putTrack(body);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle success
                    Log.d("API_RESPONSE", "PUT SUCCESFULL");
                } else {
                    // Handle failure
                    Log.d("API_RESPONSE", "Response not successful, code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle error
                Log.e("API_ERROR", "API call failed", t);
            }
        });
    }
    public void delete(String _id){
        Call<Void> call = serv.deleteTrack(_id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("API_RESPONSE", "DELETE SUCCESFUL");
                } else {
                    Log.d("API_RESPONSE", "Response not successful, code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle the error
                Log.e("API_ERROR", "API call failed", t);
            }
        });
    }
}
