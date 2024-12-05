package cal335.projet.DAO;

import cal335.projet.DTO.AdresseDTO;
import cal335.projet.DTO.ContactDTO;
import cal335.projet.DTO.CoordonneesDTO;
import cal335.projet.GestionConnBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactDAO {
    public List<AdresseDTO> getAdressesByContactId(int id_contact) {
        List<AdresseDTO> adresses = new ArrayList<>();
        String query = "SELECT * FROM Adresse WHERE id_contact = ?";

        try (Connection connection = GestionConnBD.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id_contact);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    AdresseDTO adresse = new AdresseDTO();
                    adresse.setId_adresse(resultSet.getInt("id_adresse"));
                    adresse.setId_contact(resultSet.getInt("id_contact"));
                    adresse.setRue(resultSet.getString("rue"));
                    adresse.setVille(resultSet.getString("ville"));
                    adresse.setCodePostal(resultSet.getString("code_postal"));
                    adresse.setPays(resultSet.getString("pays"));


                    adresses.add(adresse);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
        return adresses;
    }

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
}

