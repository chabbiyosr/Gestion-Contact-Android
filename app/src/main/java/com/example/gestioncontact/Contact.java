package com.example.gestioncontact;

public class Contact {
    public String nom,prenom,numero;
    public int id;


    public Contact(String nom, String prenom, String numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
    }

    public Contact(int id, String nom, String prenom, String numero) {

        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }

    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return nom;
    }
    public String getNum() {
        return numero;
    }
}
