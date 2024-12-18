package cal335.projet.Service;

import cal335.projet.DTO.AdresseDTO;
import cal335.projet.DTO.ContactDTO;
import cal335.projet.DTO.CoordonneesDTO;
import cal335.projet.Modele.Adresse;
import cal335.projet.Modele.Contact;
import cal335.projet.Modele.Coordonnees;

import java.util.List;
import java.util.Map;

public interface IContactService {
    List<Contact> obtenirTousLesContacts();

    List<Contact> obtenirLesFavoris();

    ContactDTO ajouterContact(ContactDTO contactDTO);

    void supprimerContact(ContactDTO contactDTO);

    ContactDTO MAJContact(ContactDTO contactDTO);

    ContactDTO trouverContactParId(Integer id);

    List<ContactDTO> rechercherContactsProches(AdresseDTO adresseDTO);

    Coordonnees obtenirCoordonnees(Adresse adresse);

    void associerCoordonnees(Adresse adresse, Coordonnees coordonnees);

}
