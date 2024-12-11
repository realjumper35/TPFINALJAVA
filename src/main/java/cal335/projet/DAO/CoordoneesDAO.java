package cal335.projet.DAO;

import cal335.projet.GestionConnBD;
import cal335.projet.Modele.Coordonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CoordoneesDAO {

    public Coordonnees ajouterCoordonnees(Coordonnees coordonnees, int idAdresse) {
        String query = "INSERT INTO Coordonnee (id_adresse, latitude, longitude) VALUES (?, ?, ?)";
        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idAdresse);
            preparedStatement.setDouble(2, coordonnees.getLatitude());
            preparedStatement.setDouble(3, coordonnees.getLongitude());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    coordonnees.setId_coordonnees(generatedId);
                    coordonnees.setId_adresse(idAdresse);
                }
            }


        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout des coordonnees : " + e.getMessage());
        }
        return coordonnees;
    }
}
