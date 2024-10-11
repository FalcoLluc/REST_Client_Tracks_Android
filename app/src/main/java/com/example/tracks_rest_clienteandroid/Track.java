package com.example.tracks_rest_clienteandroid;

public class Track {
    public final String id;
    public final String title;
    public final String singer;

    public Track(String _id,String _title, String _singer){
        this.id=_id;
        this.title=_title;
        this.singer=_singer;
    }
}
