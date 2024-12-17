package cal335.projet.Service;

import cal335.projet.DAO.ContactDAO;
import cal335.projet.DTO.ContactDTO;
import cal335.projet.Mapper.ContactMapper;
import cal335.projet.Modele.Adresse;
import cal335.projet.Modele.Contact;
import cal335.projet.Modele.Coordonnees;

import java.util.List;

public class ContactService implements IContactService {

    private final CacheService cacheService;

    public ContactService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

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

        ContactDTO newContact = ContactMapper.toDTO(contactDAO.ajouterContact(contact));
        if (newContact.isFavoris()) {
            cacheService.ajoutfavCache(contact);
        }
        return newContact;
    }

    @Override
    public void supprimerContact(ContactDTO contactDTO) {
        ContactDAO contactDAO = new ContactDAO();
        try {
            contactDAO.supprimerContact(ContactMapper.toEntity(contactDTO));
        } catch (Exception e) {
            System.out.println("Contact id non trouvé dans contactDAO");
            System.out.println(e);
        }
        cacheService.supprimerfavCache(ContactMapper.toEntity(contactDTO));
    }


    @Override
    public ContactDTO MAJContact(ContactDTO contactDTO) {
        Contact contact = ContactMapper.toEntity(contactDTO);

        for (Adresse adresse : contact.getListAdresses()) {
            Coordonnees coordonnees = obtenirCoordonnees(adresse);
            associerCoordonnees(adresse, coordonnees);
        }
        ContactDAO contactDAO = new ContactDAO();
        if (contact.isFavoris()) {
            cacheService.ajoutfavCache(contact);
        } else {
            try {
                cacheService.supprimerfavCache(ContactMapper.toEntity(contactDTO));
            } catch (Exception e) {
                System.out.println("Contact id non trouvé dans cacheService");
                System.out.println(e);
            }
        }
        return ContactMapper.toDTO(contactDAO.MAJContact(contact));
    }

    @Override
    public ContactDTO trouverContactParId(Integer id) {
        ContactDAO contactDAO = new ContactDAO();
        return ContactMapper.toDTO(contactDAO.getContact(id));
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
