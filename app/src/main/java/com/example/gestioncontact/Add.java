package com.example.gestioncontact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add extends AppCompatActivity {

    EditText ednom,edprenom,ednum;
    Button btnval,btnqte;

    boolean add=true;
    Contact ct =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


       // edid = findViewById(R.id.edid_ajout);
        ednom = findViewById(R.id.ednom_edit);
        edprenom = findViewById(R.id.edprenom_edit);
        ednum = findViewById(R.id.ednum_edit);
        btnqte=findViewById(R.id.btnqte_edit);
        btnval=findViewById(R.id.btnval_edit);
        Intent i = getIntent();
        if(i.getSerializableExtra("ct")!=null){
            ct= (Contact) i.getSerializableExtra("ct");
            add=false;
            ednom.setText(ct.getNom());
            edprenom.setText(ct.getPrenom());
            ednum.setText(ct.getNum());


        }

        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int id = edid.getId();
                String nom = ednom.getText().toString();
                String prenom = edprenom.getText().toString();
                String numero = ednum.getText().toString();
                ContactManager manager = new ContactManager(Add.this);
                manager.ouvrir();

                if (add) {

                    //Contact c = new Contact(nom,prenom,numero);

                    manager.ajout(nom, prenom, numero);
                    //  Accueil.data.add(c);
                    Toast.makeText(Add.this, "Ajout Avec Succées", Toast.LENGTH_SHORT).show();
                }else {
                        manager.modifier(nom, prenom, numero);
                    //  Accueil.data.add(c);
                        Toast.makeText(Add.this, "Modifcation  Avec Succées", Toast.LENGTH_SHORT).show();

                }
            }

        });


    }
}