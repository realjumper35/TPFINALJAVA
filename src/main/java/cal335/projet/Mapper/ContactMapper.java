package cal335.projet.Mapper;

import cal335.projet.DTO.AdresseDTO;
import cal335.projet.DTO.ContactDTO;
import cal335.projet.Modele.Adresse;
import cal335.projet.Modele.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactMapper {
    public static ContactDTO toDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        if (contact.getId_contact() != null) {
            dto.setId_contact(contact.getId_contact());
        }
        dto.setNom(contact.getNom());
        dto.setPrenom(contact.getPrenom());
        dto.setFavoris(contact.isFavoris());
        dto.setListAdresses(AdresseMapper.toDTO(contact.getListAdresses()));
        return dto;
    }

    public static Contact toEntity(ContactDTO dto) {
        Contact contact = new Contact();
        if (dto.getId_contact() != null) {
            contact.setId_contact(dto.getId_contact());
        }
        contact.setNom(dto.getNom());
        contact.setPrenom(dto.getPrenom());
        contact.setFavoris(dto.isFavoris());
        contact.setListAdresses(AdresseMapper.toEntity(dto.getListAdresses()));
        return contact;
    }

    public static List<ContactDTO> toDTO(List<Contact> contacts) {
        List<ContactDTO> ListcontactsDTOs = new ArrayList<>();
        for (Contact contact : contacts) {
            ListcontactsDTOs.add(toDTO(contact));
        }
        return ListcontactsDTOs;
    }

    public static List<Contact> toEntity(List<ContactDTO> contactDTOs) {
        List<Contact> Listcontacts = new ArrayList<>();
        for (ContactDTO dto : contactDTOs) {
            Listcontacts.add(toEntity(dto));
        }
        return Listcontacts;
    }
}
