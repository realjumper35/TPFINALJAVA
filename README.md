# 🛠️ Application **mes_chums**

L'application **mes_chums** permet d'interagir avec une base de données en proposant des API pour gérer des contacts. Chaque contact peut avoir une ou plusieurs adresses et des coordonnées géographiques associées.

---

## 📝 **Informations Générales**

- **Langage** : Java
- **Outils d'aide** : Maven  
  La gestion de projet et la gestion des dépendances est gérée par Maven. Maven permet de gérer les dépendances du projet et de les télécharger automatiquement.

---

## 🛡️ **Dépendances Utilisées**

### `jackson-databind`
Jackson permet de convertir des objets Java en JSON et vice versa. Il est utilisé pour la sérialisation et la désérialisation des données de l'application.

### `sqlite-jdbc`
sqlite-jdbc est un pilote JDBC pour SQLite. Il est utilisé pour la connexion à la base de données SQLite.

### `HikariCP`
HikariCP est un pool de connexion JDBC. Il optimise les performances en gardant les connexions ouvertes et en les réutilisant pour éviter les coûts ou conflits de connexion.

---

## 🛏️ **Serveur**

- **Technologie** : `HttpServer` de Java
- **Port** : `8080` (par défaut)  
  Le serveur tourne en local et se lance automatiquement au démarrage de l'application.

---

## 📂 **Base de Données**

La base de données est une base SQLite stockée localement. Elle contient trois tables : `Contacts`, `Adresse` et `Coordonnee`.

### 📚 **Structure des Tables**

```sql
CREATE TABLE Contacts (
    id_contact INTEGER PRIMARY KEY AUTOINCREMENT,
    nom TEXT NOT NULL,
    prenom TEXT NOT NULL,
    is_favoris BOOLEAN DEFAULT FALSE
);

CREATE TABLE Adresse (
    id_adresse INTEGER PRIMARY KEY AUTOINCREMENT,
    id_contact INTEGER NOT NULL,
    rue TEXT NOT NULL,
    ville TEXT NOT NULL,
    code_postal TEXT NOT NULL,
    pays TEXT NOT NULL,
    FOREIGN KEY (id_contact) REFERENCES Contacts (id_contact) ON DELETE CASCADE
);

CREATE TABLE Coordonnee (
    id_coordonnee INTEGER PRIMARY KEY AUTOINCREMENT,
    id_adresse INTEGER UNIQUE NOT NULL,
    latitude REAL NOT NULL,
    longitude REAL NOT NULL,
    FOREIGN KEY (id_adresse) REFERENCES Adresse (id_adresse) ON DELETE CASCADE
);
```

---

## 📡 **API Externe Utilisée**

L'application utilise l'API de **MapBox** pour obtenir les coordonnées géographiques d'une adresse donnée.

---

## 🛠️ **Endpoints de l'Application**

### Ajouter un Contact
- **Méthode** : `POST`
- **URL** : `http://localhost:8080/contact/Ajouter`
- **Header** : Objet `Contact` avec un ou plusieurs objets `Adresse` au format JSON.

### Mettre à Jour un Contact
- **Méthode** : `PUT`
- **URL** : `http://localhost:8080/contact/MAJ`
- **Header** : Objet `Contact` incluant l'ID du contact au format JSON.  
  Retourne un objet `Contact` avec les modifications appliquées.

### Supprimer un Contact
- **Méthode** : `DELETE`
- **URL** : `http://localhost:8080/contact/Supprimer`
- **Header** : Objet `Contact` incluant l'ID du contact au format JSON.  
  Retourne `200 OK` si l'opération réussit.

### Trouver un Contact par ID
- **Méthode** : `GET`
- **URL** : `http://localhost:8080/contact/ID`  
  L'ID est passé en paramètre de la requête. Retourne un objet `Contact` au format JSON.

### Rechercher des Contacts Proches
- **Méthode** : `GET`
- **URL** : `http://localhost:8080/contact/Aproxi`
- **Header** : Objet `Adresse` sans besoin d'ID au format JSON.  
  Retourne une liste de contacts au format JSON.

### Trouver les Contacts Favoris
- **Méthode** : `GET`
- **URL** : `http://localhost:8080/contact/LesFavoris`  
  Retourne une liste de contacts favoris au format JSON.

### Afficher Tous les Contacts
- **Méthode** : `GET`
- **URL** : `http://localhost:8080/contact/LesContacts`  
  Retourne une liste de tous les contacts au format JSON.

---

Architecture de l'application:

L'application est divisée en 6 packages:

Modele: Contient les classes qui représentent les objets de l'application. Les classes du modèle sont utilisées pour
stocker et manipuler les données dans l'application .

DTO: Contient les classes qui représentent les objets de transfert de données. Les classes DTO sont utilisées pour
transférer les données recu par les API.

