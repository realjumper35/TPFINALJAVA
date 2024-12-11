package cal335.projet.Modele;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private Integer id_contact;
    private String nom;
    private String prenom;
    private Boolean isFavoris;
    private List<Adresse> adresses = new ArrayList<>();

    public Contact() {
        // Default constructor
    }

    public Contact(Integer id_contact, String nom, String prenom, Boolean isFavoris, List<Adresse> adresses) {
        this.id_contact = id_contact;
        this.nom = nom;
        this.prenom = prenom;
        this.isFavoris = isFavoris;
        this.adresses = adresses;
    }


    public Integer getId_contact() {
        return id_contact;
    }

    public void setId_contact(Integer id_contact) {
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

    public Boolean isFavoris() {
        return isFavoris;
    }

    public void setFavoris(Boolean favoris) {
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
