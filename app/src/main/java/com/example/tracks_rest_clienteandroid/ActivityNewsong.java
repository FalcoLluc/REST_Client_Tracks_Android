package com.example.tracks_rest_clienteandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityNewsong extends AppCompatActivity {
    Button btnOpenUpload;
    EditText textTrackId;
    EditText textTrackName;
    EditText textTrackSinger;

    TracksService trservice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_newsong);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnOpenUpload = findViewById(R.id.btn_postSong);
        textTrackId=findViewById(R.id.song_id);
        textTrackName=findViewById(R.id.song_name);
        textTrackSinger=findViewById(R.id.song_singer);

        trservice=new TracksService();
    }

    public void onClick(View V){
        String id= textTrackId.getText().toString().trim();
        String name= textTrackName.getText().toString().trim();
        String singer= textTrackSinger.getText().toString().trim();
        trservice.post(id,name,singer);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondaryActivity", "Activity destroyed");
    }
}