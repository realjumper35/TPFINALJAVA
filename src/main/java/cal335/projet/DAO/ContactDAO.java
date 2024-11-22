package cal335.projet.DAO;

import cal335.projet.DTO.ContactDTO;
import cal335.projet.GestionConnBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    public List<ContactDTO> getListeDesContacts() {
        List<ContactDTO> ListeDesContactsDTO = new ArrayList<>();

        try (Connection connection = GestionConnBD.getInstance().getConnexion();
             PreparedStatement PreparedStatement = connection.prepareStatement("SELECT * FROM Contacts");
             ResultSet resultSet = PreparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ContactDTO unContactDTO = new ContactDTO();
                unContactDTO.setid_contact(resultSet.getInt("id_contact"));
                unContactDTO.setNom(resultSet.getString("nom"));
                unContactDTO.setPrenom(resultSet.getString("prenom"));
                unContactDTO.setFavoris(resultSet.getBoolean("is_favoris"));
                ListeDesContactsDTO.add(unContactDTO);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
        return ListeDesContactsDTO;
    }
}

