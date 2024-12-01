package cal335.projet.Service;

import cal335.projet.DAO.ContactDAO;
import cal335.projet.DTO.ContactDTO;
import cal335.projet.Mapper.ContactMapper;
import cal335.projet.Modele.Contact;

import java.util.List;

public class ContactService implements IContactService {

    @Override
    public List<Contact> obtenirTousLesContacts() {
        ContactDAO contactDAO = new ContactDAO();
        List<ContactDTO> contactDTOs = contactDAO.getListeDesContacts();
        return ContactMapper.toEntity(contactDTOs);
    }

}
