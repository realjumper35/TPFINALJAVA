package cal335.projet;

import cal335.projet.Controleur.ContactControleur;
import cal335.projet.Service.ContactService;
import cal335.projet.Service.IContactService;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ApplicationCHUMServeur {
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        HttpServer serveur = HttpServer.create(new InetSocketAddress(PORT), 0);

        IContactService contactService = new ContactService();

        ContactControleur controleur = new ContactControleur(contactService);
        serveur.createContext("/contact", controleur);
        serveur.setExecutor(null);
        serveur.start();
        System.out.println("Serveur démarré sur le port " + PORT);

    }
}