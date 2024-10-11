package com.example.tracks_rest_clienteandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements TracksCallback {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ImageView newSong;

    List<Track> obtainedTracks;
    TracksService trservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //VARIABLES MAIN
        this.obtainedTracks=new ArrayList<>();
        newSong=findViewById(R.id.imageView);

        //INSTANCIA TRACKSERVICE
        trservice=new TracksService();

        //RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(MainActivity.this,obtainedTracks);
        recyclerView.setAdapter(mAdapter);

        getAllTracks();
        //postTrack("1235","Bellaqueando","Falks");
        //postTrack("1232","Bellaquita","Falks");
        //postTrack("1236","Gasisme","Falks");
        //putTrack("1236","GasismeV2","Falks");
        //deleteTrack("1235");

        //IMAGEVIEW CLICK
        newSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityNewsong.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    public void getAllTracks(){
        trservice.getAll(this);
    }

    public void getTrack(String _id){

    }

    public void postTrack(String _id, String _title, String _singer){

    }
    public void putTrack(String _id, String _title, String _singer){

    }

    public void deleteTrack(String _id){


    }
    @Override
    public void onTracksLoaded(List<Track> tracks) {
        // Actualizar la lista de tracks y notificar al adapter
        obtainedTracks.clear();
        obtainedTracks.addAll(tracks);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(MainActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        getAllTracks();
    }
}