package cal335.projet.Mapper;

import cal335.projet.DTO.AdresseDTO;
import cal335.projet.DTO.CoordonneesDTO;
import cal335.projet.Modele.Adresse;
import cal335.projet.Modele.Coordonnees;

public class CoordonneesMapper {


        public static CoordonneesDTO toDTO(Coordonnees coordonnes) {
            CoordonneesDTO dto = new CoordonneesDTO();
            dto.setLatitude(coordonnes.getLatitude());
            dto.setLongitude(coordonnes.getLongitude());
            return dto;
        }

        public static Coordonnees toEntity(CoordonneesDTO dto) {
            Coordonnees coordonnes = new Coordonnees();
            coordonnes.setLatitude(dto.getLatitude());
            coordonnes.setLongitude(dto.getLongitude());
            return coordonnes;
        }
}
