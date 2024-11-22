package cal335.projet.Modele;

public class Adresse {
    private Integer id_adresse;
    private String rue;
    private String ville;
    private String codePostal;
    private String pays;
    private Coordonnees coordonnees;

    public Integer getid_adresse() {
        return id_adresse;
    }

    public void setid_adresse(Integer id_adresse) {
        this.id_adresse = id_adresse;
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
