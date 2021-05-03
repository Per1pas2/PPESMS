package com.example.applicationintervention;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Arriver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arriver);
        Button bouton_envoi_sms = findViewById(R.id.BTN_ENVOIE_SMS);

        bouton_envoi_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.smsfactor.com/send";
                String textesms = "?text=Le%20plus%20bo";
                String numero = "&to=33616089574";
                String token = "&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0Mzc2MiIsImlhdCI6MTYyMDA3MjQwN30.L2yiJVEBuKKv8wQryjPhVxV0A8S2GgW6NxGPsbXfP_Y";
                String link = url + textesms+numero+token;

                try {
                    java.net.URL urlfinal = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection)urlfinal.openConnection();
                    conn.getContent();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}