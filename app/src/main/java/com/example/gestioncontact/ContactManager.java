package com.example.gestioncontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactManager {
    SQLiteDatabase db=null;
    Context con;

    ContactManager(Context con){
        this.con =con;

    }
    public void ouvrir (){
      ContactHelper  helper = new ContactHelper(con, "mabase.db",null,3);
      db=helper.getWritableDatabase();
    }
    public long ajout(String nom, String prenom, String numero) {
        long a=0;
        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_nom,nom);
        values.put(ContactHelper.col_prenom,prenom);
        values.put(ContactHelper.col_numero,numero);


        a=db.insert(ContactHelper.table_contact,null,values);

        return a;

    }

   public ArrayList <Contact> getAllContact() {
        ArrayList <Contact> l = new ArrayList<Contact>();

        Cursor cr=db.query(ContactHelper.table_contact,
                new String[]{ContactHelper.col_id,
                        ContactHelper.col_nom,
                        ContactHelper.col_prenom,
                        ContactHelper.col_numero},
                null,null,null,null,null);

        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            int i1 = cr.getInt(0);
            String i2 = cr.getString(1);
            String i3 = cr.getString(2);
            String i4 = cr.getString(3);

            l.add(new Contact(i1, i2, i3,i4));

            cr.moveToNext();


        }
       return l ;
   }

     public   long supprimer(int id)
        {
            int a=-1;
            a=db.delete(ContactHelper.table_contact,
                    ContactHelper.col_id+"="+id,null);
            return a;
        }



        public void fermer() {

    }
    long modifier ( String nom,String prenom,String numero)
    {
        int a=-1;
        // initialisation nouvelle valeur
        ContentValues v=new ContentValues();
        v.put(ContactHelper.col_nom,nom);
        v.put(ContactHelper.col_prenom,prenom);
        v.put(ContactHelper.col_numero,numero);
        a=db.update(ContactHelper.table_contact,
                v,
                ContactHelper.col_numero+"="+numero,null);
        return a;
    }

    public void update ( int id,String nom,String prenom,String numero)
    {
        // int a=-1;
        // initialisation nouvelle valeur
        ContentValues v=new ContentValues();
        v.put(ContactHelper.col_id,id);
        v.put(ContactHelper.col_nom,nom);
        v.put(ContactHelper.col_prenom,prenom);
        v.put(ContactHelper.col_numero,numero);
        long result=db.update(ContactHelper.table_contact, v, ContactHelper.col_id+"="+id,null);
        if(result == -1){

             Toast.makeText(con, "Failed", Toast.LENGTH_SHORT).show();
            //FancyToast.makeText(con,"Failed to Update.",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
        }else {
             Toast.makeText(con, "Updated Successfully!", Toast.LENGTH_SHORT).show();
           // FancyToast.makeText(con, "Updated Successfully!", FancyToast.LENGTH_LONG, FancyToast.CONFUSING, false).show();
        }
    }

    public ArrayList <Contact> searchContact(String mot) {
        ArrayList <Contact> l = new ArrayList<Contact>();

        Cursor cr=db.query(ContactHelper.table_contact,
                new String[]{ContactHelper.col_id,
                        ContactHelper.col_nom,
                        ContactHelper.col_prenom,
                        ContactHelper.col_numero},
                "nom like %?%" ,new String[]{mot},null,null,null);

        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            int i1 = cr.getInt(0);
            String i2 = cr.getString(1);
            String i3 = cr.getString(2);
            String i4 = cr.getString(3);

            l.add(new Contact(i1, i2, i3,i4));

            cr.moveToNext();


        }
        return l ;
    }

}
