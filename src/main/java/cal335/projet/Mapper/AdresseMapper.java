package cal335.projet.Mapper;

import cal335.projet.DTO.AdresseDTO;
import cal335.projet.Modele.Adresse;

import java.util.ArrayList;
import java.util.List;

public class AdresseMapper {

    public static List<AdresseDTO> toDTO(List<Adresse> adresses) {
        List<AdresseDTO> listAdressesDTO = new ArrayList<>();
        for (Adresse adresse : adresses) {
            listAdressesDTO.add(toDTO(adresse));
        }
        return listAdressesDTO;
    }


    public static List<Adresse> toEntity(List<AdresseDTO> adressesDTO) {
        List<Adresse> listAdresses = new ArrayList<>();
        for (AdresseDTO dto : adressesDTO) {
            listAdresses.add(toEntity(dto));
        }
        return listAdresses;
    }

    public static AdresseDTO toDTO(Adresse adresse) {
        AdresseDTO dto = new AdresseDTO();
        dto.setId_adresse(adresse.getId_adresse());
        dto.setRue(adresse.getRue());
        dto.setVille(adresse.getVille());
        dto.setCodePostal(adresse.getCodePostal());
        dto.setPays(adresse.getPays());
        dto.setCoordonnees(CoordonneesMapper.toDTO(adresse.getCoordonnees()));

        return dto;
    }


    public static Adresse toEntity(AdresseDTO dto) {
        Adresse adresse = new Adresse();
        adresse.setId_adresse(dto.getId_adresse());
        adresse.setRue(dto.getRue());
        adresse.setVille(dto.getVille());
        adresse.setCodePostal(dto.getCodePostal());
        adresse.setPays(dto.getPays());
        adresse.setCoordonnees(CoordonneesMapper.toEntity(dto.getCoordonnees()));
        return adresse;
    }
}
