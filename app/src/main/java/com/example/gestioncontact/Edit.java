package com.example.gestioncontact;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Edit extends AppCompatActivity {
    EditText edrecherche;
    RecyclerView rv_affiche;
    //ListView lv_affiche;

    ArrayList<Contact> data = new ArrayList<Contact>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        edrecherche = findViewById(R.id.search);
        //lv_affiche = findViewById(R.id.rv_affiche);
        rv_affiche = findViewById(R.id.rv_affiche);


        ContactManager manager = new ContactManager(Edit.this);
        manager.ouvrir();
        // manager.ajout("chabbi","yosr", "53392029");
        //manager.ajout("turki","asma","53392807");
        //manager.ajout("achour","houda","99616733");

        data = manager.getAllContact();

         /* data.add(new Contact("Chabbi", "Yosr", "53392029"));
          data.add(new Contact("turki","asma","53392807"));
          data.add(new Contact("achour","houda","99616733"));*/

        MyContactRecycleAdapter ad = new MyContactRecycleAdapter(Edit.this, data);
        rv_affiche.setAdapter(ad);

        //LinearLayoutManager manager1 = new LinearLayoutManager(Edit.this, LinearLayoutManager.VERTICAL,true);
        GridLayoutManager manager1 = new GridLayoutManager(Edit.this, 1,GridLayoutManager.HORIZONTAL,false);
        rv_affiche.setLayoutManager(manager1);

        edrecherche.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence ch, int i, int i1, int i2) {
                /**
                 * RECHERCHER CH DANS DATA
                 *extraire data2
                 *adapter et affichage dans la listView
                 **/
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());


            }
        });

    }
    private void filter (String text){
        ArrayList<Contact>filtredlist = new ArrayList<>();
        for (Contact item : data){
            if(item.nom.toLowerCase().contains(text.toLowerCase())||item.prenom.toLowerCase().contains(text.toLowerCase())||item.numero.toLowerCase().contains(text.toLowerCase())){
                filtredlist.add(item);
            }


        }

         MyContactRecycleAdapter ad = new MyContactRecycleAdapter(Edit.this,filtredlist);

        rv_affiche.setAdapter(ad);

    }
}

     /*  public boolean onCreateOptionMenu (Menu menu){
            getMenuInflater().inflate(R.menu.menu_main, menu);
            MenuItem item = menu.findItem(R.id.search);
            SearchView search = (SearchView) item.getActionView();
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newtext) {
                    ContactManager manager3 = new ContactManager(Edit.this);
                    manager3.ouvrir();
                    manager3.searchContact(newtext);
                    return false;
                }
            });
        }
        return false;
    }*/