DAO: Contient les classes(Contact,Adresse,Coordonnées) qui gèrent l'accès à la base de données. Les classes DAO sont
responsables de la création, la
suppression, la mise à jour et la récupération des données de la base de données. CHUM.db qui a la racine du projet.

Mapper: Contient les classes qui convertissent les objets et liste d'objet en objectDTO et vice versa.

Service: Contient les classes qui gèrent la logique métier de l'application. Les classes Service sont responsables de la
gestion des contacts. En utilisant les classes DAO pour accéder à la base de données. C'est aussi dans les services que
les mappers sont utilisés pour convertir les objets en objectDTO.

CalculateurDistance est dans service mais est utilisé seulement pour calculer la distance entre 2 coordonnées
géographiques.

Le controleur: Contient les classes qui gèrent les requêtes HTTP. Les classes Controller est comme l'éguilleur
principal. Il va rediriger les requetes vers les services appropriés en fonction du endpoint.Dans ce cas il y a que 1
seul controleur qui redirige seulement les requetes contacte.

Main:
Le Main est la classe principale de l'application. C'est ici que le serveur est lancé et que le caches est initialisé.
Et c'est ici que les requetes sont redirigé vers le controleur.

Le Cache:
Dans cette applications il y a 1 caches sous forme de MAP pour stocker les contacts favoris. Il permet de ne pas avoir à
faire des
requetes à la base de données à chaque fois qu'on veut afficher les contacts favoris. Cela permet d'optimiser les
performances de l'application. Comme ca des que l'application est lancé les contacts favoris sont chargé en mémoire. Et
mis a jour en meme temps que la base de données.

le chemin du cache:

Initialisation dans le Main:
cacheService.InitCacheFav();

Mise à jour dans le controleur après une MAj dans la BD :
public ContactDTO MAJContact(ContactDTO contactDTO) {
Contact contact = ContactMapper.toEntity(contactDTO);

ContactDAO contactDAO = new ContactDAO();
ContactDTO contactMaj = new ContactDTO();
try {
contactMaj = ContactMapper.toDTO(contactDAO.MAJContact(contact)); //essai de mettre a jour le contact dans la BD
if (contact.isFavoris()) {
cacheService.ajoutfavCache(contact); si le contact est favoris on l'ajoute au cache
} else {
try {
cacheService.supprimerfavCache(ContactMapper.toEntity(contactDTO)); //sinon on essaie de le supprimer du cache
} catch (Exception e) {
System.out.println("Contact id non trouvé dans cacheService");
System.out.println(e);
}
}
} catch (Exception e) {
System.out.println("Contact id non trouvé dans contactDAO");
System.out.println(e);
}

return contactMaj;
}

Cache service pour modifier la MAP(@override .equals et .hashcode dans Contact):

public void ajoutfavCache(Contact contact) {
cacheFavoris.put(cacheFavoris.size() + 1, contact);
System.out.println("Nombre de favoris dans le cache APRES AJOUT: " + cacheFavoris.size());
}

public void supprimerfavCache(Contact contact) {
cacheFavoris.entrySet().removeIf(entry -> entry.getValue().getId_contact().equals(contact.getId_contact()));
System.out.println("Nombre de favoris dans le cache APRES SUPPRESSION: " + cacheFavoris.size());

}

API de Geolocalisation avec MapBox:
L'API de géocodage de MapBoz est utilisée pour convertir une adresse en coordonnées GPS (latitude et longitude).

Contruit la requete a envoyer a MapBox avec URL et token;
public Coordonnees obtenirCoordonnees(String adresse) {
String endPoint = API_URL + adresse + ".json?access_token=" + API_token;
return deserialiserCoordonnees(envoyerRequete(endPoint));
}

etablir la connexion et envoyer la requete a MapBox .
HttpURLConnection connexionHttp = (HttpURLConnection) urlFinale.openConnection();
connexionHttp.setRequestMethod("GET");

            BufferedReader entrant = new BufferedReader(new InputStreamReader(connexionHttp.getInputStream()));
            String ligneRecue;

            while ((ligneRecue = entrant.readLine()) != null) {
                reponse.append(ligneRecue);
            }
            entrant.close();
            connexionHttp.disconnect();

utilise la fonction deserialiserCoordonnees pour convertir le JSON en objet Coordonnees.

Injection de dépendances:
ContactService : Le constructeur de ContactService prend un objet CacheService en paramètre. Cela permet de remplacer
facilement l'implémentation de CacheService par une autre si nécessaire, sans modifier le code de ContactService.

ContactControleur : Le constructeur de ContactControleur prend un objet IContactService en paramètre. Cela permet de
remplacer facilement l'implémentation de IContactService par une autre, facilitant ainsi les tests unitaires et les
modifications futures.