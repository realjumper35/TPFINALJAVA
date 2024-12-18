package cal335.projet.Controleur;

import cal335.projet.DTO.AdresseDTO;
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
        } else if ("PUT".equals(methode) && chemin.equals("/contact/MAJ")) {
            this.MAJContact(echange);
        } else if ("GET".equals(methode) && chemin.startsWith("/contact/UnContact/")) {
            this.obtenirContactParId(echange);
        }else if ("GET".equals(methode) && chemin.equals("/contact/Aproxi")) {
            this.obtenirContactProche(echange);


        } else {
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

    private void MAJContact(HttpExchange echange) throws IOException {
        ContactDTO contactDTO = objectMapper.readValue(echange.getRequestBody(), ContactDTO.class);
        String reponseJson = objectMapper.writeValueAsString(contactService.MAJContact(contactDTO));

        echange.getResponseHeaders().set("Content-Type", "application/json");
        echange.sendResponseHeaders(200, reponseJson.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = echange.getResponseBody();
        os.write(reponseJson.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    private void obtenirContactParId(HttpExchange echange) throws IOException {
        String chemin = echange.getRequestURI().getPath();
        String[] segments = chemin.split("/");

        if (segments.length == 4 && "UnContact".equals(segments[2])) {
            try {
                int id = Integer.parseInt(segments[3]);
                System.out.println("ID: " + id);
                ContactDTO contactDTO = contactService.trouverContactParId(id);

                if (contactDTO != null) {
                    String reponseJson = objectMapper.writeValueAsString(contactDTO);
                    echange.getResponseHeaders().set("Content-Type", "application/json");
                    echange.sendResponseHeaders(200, reponseJson.getBytes(StandardCharsets.UTF_8).length);
                    OutputStream os = echange.getResponseBody();
                    os.write(reponseJson.getBytes(StandardCharsets.UTF_8));
                    os.close();
                } else {
                    echange.sendResponseHeaders(404, -1);
                }
            } catch (NumberFormatException e) {
                echange.sendResponseHeaders(400, -1);
            }
        } else {
            echange.sendResponseHeaders(400, -1);
        }
    }

   private void obtenirContactProche(HttpExchange echange) throws IOException {
    try {
        AdresseDTO adresseDTO = objectMapper.readValue(echange.getRequestBody(), AdresseDTO.class);
        String reponseJson = objectMapper.writeValueAsString(contactService.rechercherContactsProches(adresseDTO));

        echange.getResponseHeaders().set("Content-Type", "application/json");
        echange.sendResponseHeaders(200, reponseJson.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = echange.getResponseBody();
        os.write(reponseJson.getBytes(StandardCharsets.UTF_8));
        os.close();
    } catch (IOException e) {
        echange.sendResponseHeaders(500, -1);
    }
}
}
