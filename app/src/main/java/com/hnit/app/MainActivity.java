package com.hnit.app;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> videos = new ArrayList<String>();
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);

        getVideos();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playVideo();
            }
        });

    }

    protected void onStart(){
        super.onStart();
        playVideo();
    }

    protected void onResume(){
        super.onResume();
        videoView.resume();
    }

    protected void onPause(){
        super.onPause();
        videoView.pause();
    }

    protected void onStop(){
        super.onStop();
    }

    protected void onDestroy(){
        super.onDestroy();
    }

    protected void onRestart(){
        super.onRestart();
        getVideos();
    }



    private void getVideos(){
        File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File[] files = downloadDir.listFiles();
        if(files != null){
            for(int i = 0;i < files.length;i++){
                if(files[i].isFile() && isVideoFile(files[i].toString())){
                    videos.add(files[i].toString());
                }
                Log.d("MainActivity",files[i].toString());
            }
        }
    }

    private boolean isVideoFile(String path){
        String fileExtension = MimeTypeMap.getFileExtensionFromUrl(path);
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension);
        return mimeType != null && mimeType.startsWith("video");
    }

    private void playVideo(){
        int randIndex = new Random().nextInt(videos.size());
        Log.d("MainActivity", videos.get(randIndex));
        videoView.setVideoPath(videos.get(randIndex));
        videoView.start();
    }
}