package com.example.applicationintervention;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class depart extends AppCompatActivity {
    private static String URL = "http://192.168.1.30/Android/select_localisation.php";
    private static String URLIntervention = "http://192.168.1.30/Android/select_intervention.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depart);
        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        String dataentreprise = getIntent().getStringExtra("Data");
        TextView entreprise = findViewById(R.id.LBL_SOCIETE2);
        entreprise.setText(dataentreprise.split(";")[2]);
        EditText heure = findViewById(R.id.TXT_HEURE);
        Button bouton_envoi_sms = findViewById(R.id.BTN_ENVOIE_SMS2);
        Spinner spinner = findViewById(R.id.SPN_LOGEMENT2);

        String data = doInBackgroundAssistante(URL);
        ArrayList<String> liste = onPostExecute(data);

        Log.w("liste",liste.toString());

        ArrayAdapter<String> at = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,liste );
        at.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(at);

        //----------------------------------------------------------------------------------------------------

        Spinner spinnerintervention = findViewById(R.id.SPN_Type_Intervention4);
        String dataintervention = doInBackgroundntervention(URLIntervention);

        ArrayList<String> listeintervention = onPostExecuteIntervention(dataintervention);

        Log.w("liste_intervention",listeintervention.toString());

        ArrayAdapter<String> atintervention = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listeintervention );
        atintervention.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerintervention.setAdapter(atintervention);

        bouton_envoi_sms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url = "https://api.smsfactor.com/send";
                String textesms = "?text=Depart Localisation : "+ spinner.getSelectedItem().toString() + ",Intervention : "+spinnerintervention.getSelectedItem().toString()+",Entreprise : "+ entreprise.getText().toString()+ ",Heure : "+heure.getText().toString();
                String numero = "&to=33616089574";
                String token = "&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0MzczNCIsImlhdCI6MTYyMDAzNTcxOX0.VYiCCRuI8YjbHKD7JgrHuTWnnWSSP_rGx3xuGdRd8oY";
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

    protected ArrayList<String> onPostExecute(String data){

        ArrayList<String> liste_localisation = new ArrayList<>();
        try{
            JSONArray ja = new JSONArray(data);
            JSONObject jsonObject;
            for(int i = 0; i<ja.length(); i++){
                jsonObject=ja.getJSONObject(i);
                // Désérialisation
                String localisation = jsonObject.getString("Localisation_BIEN_IMMOBILIER");
                liste_localisation.add(localisation);
            }
            return liste_localisation;
        }catch(Exception ex){
            ex.getStackTrace();
        }
        return null;
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


    protected ArrayList<String> onPostExecuteIntervention(String data){

        ArrayList<String> liste_type = new ArrayList<>();
        try{
            JSONArray ja = new JSONArray(data);
            JSONObject jsonObject;
            for(int i = 0; i<ja.length(); i++){
                jsonObject=ja.getJSONObject(i);
                // Désérialisation
                String type = jsonObject.getString("Nom_type");
                liste_type.add(type);
            }
            return liste_type;
        }catch(Exception ex){
            ex.getStackTrace();
        }
        return null;
    }

    /**
     * Va chercher le JSON sur la page web
     * @param linkurl
     * @return
     */
    protected String doInBackgroundntervention(String linkurl) {
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