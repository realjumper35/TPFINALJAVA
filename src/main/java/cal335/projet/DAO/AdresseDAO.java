package cal335.projet.DAO;

import cal335.projet.GestionConnBD;
import cal335.projet.Modele.Adresse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public Adresse MAJAdresse(Adresse adresse) {
        CoordoneesDAO coordoneesDAO = new CoordoneesDAO();
        String query = "UPDATE adresse SET rue = ?, ville = ?, code_postal = ?, pays = ? WHERE id_adresse = ?";
        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, adresse.getRue());
            preparedStatement.setString(2, adresse.getVille());
            preparedStatement.setString(3, adresse.getCodePostal());
            preparedStatement.setString(4, adresse.getPays());
            preparedStatement.setInt(5, adresse.getId_adresse());

            preparedStatement.executeUpdate();

            coordoneesDAO.MAJCoordonnees(adresse.getCoordonnees());

        } catch (Exception e) {
            System.err.println("Erreur lors de la MAJ de l'adresse : " + e.getMessage());
        }
        return adresse;
    }

    public List<Adresse> getAdresse(int idContact) {
        CoordoneesDAO coordoneesDAO = new CoordoneesDAO();
        List<Adresse> adresses = new ArrayList<>();
        String query = "SELECT * FROM adresse WHERE id_contact = ?";
        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idContact);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Adresse adresse = new Adresse();
                    adresse.setId_adresse(resultSet.getInt("id_adresse"));
                    adresse.setId_contact(resultSet.getInt("id_contact"));
                    adresse.setRue(resultSet.getString("rue"));
                    adresse.setVille(resultSet.getString("ville"));
                    adresse.setCodePostal(resultSet.getString("code_postal"));
                    adresse.setPays(resultSet.getString("pays"));
                    adresse.setCoordonnees(coordoneesDAO.getCoordonnees(adresse.getId_adresse()));
                    adresses.add(adresse);
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des adresses : " + e.getMessage());
        }
        return adresses;
    }
    public Adresse getAdresseById(int idAdresse) {
        CoordoneesDAO coordoneesDAO = new CoordoneesDAO();
        Adresse adresse = new Adresse();
        String query = "SELECT * FROM adresse WHERE id_adresse = ?";
        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idAdresse);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    adresse.setId_adresse(resultSet.getInt("id_adresse"));
                    adresse.setId_contact(resultSet.getInt("id_contact"));
                    adresse.setRue(resultSet.getString("rue"));
                    adresse.setVille(resultSet.getString("ville"));
                    adresse.setCodePostal(resultSet.getString("code_postal"));
                    adresse.setPays(resultSet.getString("pays"));
                    adresse.setCoordonnees(coordoneesDAO.getCoordonnees(adresse.getId_adresse()));
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des adresses : " + e.getMessage());
        }
        return adresse;
    }



}
