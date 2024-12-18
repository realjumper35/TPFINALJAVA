package cal335.projet.DAO;

import cal335.projet.Modele.Contact;

import java.util.List;

public interface DAOGenerique<T> {
    T ajouter(T t);
    void supprimer(T t);
    T mettreAJour(T t);
    T trouverParId(Integer id);
    List<T> trouverTous();


}

