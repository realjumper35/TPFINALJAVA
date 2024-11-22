package cal335.projet.Service;

public class ContactService {
//    private final ContactDAO contactDAO;
//    private final CacheService cacheService;
//
//    public ContactServiceImpl(ContactDAO contactDAO, CacheService cacheService) {
//        this.contactDAO = contactDAO;
//        this.cacheService = cacheService;
//    }
//
//    @Override
//    public void ajouterContact(ContactDTO contactDTO) {
//        Contact contact = ContactMapper.toEntity(contactDTO);
//        contactDAO.ajouter(contact);
//    }
//
//    @Override
//    public void supprimerContact(Integer id) {
//        Contact contact = contactDAO.trouverParId(id);
//        if (contact != null) {
//            contactDAO.supprimer(contact);
//            cacheService.retirerFavori(contact);
//        }
//    }
//
//    @Override
//    public void mettreAJourContact(ContactDTO contactDTO) {
//        Contact contact = ContactMapper.toEntity(contactDTO);
//        contactDAO.mettreAJour(contact);
//    }
//
//    @Override
//    public ContactDTO trouverContactParId(Integer id) {
//        Contact contact = contactDAO.trouverParId(id);
//        return (contact != null) ? ContactMapper.toDTO(contact) : null;
//    }
//
//    @Override
//    public List<ContactDTO> rechercherContactsProches(CoordonneesDTO coordonneesDTO, double rayon) {
//        List<Contact> contacts = contactDAO.trouverTous();
//        Coordonnees centre = new Coordonnees(coordonneesDTO.getLatitude(), coordonneesDTO.getLongitude());
//
//        return contacts.stream()
//                .filter(contact -> contact.getAdresses().stream()
//                        .anyMatch(adresse -> CalculateurDistance.calculerDistance(
//                                centre, adresse.getCoordonnees()) <= rayon))
//                .map(ContactMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void marquerCommeFavori(Integer id) {
//        Contact contact = contactDAO.trouverParId(id);
//        if (contact != null && !contact.isFavoris()) {
//            contact.setFavoris(true);
//            contactDAO.mettreAJour(contact);
//            cacheService.ajouterFavori(contact);
//        }
//    }
//
//    @Override
//    public void retirerDesFavoris(Integer id) {
//        Contact contact = contactDAO.trouverParId(id);
//        if (contact != null && contact.isFavoris()) {
//            contact.setFavoris(false);
//            contactDAO.mettreAJour(contact);
//            cacheService.retirerFavori(contact);
//        }
//    }
}
