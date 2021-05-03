package com.example.applicationintervention;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button btn;
    DatabaseHelper dh = new DatabaseHelper(MainActivity.this,"DatabaseHelper",null,1);
    private static final String URL = "http://192.168.0.27/android/select_login_assistantes.php";
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
    protected void onPostExecute(String data){
        try{
            JSONArray ja = new JSONArray(data);
            JSONObject jsonObject;
            for(int i = 0; i<=3; i++){
                jsonObject=ja.getJSONObject(i);
                Log.println(Log.WARN,"oui",jsonObject.toString());
                // Désérialisation
                int id = jsonObject.getInt("id_assistante");
                String name = jsonObject.getString("nom_assistante");
                String pass = jsonObject.getString("prenom_assistante");
                dh.InsererAssistantesDansDB(id, name, pass);
            }
        }catch(Exception ex){
            ex.getStackTrace();
        }
    }

    /**
     * Va chercher le JSON sur la page web
     * @param linkurl
     * @return
     */
    protected String doInBackgroundAssistante(String linkurl) {
        try{
            java.net.URL url = new URL(linkurl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder data = new StringBuilder();
            String line;
            while((line=br.readLine())!=null){
                data.append(line+"\r\n");
            }
            br.close();

            return data.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}