package cal335.projet.Service;

import cal335.projet.DTO.ContactDTO;
import cal335.projet.DTO.CoordonneesDTO;
import cal335.projet.Modele.Contact;

import java.util.List;

public interface IContactService {
    List<Contact> obtenirTousLesContacts();
//    void ajouterContact(ContactDTO contactDTO);
//
//    void supprimerContact(Integer id);
//
//    void mettreAJourContact(ContactDTO contactDTO);
//
//    ContactDTO trouverContactParId(Integer id);
//
//    List<ContactDTO> rechercherContactsProches(CoordonneesDTO coordonnees, double rayon);
//
//    void marquerCommeFavori(Integer id);
//
//    void retirerDesFavoris(Integer id);
}
