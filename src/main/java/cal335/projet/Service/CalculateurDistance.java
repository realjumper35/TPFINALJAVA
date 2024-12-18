package cal335.projet.Service;

import cal335.projet.Modele.Coordonnees;

public class CalculateurDistance {
    public double calculerDistance(Coordonnees adresse1, Coordonnees adresse2) {
        final int rayonTerre = 6371; // Rayon de la terre en kilomètres

        double latDistance = Math.toRadians(adresse2.getLatitude() - adresse1.getLatitude());
        double lonDistance = Math.toRadians(adresse2.getLongitude() - adresse1.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(adresse1.getLatitude())) * Math.cos(Math.toRadians(adresse2.getLatitude())) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = rayonTerre * c; // Distance en kilomètres
        return distance;// retourne la distance en kilomètres
    }
}
