package cal335.projet.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ContactDTO {
    private Integer id_contact;
    private String nom;
    private String prenom;
    private Boolean isFavoris;
    private List<AdresseDTO> adresses = new ArrayList<>();

    public ContactDTO() {
        // Default constructor
    }


    public ContactDTO(Integer id_contact, String nom, String prenom, Boolean isFavoris, List<AdresseDTO> adresses) {
        this.id_contact = id_contact;
        this.nom = nom;
        this.prenom = prenom;
        this.isFavoris = isFavoris;
        this.adresses = adresses;
    }

    // Getters et Setters
    public Integer getId_contact() {
        return id_contact;
    }

    public void setId_contact(Integer id_contact) {
        this.id_contact = id_contact;
    }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonProperty("prenom")
    public String getPrenom() {
        return prenom;
    }

    @JsonProperty("prenom")
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @JsonProperty("isFavoris")
    public boolean isFavoris() {
        return isFavoris;
    }

    @JsonProperty("isFavoris")
    public void setFavoris(Boolean isFavoris) {
        this.isFavoris = isFavoris;
    }

    @JsonProperty("adresses")
    public List<AdresseDTO> getListAdresses() {
        return adresses;
    }


    public void setAdresse(AdresseDTO adresse) {
        this.adresses.add(adresse);
    }

    @JsonProperty("adresses")
    public void setListAdresses(List<AdresseDTO> adresses) {
        this.adresses = adresses;
    }
}
