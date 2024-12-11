package cal335.projet.DTO;

public class AdresseDTO {
    private Integer id_adresse;
    private Integer id_contact;
    private String rue;
    private String ville;
    private String codePostal;
    private String pays;
    private CoordonneesDTO coordonnees;

    public AdresseDTO() {
        // Default constructor
    }

    public AdresseDTO(Integer id_adresse, Integer id_contact, String rue, String ville, String codePostal, String pays, CoordonneesDTO coordonnees) {
        this.id_adresse = id_adresse;
        this.id_contact = id_contact;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
        this.coordonnees = coordonnees;
    }

    public Integer getId_adresse() {
        return id_adresse;
    }

    public void setId_adresse(Integer id_adresse) {
        this.id_adresse = id_adresse;
    }

    public Integer getId_contact() {
        return id_contact;
    }

    public void setId_contact(Integer id_contact) {
        this.id_contact = id_contact;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public CoordonneesDTO getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(CoordonneesDTO coordonnees) {
        this.coordonnees = coordonnees;
    }



}
