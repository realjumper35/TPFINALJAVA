package cal335.projet.DAO;

import cal335.projet.GestionConnBD;
import cal335.projet.Modele.Coordonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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


    public Coordonnees MAJCoordonnees(Coordonnees coordonnees) {
        String query = "UPDATE Coordonnee SET latitude = ?, longitude = ? WHERE id_coordonnee = ?";
        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, coordonnees.getLatitude());
            preparedStatement.setDouble(2, coordonnees.getLongitude());
            preparedStatement.setInt(3, coordonnees.getId_coordonnees());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.err.println("Erreur lors de la MAJ des coordonnees : " + e.getMessage());
        }
        return coordonnees;
    }

    public Coordonnees getCoordonnees(int idAdresse) {
        Coordonnees coordonnees = new Coordonnees();
        String query = "SELECT * FROM Coordonnee WHERE id_adresse = ?";
        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idAdresse);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    coordonnees.setId_coordonnees(resultSet.getInt("id_coordonnee"));
                    coordonnees.setId_adresse(resultSet.getInt("id_adresse"));
                    coordonnees.setLatitude(resultSet.getDouble("latitude"));
                    coordonnees.setLongitude(resultSet.getDouble("longitude"));
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des coordonnees : " + e.getMessage());
        }
        return coordonnees;
    }

    public List<Coordonnees> getListeCoordonnees() {
        List<Coordonnees> coordonnees = new ArrayList<>();
        String query = "SELECT * FROM Coordonnee";
        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Coordonnees coordonnee = new Coordonnees();
                    coordonnee.setId_coordonnees(resultSet.getInt("id_coordonnee"));
                    coordonnee.setId_adresse(resultSet.getInt("id_adresse"));
                    coordonnee.setLatitude(resultSet.getDouble("latitude"));
                    coordonnee.setLongitude(resultSet.getDouble("longitude"));
                    coordonnees.add(coordonnee);
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des coordonnees : " + e.getMessage());
        }
        return coordonnees;
    }
}
