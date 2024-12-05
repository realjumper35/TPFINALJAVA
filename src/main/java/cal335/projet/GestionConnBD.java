package cal335.projet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestionConnBD {
    private static GestionConnBD instance;
    private static final String URL = "jdbc:sqlite:src/main/resources/CHUM.db";
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
//        config.setUsername("");
//        config.setPassword("");
        config.setMaximumPoolSize(10);
        dataSource = new HikariDataSource(config);
    }

    private GestionConnBD() {
        try {
            DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }

//    public static synchronized GestionConnBD getInstance() {
//        if (instance == null) {
//            instance = new GestionConnBD();
//        }
//        return instance;
//    }

    public static Connection getConnexion() throws SQLException {
        return dataSource.getConnection();
    }

}
