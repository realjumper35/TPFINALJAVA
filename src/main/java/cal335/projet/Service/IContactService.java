package cal335.projet.Service;

import cal335.projet.DTO.ContactDTO;
import cal335.projet.DTO.CoordonneesDTO;

import java.util.List;

public interface IContactService {
    void ajouterContact(ContactDTO contactDTO);

    void supprimerContact(Integer id);

    void mettreAJourContact(ContactDTO contactDTO);

    ContactDTO trouverContactParId(Integer id);

    List<ContactDTO> rechercherContactsProches(CoordonneesDTO coordonnees, double rayon);

    void marquerCommeFavori(Integer id);

    void retirerDesFavoris(Integer id);
}
