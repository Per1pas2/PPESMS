package com.example.applicationintervention;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE assistantes(id_assistante INTEGER PRIMARY KEY AUTOINCREMENT, nom_assistante TEXT NOT NULL, prenom_assistante TEXT not null)");
        db.execSQL("CREATE TABLE login_assistantes(id_login_assistante INTEGER PRIMARY KEY AUTOINCREMENT, username_login_assistante TEXT NOT NULL, md5_pass_login_assistante TEXT NOT NULL, id_assistante INTEGER NOT NULL, FOREIGN KEY (id_assistante) references assistante(id_assistante))");
        db.execSQL("CREATE TABLE enfant(id_enfant INTEGER PRIMARY KEY AUTOINCREMENT, prenom_enfant TEXT NOT NULL, nom_enfant TEXT NOT NULL, date_naissance_enfant DATE NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("0.1", "Mise à jour de la version de la base de données de  : " + oldVersion + " à : "+ newVersion + " qui va détruire toutes les données");
        db.execSQL("DROP TABLE IF EXISTS login_assistantes");
        db.execSQL("DROP TABLE IF EXISTS assistantes");
        db.execSQL("DROP TABLE IF EXISTS enfant");
        onCreate(db);
    }


    public void InsererAssistantesDansDB(int id, String nom_assist, String prenom_assist){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("id_assistante",id);
        contentValues.put("nom_assistante",nom_assist);
        contentValues.put("prenom_assistante",prenom_assist);
        db.insert("assistantes", null,contentValues);
    }

    public int VerifyAssistantesIntoDB(String username, String md5pass){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT id_login_assistante FROM login_assistantes WHERE username_login_assistante = '" + username + "' AND md5_pass_login_assistante = '" + md5pass + "'";
        Cursor c = db.rawQuery(sql, null);
        if(c.moveToFirst()){
            int id = c.getInt(0);
            c.close();

            return id;
        }
        else{
            return 0;
        }
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }
}