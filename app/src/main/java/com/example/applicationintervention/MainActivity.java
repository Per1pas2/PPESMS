package com.example.applicationintervention;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        EditText Identifiant = findViewById(R.id.TXT_IDENFIANT);
        EditText Mdp = findViewById(R.id.TXT_MDP);

        btn = findViewById(R.id.BTN_CONNEXION);

        btn.setOnClickListener(v -> {

            try {
                String Identifiant_TEST = Identifiant.getText().toString();
                String Mdp_TEST = Mdp.getText().toString();
                Intent i = new Intent(MainActivity.this, tableaudebord.class);
                startActivity(i);
            }catch (Exception e)
            {
                Context context = getApplicationContext();
                CharSequence text = "Imp";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}