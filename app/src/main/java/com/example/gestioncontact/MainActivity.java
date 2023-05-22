package com.example.gestioncontact;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // declaration composants type dima majuscule
    //alt+entrée pour effacer l'erreur  et importer le bibliothéque EditText
    EditText edmail, edpwd;

    Button btnexit, btnsubmit;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean savelogin;
    CheckBox savelogincheckbox;

    //CheckBox checkBox ;

    /*private static final String mail = "chabbiyosr@gmail.com";
    private static final String password = "000";


*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mettre un fichier xml sur l'ecran ( ay haja fiha R rahi res )
        setContentView(R.layout.activity_main);
        //récupération composants dima ba3ed setContentView
        edmail = findViewById(R.id.edmail_auth);
        edpwd = findViewById(R.id.edpwd_auth);
        btnexit = findViewById(R.id.btnexit_auth);
        btnsubmit = findViewById(R.id.btnsubmit_auth);

        sharedPreferences = getSharedPreferences("loginref",MODE_PRIVATE);
        savelogincheckbox=findViewById(R.id.checkremeberme);
        editor = sharedPreferences.edit();


        // Evenement
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override//view source de traitemnt
            public void onClick(View view) {
                //this.finish ne marche pas car this tarja3 lel classe courante ama ahna nhebou nsakro l activity
                MainActivity.this.finish();
            }
        });


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox ch = (CheckBox) findViewById(R.id.checkremeberme);
                String mail = edmail.getText().toString();
                String pwd = edpwd.getText().toString();

                if(mail.equalsIgnoreCase("chabbi Yosr")&& pwd.equalsIgnoreCase("000")){
                    if (savelogincheckbox.isChecked()){
                        editor.putBoolean("savelogin",true);
                        editor.putString("email",mail);
                        editor.putString("password",pwd);
                        editor.commit();
                    }
                    //passage vers accueil
                    Intent i=new Intent(MainActivity.this,Accueil.class);
                    i.putExtra("USER",mail);
                    startActivity(i);
                    finish();


                }
                else{
                        //message d'erreur
                        Toast.makeText(MainActivity.this, "vérifier mail et mdp", Toast.LENGTH_SHORT).show();
                    }
                }

            });

        savelogin=sharedPreferences.getBoolean("savelogin",true);
        if(savelogin==true){
            edmail.setText(sharedPreferences.getString("email",null));
            edpwd.setText(sharedPreferences.getString("password",null));
        }
        else {
            edmail.setText("");
            edpwd.setText("");

        }

    }
    }



