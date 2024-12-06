package cal335.projet.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapBoxService {
    private final String API_token = "pk.eyJ1IjoieW95bzM1IiwiYSI6ImNsdWhmbmhmZzAzeXUya2s2aTc1Y2d6NXUifQ.GXJiCwbRF6HKY5kcEReqYQ";
    private final String API_URL = "https://api.mapbox.com/geocoding/v5/mapbox.places/";
//    https://api.mapbox.com/geocoding/v5/mapbox.places/Adresse+ici.json?access_token=VOTRE_CLE_API


    public String obtenirCoordonnees(String adresse) {
        String endPoint = API_URL + adresse + ".json?access_token=" + API_token;
        return envoyerRequete(endPoint);
    }


    private String envoyerRequete(String endPoint) {
        StringBuilder reponse = new StringBuilder();
        try {
            URL urlFinale = new URL(endPoint);
            HttpURLConnection connexionHttp = (HttpURLConnection) urlFinale.openConnection();
            connexionHttp.setRequestMethod("GET");

            BufferedReader entrant = new BufferedReader(new InputStreamReader(connexionHttp.getInputStream()));
            String ligneRecue;

            while ((ligneRecue = entrant.readLine()) != null) {
                reponse.append(ligneRecue);
            }
            entrant.close();
            connexionHttp.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reponse.toString();
    }

}
