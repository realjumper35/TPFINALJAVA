package cal335.projet;

import cal335.projet.Controleur.ContactControleur;
import cal335.projet.Modele.Contact;
import cal335.projet.Service.CacheService;
import cal335.projet.Service.ContactService;
import cal335.projet.Service.IContactService;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationCHUMServeur {
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        HttpServer serveur = HttpServer.create(new InetSocketAddress(PORT), 0);

        CacheService cacheService = new CacheService();
        cacheService.InitCacheFav();
        IContactService contactService = new ContactService(cacheService);
        ContactControleur contactControleur = new ContactControleur(contactService);

        //cache fav



        serveur.createContext("/contact", contactControleur);
        serveur.setExecutor(null);
        serveur.start();
        System.out.println("Serveur démarré sur le port " + PORT);
    }
}