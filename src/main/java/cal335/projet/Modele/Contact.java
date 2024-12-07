package cal335.projet.Modele;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private int id_contact;
    private String nom;
    private String prenom;
    private boolean isFavoris;
    private List<Adresse> adresses = new ArrayList<>();

    // Getters et Setters


    public int getId_contact() {
        return id_contact;
    }

    public void setId_contact(int id_contact) {
        this.id_contact = id_contact;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isFavoris() {
        return isFavoris;
    }

    public void setFavoris(boolean favoris) {
        isFavoris = favoris;
    }

    public List<Adresse> getListAdresses() {
        return adresses;
    }

    public void setAdresse(Adresse adresse) {
        this.adresses.add(adresse);
    }

    public void setListAdresses(List<Adresse> adresses) {
        this.adresses = adresses;
    }
}
