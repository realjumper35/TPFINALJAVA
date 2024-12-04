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

//    @Override
//    public Map<Integer, Contact> obtenirLesFavoris() {
//        ContactDAO contactDAO = new ContactDAO();
//        List<ContactDTO> contactDTOs = contactDAO.getListeDesFavoris();
//        Map<Integer, Contact> favorisMap = new HashMap<>();
//        int index = 0;
//        for (ContactDTO contactDTO : contactDTOs) {
//            Contact contact = ContactMapper.toEntity(contactDTO);
//            favorisMap.put(index++, contact);
//        }
//        return favorisMap;
//    }

}
