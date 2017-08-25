package com.rijo.usbankassignment.ui.radio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rijo.usbankassignment.R;
import com.rijo.usbankassignment.services.StreamingService;
import com.rijo.usbankassignment.ui.converter.ConverterActivity;
import com.rijo.usbankassignment.util.Utilities;

public class RadioActivity extends Activity implements ServiceConnection, StreamingService.Callback {

    private StreamingService streamingService;
    private Button startButton;
    private ProgressBar progressBar;
    TextView infoTextView;
    public static final String streamingUrl="http://live-radio01.mediahubaustralia.com/PBW/mp3/Title1=NewsRadio";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        startButton=findViewById(R.id.startButton);
        progressBar=findViewById(R.id.progressBar);
        infoTextView=findViewById(R.id.infoTextView);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (streamingService != null) {
                    if (startButton.getText().toString().equals(getResources().getString(R.string.play)))
                    {
                        if (Utilities.IsInternetAvailable(getApplicationContext())) {
                            progressBar.setVisibility(View.VISIBLE);
                            startButton.setEnabled(false);
                            streamingService.startStreaming(streamingUrl);
                        } else {
                            showNoNetworkDialog();
                        }
                }
                else{
                        startButton.setEnabled(false);
                        streamingService.stopStreaming();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent bindIntent=new Intent(this, StreamingService.class);
        bindService(bindIntent,this,BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(streamingService!=null) {
            streamingService.setCallback(null);
            unbindService(this);
        }
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        streamingService=((StreamingService.LocalBinder)iBinder).getService();
        streamingService.setCallback(this);
        startButton.setEnabled(true);
        streamingService.getStatus();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        findViewById(R.id.startButton).setEnabled(false);
        streamingService=null;
    }

    private void showNoNetworkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RadioActivity.this);

        builder.setMessage(R.string.internet_dialog_message)
                .setTitle(R.string.internet_dialog_title);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
    }

    @Override
    public void stoppedSuccessfully() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                infoTextView.setTextColor(getResources().getColor(R.color.redColor));
                infoTextView.setText(R.string.click_start_button_to_listen_audio);
                startButton.setText(R.string.play);
                startButton.setEnabled(true);
            }
        });

    }

    @Override
    public void startSuccessfully() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                infoTextView.setTextColor(getResources().getColor(R.color.greenColor));
                infoTextView.setText(R.string.audio_playing_info);
                startButton.setText(R.string.stop);
                startButton.setEnabled(true);
            }
        });

    }

    @Override
    public void preparing() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                infoTextView.setTextColor(getResources().getColor(R.color.yellowColor));
                infoTextView.setText(R.string.audioPrepareText);
            }
        });

    }

    @Override
    public void setStatus(StreamingService.status status) {
        if(status.equals(StreamingService.status.valueOf("PLAYING"))) {
            progressBar.setVisibility(View.GONE);
            infoTextView.setTextColor(getResources().getColor(R.color.greenColor));
            infoTextView.setText(R.string.audio_playing_info);
            startButton.setText(R.string.stop);
            startButton.setEnabled(true);
        }
    }
}
