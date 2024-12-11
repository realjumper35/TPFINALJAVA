package cal335.projet.Service;

import cal335.projet.DAO.ContactDAO;
import cal335.projet.DTO.ContactDTO;
import cal335.projet.Mapper.ContactMapper;
import cal335.projet.Modele.Adresse;
import cal335.projet.Modele.Contact;
import cal335.projet.Modele.Coordonnees;

import java.util.List;

public class ContactService implements IContactService {

    @Override
    public List<Contact> obtenirTousLesContacts() {
        ContactDAO contactDAO = new ContactDAO();
        List<ContactDTO> contactDTOs = contactDAO.getListeDesContacts();
        return ContactMapper.toEntity(contactDTOs);
    }

    @Override
    public List<Contact> obtenirLesFavoris() {
        ContactDAO contactDAO = new ContactDAO();
        List<ContactDTO> contactDTOs = contactDAO.getListeDesFavoris();
        return ContactMapper.toEntity(contactDTOs);
    }

    @Override
    public ContactDTO ajouterContact(ContactDTO contactDTO) {
        Contact contact = ContactMapper.toEntity(contactDTO);
        for (Adresse adresse : contact.getListAdresses()) {
            Coordonnees coordonnees = obtenirCoordonnees(adresse);
            associerCoordonnees(adresse, coordonnees);
        }
        ContactDAO contactDAO = new ContactDAO();
        return ContactMapper.toDTO(contactDAO.ajouterContact(contact));
    }


    public Coordonnees obtenirCoordonnees(Adresse adresse) {
        MapBoxService mapBoxService = new MapBoxService();
        String LAdresse = adresse.getRue() + " " + adresse.getCodePostal() + " " + adresse.getVille();
        return mapBoxService.obtenirCoordonnees(LAdresse);
    }

    @Override
    public void associerCoordonnees(Adresse adresse, Coordonnees coordonnees) {
        adresse.setCoordonnees(coordonnees);
    }


}
