package cal335.projet.DTO;

import java.util.List;

public class ContactDTO {
    private Integer id_contact;
    private String nom;
    private String prenom;
    private boolean isFavoris;
    private List<AdresseDTO> adresses;

    // Getters et Setters
    public Integer getid_contact() {
        return id_contact;
    }

    public void setid_contact(Integer id_contact) {
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

    public List<AdresseDTO> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<AdresseDTO> adresses) {
        this.adresses = adresses;
    }
}
