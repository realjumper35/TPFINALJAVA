package cal335.projet.Mapper;

import cal335.projet.DTO.AdresseDTO;
import cal335.projet.DTO.CoordonneesDTO;
import cal335.projet.Modele.Adresse;
import cal335.projet.Modele.Coordonnees;

public class CoordonneesMapper {


        public static CoordonneesDTO toDTO(Coordonnees coordonnes) {
            CoordonneesDTO dto = new CoordonneesDTO();
            dto.setId_coordonnees(coordonnes.getId_coordonnees());
            dto.setId_adresse(coordonnes.getId_adresse());
            dto.setLatitude(coordonnes.getLatitude());
            dto.setLongitude(coordonnes.getLongitude());
            return dto;
        }

        public static Coordonnees toEntity(CoordonneesDTO dto) {
            Coordonnees coordonnes = new Coordonnees();
            coordonnes.setId_coordonnees(dto.getId_coordonnees());
            coordonnes.setId_adresse(dto.getId_adresse());
            coordonnes.setLatitude(dto.getLatitude());
            coordonnes.setLongitude(dto.getLongitude());
            return coordonnes;
        }
}
