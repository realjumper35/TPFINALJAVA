package cal335.projet.DAO;

import cal335.projet.GestionConnBD;
import cal335.projet.Modele.Coordonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CoordoneesDAO {

    public void ajouterCoordonnees(Coordonnees coordonnees, int idAdresse) {
        String query = "INSERT INTO Coordonnee (id_adresse, latitude, longitude) VALUES (?, ?, ?)";
        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idAdresse);
            preparedStatement.setDouble(2, coordonnees.getLatitude());
            preparedStatement.setDouble(3, coordonnees.getLongitude());
            preparedStatement.executeUpdate();



        }catch (Exception e) {
            System.err.println("Erreur lors de l'ajout des coordonnees : " + e.getMessage());
        }
    }
}
