package com.example.gestioncontact;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyContactRecycleAdapter extends RecyclerView.Adapter<MyContactRecycleAdapter.MyViewHolder> {
    Context con;
    ArrayList<Contact> d;

    public MyContactRecycleAdapter(Context con, ArrayList<Contact> d) {
        this.con = con;
        this.d = d;
    }

    @NonNull
    @Override
    public MyContactRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(con);
        View v = inf.inflate(R.layout.view_contact, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyContactRecycleAdapter.MyViewHolder holder, int pos) {
        //Affectation et modification des Holders :
        Contact c = d.get(pos);
        holder.tvnom.setText(c.nom);
        holder.tvprenom.setText(c.prenom);
        holder.tvnumero.setText(c.numero);

    }

    @Override
    public int getItemCount() {
        return d.size();
    }

    public void filterlist(ArrayList<Contact> filtredlist) {

        d = filtredlist;
        notifyDataSetChanged();
    }

    //viewholder v global aamlnehlha parsing viewconatct
    public class MyViewHolder extends RecyclerView.ViewHolder {

        EditText message;
        TextView tvnom, tvprenom, tvnumero;
        ImageView imgdelete, imgcall, imgedit, imgmsg;

        public MyViewHolder(@NonNull View v) {
            super(v);
            tvnom = v.findViewById(R.id.tvnom_contact);
            tvprenom = v.findViewById(R.id.tvprenom_contact);
            tvnumero = v.findViewById(R.id.tvnumero_contact);

            imgdelete = v.findViewById(R.id.imageViewDelete_contact);
            imgcall = v.findViewById(R.id.imageViewCall_contact);
            imgedit = v.findViewById(R.id.imageViewEdit_conatct);
            imgmsg = v.findViewById(R.id.imageView_send_msg);


            imgcall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int indice = getAdapterPosition();
                    if (Accueil.PHONE_PERMISSION) {
                        Intent i = new Intent(Intent.ACTION_CALL);
                        i.setData(Uri.parse("tel:" + d.get(indice).numero));
                        con.startActivity(i);
                    }
                }
            });


            imgedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    AlertDialog alert = new AlertDialog.Builder(con).create();
                    alert.setTitle("Edition");
                    alert.setMessage("Do you want to edit " + d.get(position).prenom + "'s Informations");
                    alert.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog a = new AlertDialog.Builder(con).create();

                            LayoutInflater inf = LayoutInflater.from(con);
                            View view = inf.inflate(R.layout.activity_edit, null);
                            EditText tvnom = (EditText) view.findViewById(R.id.ednom_edit);
                            EditText tvprenom = (EditText) view.findViewById(R.id.edprenom_edit);
                            EditText tvnumero = (EditText) view.findViewById(R.id.ednum_edit);
                            Button btnval1 = (Button) view.findViewById(R.id.btnval_edit);
                            Button btnqte1 = (Button) view.findViewById(R.id.btnqte_edit);
                            Contact c = d.get(position);
                            tvnom.setText(c.nom);
                            tvprenom.setText(c.prenom);
                            tvnumero.setText(c.numero);
                            Toast toast = Toast.makeText(con, "contact Informations", Toast.LENGTH_SHORT);
                            toast.show();
                            a.setView(view);
                            btnval1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ContactManager manager = new ContactManager(con);
                                    manager.ouvrir();
                                    String nom = tvnom.getText().toString();
                                    String prenom = tvprenom.getText().toString();
                                    String numero = tvnumero.getText().toString();
                                    manager.update(c.id, nom, prenom, numero);
                                    d.set(position, new Contact(nom, prenom, numero));
                                    notifyDataSetChanged();
                                    notifyItemChanged(position);
                                    tvnom.setText(c.nom);
                                    tvprenom.setText(c.prenom);
                                    tvnumero.setText(c.numero);


                                    a.dismiss();
                                }
                            });

                            a.show();
                            btnqte1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    a.dismiss();
                                }
                            });
                        }


                    });

                    alert.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            alert.dismiss();
                        }
                    });
                    alert.show();


                }
            });

            imgdelete.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View View){
                    //recuperer l'indice de l'element sélectionné
                    int indice = getAdapterPosition();

                    AlertDialog.Builder alert = new AlertDialog.Builder(con);
                    alert.setTitle("Attention");
                    alert.setMessage("Supprimer les infos");
                    alert.setPositiveButton("confirmer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ContactManager Manager = new ContactManager(con);
                            Manager.ouvrir();
                            Manager.supprimer(d.get(indice).id);
                            d.remove(indice);
                            notifyDataSetChanged();

                        }
                    });
                    alert.show();
                }
            });


            imgmsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //recuperer l'indice de l'element sélectionné
                    int indice = getAdapterPosition();
                    message = new EditText(view.getContext());
                    message.setHint("Ecrire message ....");
                    message.setText("FINDFRIENDS: envoyer moi votre position");


                    final AlertDialog.Builder sending_sms = new AlertDialog.Builder(view.getContext());

                    sending_sms.setIcon(R.drawable.baseline_message_24)
                            .setTitle(" Envoyer à  :" + d.get(indice).getPrenom())
                            .setView(message)
                            .setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    sendSMS(d.get(indice).getNum(), message.getText().toString());
                                }
                            })
                            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).create();

                    sending_sms.show();

                }


            });
        }









        private void sendSMS(String tvnumero, String message) {
            try {
                if (Accueil.MSG_PERMISSION) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(tvnumero, null, "hello my friend", null, null);
                    con.startActivity(i);


                    Toast.makeText(con, "Message sent to " + tvnumero, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception exception) {
                Toast.makeText(con, "something went wrong ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

