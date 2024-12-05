package cal335.projet.Service;

import cal335.projet.DAO.ContactDAO;
import cal335.projet.DTO.ContactDTO;
import cal335.projet.Mapper.ContactMapper;
import cal335.projet.Modele.Contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


}
