package com.rijo.usbankassignment.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

public class StreamingService extends Service {
    private LocalBinder mLocalBinder=new LocalBinder();
    private Callback callback;
    private MediaPlayer mediaPlayer;
    public enum status {PLAYING, NOTPLAYING}
    private static status currentStatus=status.NOTPLAYING;
    public StreamingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mLocalBinder;
    }

    public void startStreaming(final String stringUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                play(stringUrl);
            }
        }).start();
    }

    void play(String stringUrl) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(stringUrl);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    currentStatus=status.PLAYING;
                    callback.startSuccessfully();
                }
            });
            callback.preparing();
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCallback(Callback callback) {
        this.callback=callback;
    }

    public void stopStreaming() {
        if(mediaPlayer!=null)
            mediaPlayer.stop();
        currentStatus=status.NOTPLAYING;
        callback.stoppedSuccessfully();
    }

    public void getStatus() {
        callback.setStatus(currentStatus);
    }

    public class LocalBinder extends Binder {
        public StreamingService getService() {
            return StreamingService.this;
        }
    }

    public interface Callback {
        void stoppedSuccessfully();
        void startSuccessfully();
        void preparing();
        void setStatus(status status);
    }
}
