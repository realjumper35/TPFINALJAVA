package cal335.projet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestionConnBD {
    private static GestionConnBD instance ;
    private  static Connection connexion;
    private static final String URL = "jdbc:sqlite:src/main/resources/taches.db";

    private GestionConnBD() {
        try {
            connexion = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }

    public static synchronized GestionConnBD getInstance() {
        if (instance == null) {
            instance = new GestionConnBD();
        }
        return instance;
    }

    public Connection getConnexion() {
        return connexion;
    }

}
