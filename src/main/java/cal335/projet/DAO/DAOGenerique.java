package cal335.projet.DAO;

public interface DAOGenerique<T> {

    // Méthode pour ajouter un objet
    void ajouterContact(T objet);

    // Méthode pour supprimer un objet
    void supprimer(T objet);

    // Méthode pour mettre à jour un objet
    void mettreAJour(T objet);

    // Méthode pour trouver un objet par son ID
    T trouverParId(Integer id);
}

