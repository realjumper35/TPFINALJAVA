package cal335.projet.DTO;

public class CoordonneesDTO {
    private int id_coordonnees;
    private int id_adresse;
    private double latitude;
    private double longitude;

    public CoordonneesDTO() {
        // Default constructor
    }

    public CoordonneesDTO(int id_coordonnees, int id_adresse, double latitude, double longitude) {
        this.id_coordonnees = id_coordonnees;
        this.id_adresse = id_adresse;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId_coordonnees() {
        return id_coordonnees;
    }

    public void setId_coordonnees(int id_coordonnees) {
        this.id_coordonnees = id_coordonnees;
    }

    public int getId_adresse() {
        return id_adresse;
    }

    public void setId_adresse(int id_adresse) {
        this.id_adresse = id_adresse;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
