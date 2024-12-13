package cal335.projet.DAO;

import cal335.projet.DTO.AdresseDTO;
import cal335.projet.DTO.ContactDTO;
import cal335.projet.DTO.CoordonneesDTO;
import cal335.projet.GestionConnBD;
import cal335.projet.Modele.Adresse;
import cal335.projet.Modele.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactDAO {

    public List<ContactDTO> getListeDesContacts() {
        List<ContactDTO> listeDesContactsDTO = new ArrayList<>();

        String sql = "SELECT c.id_contact, c.nom, c.prenom, c.is_favoris, " +
                "a.id_adresse, a.rue, a.ville, a.code_postal, a.pays, " +
                "co.id_coordonnee, co.latitude, co.longitude " +
                "FROM Contacts c " +
                "LEFT JOIN Adresse a ON c.id_contact = a.id_contact " +
                "LEFT JOIN Coordonnee co ON a.id_adresse = co.id_adresse";

        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            Map<Integer, ContactDTO> contactMap = new HashMap<>();

            while (resultSet.next()) {
                int idContact = resultSet.getInt("id_contact");

                ContactDTO contact = contactMap.get(idContact);
                if (contact == null) {
                    contact = new ContactDTO();
                    contact.setId_contact(idContact);
                    contact.setNom(resultSet.getString("nom"));
                    contact.setPrenom(resultSet.getString("prenom"));
                    contact.setFavoris(Boolean.parseBoolean(resultSet.getString("is_favoris")));
                    contactMap.put(idContact, contact);
                }

                int idAdresse = resultSet.getInt("id_adresse");
                AdresseDTO adresse = new AdresseDTO();
                adresse.setId_adresse(idAdresse);
                adresse.setId_contact(idContact);
                adresse.setRue(resultSet.getString("rue"));
                adresse.setVille(resultSet.getString("ville"));
                adresse.setCodePostal(resultSet.getString("code_postal"));
                adresse.setPays(resultSet.getString("pays"));


                double latitude = resultSet.getDouble("latitude");
                CoordonneesDTO coordonnee = new CoordonneesDTO();
                coordonnee.setId_coordonnees(resultSet.getInt("id_coordonnee"));
                coordonnee.setId_adresse(idAdresse);
                coordonnee.setLatitude(latitude);
                coordonnee.setLongitude(resultSet.getDouble("longitude"));
                adresse.setCoordonnees(coordonnee);

                contact.setAdresse(adresse);
            }

            listeDesContactsDTO.addAll(contactMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listeDesContactsDTO;
    }


    //listdesfavoris
    public List<ContactDTO> getListeDesFavoris() {
        List<ContactDTO> listeDesFavorisDTO = new ArrayList<>();

        String sql = "SELECT c.id_contact, c.nom, c.prenom, c.is_favoris, " +
                "a.id_adresse, a.rue, a.ville, a.code_postal, a.pays, " +
                "co.id_coordonnee, co.latitude, co.longitude " +
                "FROM Contacts c " +
                "LEFT JOIN Adresse a ON c.id_contact = a.id_contact " +
                "LEFT JOIN Coordonnee co ON a.id_adresse = co.id_adresse " +
                "WHERE c.is_favoris = 'TRUE'";

        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            Map<Integer, ContactDTO> contactMap = new HashMap<>();

            while (resultSet.next()) {
                int idContact = resultSet.getInt("id_contact");

                ContactDTO contact = contactMap.get(idContact);
                if (contact == null) {
                    contact = new ContactDTO();
                    contact.setId_contact(idContact);
                    contact.setNom(resultSet.getString("nom"));
                    contact.setPrenom(resultSet.getString("prenom"));
                    contact.setFavoris(Boolean.parseBoolean(resultSet.getString("is_favoris")));
                    contactMap.put(idContact, contact);
                }

                // Address peut etre null ?
                int idAdresse = resultSet.getInt("id_adresse");

                AdresseDTO adresse = new AdresseDTO();
                adresse.setId_adresse(idAdresse);
                adresse.setId_contact(idContact);
                adresse.setRue(resultSet.getString("rue"));
                adresse.setVille(resultSet.getString("ville"));
                adresse.setCodePostal(resultSet.getString("code_postal"));
                adresse.setPays(resultSet.getString("pays"));


                int idCoordonnee = resultSet.getInt("id_coordonnee");

                CoordonneesDTO coordonnee = new CoordonneesDTO();
                coordonnee.setId_coordonnees(idCoordonnee);
                coordonnee.setId_adresse(idAdresse);
                coordonnee.setLatitude(resultSet.getDouble("latitude"));
                coordonnee.setLongitude(resultSet.getDouble("longitude"));
                adresse.setCoordonnees(coordonnee);

                contact.setAdresse(adresse);
            }


            listeDesFavorisDTO.addAll(contactMap.values());
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }

        return listeDesFavorisDTO;
    }


    public Contact ajouterContact(Contact contact) {
        String query = "INSERT INTO Contacts (nom, prenom, is_favoris) VALUES (?, ?, ?)";
        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, contact.getNom());
            preparedStatement.setString(2, contact.getPrenom());
            preparedStatement.setString(3, contact.isFavoris() ? "TRUE" : "FALSE");

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    AdresseDAO adresseDAO = new AdresseDAO();
                    int generatedId = generatedKeys.getInt(1);
                    contact.setId_contact(generatedId);
                    for (Adresse adresse : contact.getListAdresses()) {
                        adresseDAO.ajouterAdresse(adresse, generatedId);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la récupération de l'ID généré : " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du contact : " + e.getMessage());
        }
        return contact;
    }


    public void supprimerContact(Contact contact) {
        String query = "DELETE FROM Contacts WHERE id_contact = ?";
        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, contact.getId_contact());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du contact : " + e.getMessage());
        }
    }


    public void mettreAJour(ContactDTO contact) {
        String query = "UPDATE Contacts SET nom = ?, prenom = ?, is_favoris = ? WHERE id_contact = ?";
        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, contact.getNom());
            preparedStatement.setString(2, contact.getPrenom());
            preparedStatement.setString(3, contact.isFavoris() ? "TRUE" : "FALSE");
            preparedStatement.setInt(4, contact.getId_contact());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du contact : " + e.getMessage());
        }
    }

//    @Override
//    public ContactDTO trouverParId(Integer id) {
//        ContactDTO contact = null;
//        String query = "SELECT * FROM Contacts WHERE id_contact = ?";
//
//        try (Connection connection = GestionConnBD.getConnexion();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, id);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    contact = new ContactDTO();
//                    contact.setId_contact(resultSet.getInt("id_contact"));
//                    contact.setNom(resultSet.getString("nom"));
//                    contact.setPrenom(resultSet.getString("prenom"));
//                    contact.setFavoris(Boolean.parseBoolean(resultSet.getString("is_favoris")));
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println("Erreur lors de la recherche du contact : " + e.getMessage());
//        }
//        return contact;
//    }


}

