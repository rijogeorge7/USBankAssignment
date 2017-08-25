package com.rijo.usbankassignment.ui.mainScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rijo.usbankassignment.R;
import com.rijo.usbankassignment.ui.CountriesAutoComplete.CountriesActivity;
import com.rijo.usbankassignment.ui.converter.ConverterActivity;
import com.rijo.usbankassignment.ui.radio.RadioActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button autoTextButton=(Button)findViewById(R.id.autoTextButton);
        Button currencyButton=(Button)findViewById(R.id.currencyButton);
        Button audioStreamButton=(Button)findViewById(R.id.audioStreamButton);
        autoTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CountriesActivity.class);
                startActivity(intent);
            }
        });
        currencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ConverterActivity.class);
                startActivity(intent);
            }
        });
        audioStreamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,RadioActivity.class);
                startActivity(intent);
            }
        });

    }
}
