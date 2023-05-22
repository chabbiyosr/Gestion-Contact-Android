package com.example.gestioncontact;

import static android.content.pm.PackageManager.PERMISSION_DENIED;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.Manifest;
import android.Manifest.permission;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {
    // declaration des composants : type text button
    public static boolean PHONE_PERMISSION=false;
    public static boolean MSG_PERMISSION=false;
    public static ArrayList<Contact> data =  new ArrayList<Contact>();
    Button btnajout,btnaffichage;
   // private TextView tvname;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);


        // Récupération des composants

        btnajout=findViewById(R.id.btn_ajout_acc);
        btnaffichage=findViewById(R.id.btnaff_acc);
        //Evenement
        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accueil.this,Add.class);
                startActivity(i);
            }
        });
        btnaffichage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accueil.this,Edit.class);
                startActivity(i);
            }
        });

      /**  tvname=findViewById(R.id.tvuser_acc);
        Intent x =this.getIntent();
        Bundle b=x.getExtras();
        String u = b.getString("USER");
        tvname.setText("Acceuil de " + u);**/

      //gestion des permissions
        //tester la permission
       if (ActivityCompat.checkSelfPermission(Accueil.this, permission.CALL_PHONE)== PERMISSION_DENIED || ActivityCompat.checkSelfPermission(Accueil.this, permission.SEND_SMS)== PERMISSION_GRANTED)
            {
                //demander la permission
                ActivityCompat.requestPermissions(Accueil.this,
                        new String[]{permission.CALL_PHONE,
                                Manifest.permission.SEND_SMS,
                                Manifest.permission.READ_SMS,
                                Manifest.permission.RECEIVE_SMS,
                        },
                        1


                );
        }
       else
        {
                PHONE_PERMISSION=true;
                MSG_PERMISSION=true;
        }

    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1) {
            if (grantResults.length>0){
                if(grantResults[0]== PERMISSION_GRANTED){
                    PHONE_PERMISSION=true;
                    MSG_PERMISSION = true;
                }
            }
        }
    }


}
