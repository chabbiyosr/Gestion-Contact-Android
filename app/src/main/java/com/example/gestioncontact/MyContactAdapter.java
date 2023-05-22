package com.example.gestioncontact;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyContactAdapter extends BaseAdapter {
    //Role de l'adapter : creer les views
    Context con;
    ArrayList<Contact>d;
    public MyContactAdapter(Context con, ArrayList<Contact> d) {
        this.con = con;
        this.d = d;
    }

    @Override
    //nb view à retourner
    public int getCount() {
        return d.size();
    }//yraja3 nbr des views  à créer

    @Override
    public Object getItem(int i) {

        return null;
    }

    @Override
    public long getItemId(int pos) {

        return 0;
    }

    @Override // ypaarcouriha l'adapter mel les positions
    public View getView(int pos, View view, ViewGroup viewGroup) {
        /*TextView t=new TextView(con);
        t.setText(d.get(pos).numero);
        return t;*/

        //Creation d'un seul view

        //Parsing XML
        LayoutInflater inf = LayoutInflater.from(con);
        View v = inf.inflate(R.layout.view_contact,null);

        //Récupération des views : HOLDERS
        TextView tvnom = v.findViewById(R.id.tvnom_contact);
        TextView tvprenom = v.findViewById(R.id.tvprenom_contact);
        TextView tvnumero = v.findViewById(R.id.tvnumero_contact);

        ImageView imgdelete = v.findViewById(R.id.imageViewDelete_contact);
        ImageView imgcall = v.findViewById(R.id.imageViewCall_contact);
        ImageView imgedit = v.findViewById(R.id.imageViewEdit_conatct);



        //Affectation et modification des Holders :
        Contact c =d.get(pos);
        tvnom.setText(c.nom);
        tvprenom.setText(c.prenom);
        tvnumero.setText(c.numero);





        //Action et évènement :
        imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         /*
               numerotation via l'application par défaut
               Intent i = new Intent(Intent.ACTION_DIAL);
               */

                    Intent i = new Intent(Intent.ACTION_CALL);




                i.setData(Uri.parse("tel:" + d.get(pos).numero));
                con.startActivity(i);

        }
    });
      /*  imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert=new AlertDialog.Builder(con);
                alert.setTitle("Attention");
                alert.setMessage("Supprimer les infos");
                alert.setPositiveButton("confirmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        {
                            Contact c= (Contact) v.getTag();
                            ContactManager(con).supprimer(c);

                        d.remove(pos);
                        notifyDataSetChanged();

                    }
                });
                alert.show();
            }
        });

        }
    }*/

       /*imgedit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder alert=new AlertDialog.Builder(con);
               alert.setTitle("Edition");
               alert.setMessage("Modifier les infos");
               alert.show();

           }
       });*/




        return v;
}
}
          /**

        imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(Intent.ACTION_DIAL);//pour composer numéro, numérotation via l'a'pp par défaut
               // i.setData(Uri.parse(("tel:" +d.get(position).numero)));

                /** appel via notre app => attention permission
                permission
                 1 etape manifest
                 2 etape demande permission
          /**
           * Appel via notre application ==> Attention Permission
           * Permission
           * 1ere etape : Manifest
           * 2eme étape : Demande de permission


                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse(("tel:" +d.get(position).numero)));
                con.startActivity(i);
           **/

