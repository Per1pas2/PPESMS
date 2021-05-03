package com.example.applicationintervention;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class tableaudebord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableaudebord);
        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        String data = getIntent().getStringExtra("Data");
        Button bouton_quitter = findViewById(R.id.BTN_DECONECTER);
        Button bouton_arrivee = findViewById(R.id.BTN_ARRIVE);
        Button bouton_depart = findViewById(R.id.BTN_DEPART);
        TextView entreprise = findViewById(R.id.LBL_SOCIETE);

        entreprise.setText(data.split(";")[2]);
        bouton_quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tableaudebord.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bouton_arrivee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tableaudebord.this, Arriver.class);
                intent.putExtra("Data", data) ;
                startActivity(intent);
            }
        });
        bouton_depart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tableaudebord.this, depart.class);
                intent.putExtra("Data", data) ;
                startActivity(intent);
            }
        });
    }
}