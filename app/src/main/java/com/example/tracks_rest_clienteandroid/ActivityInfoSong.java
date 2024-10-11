package com.example.tracks_rest_clienteandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityInfoSong extends AppCompatActivity {
    Button btnDelete;
    Button btnPut;
    EditText newtrackName;
    EditText newtrackSinger;
    TextView trackNameView;
    TextView trackSingerView;
    TextView trackIdView;

    String id;

    TracksService trservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_song);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener los datos del Intent
        Intent intent = getIntent();
        this.id = intent.getStringExtra("trackId");
        String trackName = intent.getStringExtra("trackName");
        String trackSinger = intent.getStringExtra("trackSinger");

        btnDelete = findViewById(R.id.btn_delete);
        btnPut = findViewById(R.id.btn_postSong);
        newtrackName=findViewById(R.id.song_name);
        newtrackSinger=findViewById(R.id.song_singer);
        trackNameView=findViewById(R.id.trackNameView);
        trackSingerView=findViewById(R.id.trackSingerView);
        trackIdView=findViewById(R.id.trackIdView);

        trackNameView.setText("Name: "+trackName);
        trackSingerView.setText("Singer: "+trackSinger);
        trackIdView.setText("Id: "+this.id);

        this.trservice=new TracksService();
    }

    public void onClickPut(View V){
        String name= newtrackName.getText().toString().trim();
        String singer= newtrackSinger.getText().toString().trim();
        trservice.put(this.id,name,singer);
        this.finish();
    }

    public void onClickDelete(View V){
        trservice.delete(this.id);
        this.finish();
    }
}