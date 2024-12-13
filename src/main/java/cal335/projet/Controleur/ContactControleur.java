package cal335.projet.Controleur;

import cal335.projet.DTO.ContactDTO;
import cal335.projet.Service.ContactService;
import cal335.projet.Service.IContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ContactControleur implements HttpHandler {
    private final IContactService contactService;
    private final ObjectMapper objectMapper;

    public ContactControleur(IContactService contactService) {
        this.contactService = contactService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange echange) throws IOException {

        String methode = echange.getRequestMethod();
        String chemin = echange.getRequestURI().getPath();

        if ("GET".equals(methode) && chemin.equals("/contact/LesContacts")) {
            this.obtenirTousLesContacts(echange);
        } else if ("GET".equals(methode) && chemin.equals("/contact/LesFavoris")) {
            this.obtenirLesFavoris(echange);
        } else if ("POST".equals(methode) && chemin.equals("/contact/Ajouter")) {
            this.ajouterContact(echange);
        } else if ("DELETE".equals(methode) && chemin.equals("/contact/Supprimer")) {
            this.supprimerContact(echange);

        } else{
            echange.sendResponseHeaders(404, -1);
        }
    }

    private void obtenirTousLesContacts(HttpExchange echange) throws IOException {

        String reponseJson = objectMapper.writeValueAsString(contactService.obtenirTousLesContacts());

        echange.getResponseHeaders().set("Content-Type", "application/json");
        echange.sendResponseHeaders(200, reponseJson.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = echange.getResponseBody();
        os.write(reponseJson.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    private void obtenirLesFavoris(HttpExchange echange) throws IOException {

        String reponseJson = objectMapper.writeValueAsString(contactService.obtenirLesFavoris());

        echange.getResponseHeaders().set("Content-Type", "application/json");
        echange.sendResponseHeaders(200, reponseJson.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = echange.getResponseBody();
        os.write(reponseJson.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    private void ajouterContact(HttpExchange echange) throws IOException {

        ContactDTO contactDTO = objectMapper.readValue(echange.getRequestBody(), ContactDTO.class);
        String reponseJson = objectMapper.writeValueAsString(contactService.ajouterContact(contactDTO));

        echange.getResponseHeaders().set("Content-Type", "application/json");
        echange.sendResponseHeaders(200, reponseJson.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = echange.getResponseBody();
        os.write(reponseJson.getBytes(StandardCharsets.UTF_8));
        os.close();

    }
    private void supprimerContact(HttpExchange echange) throws IOException {
        ContactDTO contactDTO = objectMapper.readValue(echange.getRequestBody(), ContactDTO.class);
        contactService.supprimerContact(contactDTO);
        echange.sendResponseHeaders(200, -1);

    }
}
