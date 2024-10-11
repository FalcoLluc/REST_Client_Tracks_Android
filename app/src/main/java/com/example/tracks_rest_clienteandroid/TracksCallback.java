package com.example.tracks_rest_clienteandroid;

import java.util.List;

public interface TracksCallback {
    void onTracksLoaded(List<Track> tracks);
    void onError(String errorMessage);
}
