package com.example.gestioncontact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactHelper extends SQLiteOpenHelper {

    public static final String table_contact ="Contact";

    public static final String col_id="Id";

    public static final String col_nom ="nom";
    public static final String col_prenom ="prenom";
    public static final String col_numero ="numero";

    String requete = " create table "+table_contact+" ("+col_id+" Integer primary Key autoincrement, "
            +col_nom+" Text not null, "+
            col_prenom+" Text not null,"+col_numero+" Text not null)";

    public ContactHelper(@Nullable Context context,
                         @Nullable String name,
                         @Nullable SQLiteDatabase.CursorFactory factory,
                         int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//lors de lancement de l'ouverture de la base
        db.execSQL(requete);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // modification de la version
        db.execSQL(" drop table " +table_contact);
        onCreate(db);


    }
}
