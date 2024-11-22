package cal335.projet.Mapper;

import cal335.projet.DTO.AdresseDTO;
import cal335.projet.Modele.Adresse;

public class AdresseMapper {

    public static AdresseDTO toDTO(Adresse adresse) {
        AdresseDTO dto = new AdresseDTO();
        dto.setid_adresse(adresse.getid_adresse());
        dto.setRue(adresse.getRue());
        dto.setVille(adresse.getVille());
        dto.setCodePostal(adresse.getCodePostal());
        dto.setPays(adresse.getPays());
        if (adresse.getCoordonnees() != null) {
//            dto.setCoordonnees(new CoordonneesDTO(
//                    adresse.getCoordonnees().getLatitude(),
//                    adresse.getCoordonnees().getLongitude()));
        }
        return dto;
    }

    public static Adresse toEntity(AdresseDTO dto) {
        Adresse adresse = new Adresse();
        adresse.setid_adresse(dto.getid_adresse());
        adresse.setRue(dto.getRue());
        adresse.setVille(dto.getVille());
        adresse.setCodePostal(dto.getCodePostal());
        adresse.setPays(dto.getPays());
        if (dto.getCoordonnees() != null) {
//            adresse.setCoordonnees(new Coordonnees(
//                    dto.getCoordonnees().getLatitude(),
//                    dto.getCoordonnees().getLongitude()));
        }
        return adresse;
    }
}
