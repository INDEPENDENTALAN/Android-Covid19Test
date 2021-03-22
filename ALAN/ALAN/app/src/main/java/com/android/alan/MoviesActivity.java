package com.android.alan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class MoviesActivity extends AppCompatActivity {

    //
    private final static int REQUEST_PERMISSIONS = 0;

    //
    ImageButton imageButton_movies_back;
    PlayerView playerView_home;
    private SimpleExoPlayer simpleExoPlayer;
    private String url = "https://firebasestorage.googleapis.com/v0/b/alan-1dc38.appspot.com/o/Asset-.mp4?alt=media&token=261ce98f-3574-4937-97df-703952de4851";
    private boolean ready = true;
    private int window = 0;
    private long position = 0;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        imageButton_movies_back = findViewById(R.id.imageButton_movies_back);
        imageButton_movies_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                finish();
            }
        });
        //onRequestPermissions();
        //playerView_home = findViewById(R.id.playerView_movies);
    }

    private void onRequestPermissions() {
        //
        if (ContextCompat.checkSelfPermission(MoviesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MoviesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(MoviesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //
            } else {
                //
                ActivityCompat.requestPermissions(MoviesActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS);
            }
        } else {
            //granted
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //
        //onInit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        if (simpleExoPlayer != null) {
            //
            //onInit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //
        //onRelease();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //
        //onRelease();
    }

    private void onInit() {
        if (simpleExoPlayer == null) {
            //
            simpleExoPlayer = new SimpleExoPlayer.Builder(MoviesActivity.this).build();
            playerView_home.setPlayer(simpleExoPlayer);
            Uri uri = Uri.parse(url);
            MediaSource mediaSource = onBuildMediaSource(uri);
            simpleExoPlayer.setPlayWhenReady(ready);
            simpleExoPlayer.seekTo(window, position);
            simpleExoPlayer.prepare(mediaSource, false, false);
        }
    }

    private MediaSource onBuildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(MoviesActivity.this, "exoplayer-codelab");
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
        return mediaSource;
    }

    private void onRelease() {
        //
        if (simpleExoPlayer != null) {
            //
            ready = simpleExoPlayer.getPlayWhenReady();
            window = simpleExoPlayer.getCurrentWindowIndex();
            position = simpleExoPlayer.getCurrentPosition();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                //
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                } else {
                    //denied
                }
                return;
            }
        }
    }
}
