package cal335.projet.Mapper;

import cal335.projet.DTO.ContactDTO;
import cal335.projet.Modele.Contact;

import java.util.stream.Collectors;

public class ContactMapper {
    public static ContactDTO toDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setid_contact(contact.getid_contact());
        dto.setNom(contact.getNom());
        dto.setPrenom(contact.getPrenom());
        dto.setFavoris(contact.isFavoris());
        if (contact.getAdresses() != null) {

        }
        return dto;
    }

    public static Contact toEntity(ContactDTO dto) {
        Contact contact = new Contact();
        contact.setid_contact(dto.getid_contact());
        contact.setNom(dto.getNom());
        contact.setPrenom(dto.getPrenom());
        contact.setFavoris(dto.isFavoris());
        if (dto.getAdresses() != null) {
//            contact.setAdresses(dto.getAdresses().stream()
//                    .map(ContactMapper::toEntity)
//                    .collect(Collectors.toList()));
        }
        return contact;
    }
}
