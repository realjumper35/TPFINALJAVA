package cal335.projet.DAO;

import cal335.projet.GestionConnBD;
import cal335.projet.Modele.Adresse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdresseDAO {

    public Adresse ajouterAdresse(Adresse adresse, int idContact) {
        CoordoneesDAO coordoneesDAO = new CoordoneesDAO();
        String query = "INSERT INTO adresse (id_contact, rue, ville, code_postal, pays) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idContact);
            preparedStatement.setString(2, adresse.getRue());
            preparedStatement.setString(3, adresse.getVille());
            preparedStatement.setString(4, adresse.getCodePostal());
            preparedStatement.setString(5, adresse.getPays());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    adresse.setId_adresse(generatedId);
                    adresse.setId_contact(idContact);
                    adresse.setCoordonnees(coordoneesDAO.ajouterCoordonnees(adresse.getCoordonnees(), generatedId));
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la récupération de l'ID généré : " + e.getMessage());
            }


        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de l'adresse : " + e.getMessage());
        }

        return adresse;
    }

}
