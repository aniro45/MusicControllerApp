package com.example.admin.musiccontroller;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.media.AudioManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
    AudioManager audioManager;
    public void PlayClicked(View view){


        mp.start();
    }

    public void PauseClicked(View view){
        mp.pause();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp  = MediaPlayer.create(this, R.raw.apna_muze_tu_laga);

        audioManager =(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);



         SeekBar volumeControl = (SeekBar) findViewById(R.id.SeekBarId);


         volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 Log.i("SeekBAr change", Integer.toString(progress));
                 audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

             }






             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {


             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {


             }
         });




         final SeekBar scrubber = (SeekBar) findViewById(R.id.seekBar2Id);

         scrubber.setMax(mp.getDuration());

         new Timer().scheduleAtFixedRate(new TimerTask() {
             @Override
             public void run() {
                 scrubber.setProgress(mp.getCurrentPosition());
             }
         },0,100);

         scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                 Log.i("Scruuber info", Integer.toString(progress));

                 mp.seekTo(progress);

                 }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });
    }
}
