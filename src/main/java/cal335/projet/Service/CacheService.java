package cal335.projet.Service;

import cal335.projet.DAO.ContactDAO;
import cal335.projet.Mapper.ContactMapper;
import cal335.projet.Modele.Contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheService {

    public Map<Integer, Contact> getCacheFavoris() {
        ContactDAO contactDAO = new ContactDAO();
        HashMap<Integer, Contact> cacheFavoris = new HashMap<>();
        List<Contact> contacts = ContactMapper.toEntity(contactDAO.getListeDesFavoris());
        int iterateur = 0;
        //pour chaque contact ajoute dans hashmap avec chiffre de 0 a n avec contact comme valeur
        for (Contact contact : contacts) {
            cacheFavoris.put(iterateur, contact);
            iterateur++;
        }
        return cacheFavoris;
    }
}
