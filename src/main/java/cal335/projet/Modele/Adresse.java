package cal335.projet.Modele;

public class Adresse {
    private Integer id_adresse;
    private Integer id_contact;
    private String rue;
    private String ville;
    private String codePostal;
    private String pays;
    private Coordonnees coordonnees;

    public Adresse() {
        // Default constructor
    }

    public Adresse(Integer id_adresse, Integer id_contact, String rue, String ville, String codePostal, String pays, Coordonnees coordonnees) {
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

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }




}
