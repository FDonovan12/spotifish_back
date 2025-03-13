package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.MusicalGenre;
import fr.donovan.spotifish.repository.MusicalGenreRepository;
import fr.donovan.spotifish.dto.MusicalGenreDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import fr.donovan.spotifish.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class MusicalGenreService  {

    private final MusicalGenreRepository musicalGenreRepository;
    private final SecurityService securityService;
    public List<MusicalGenre> findAll() {
        return this.musicalGenreRepository.findAll();
    }

    public MusicalGenre getObjectById(String id) {
        Optional<MusicalGenre> optionalMusicalGenre = musicalGenreRepository.findById(id);
        MusicalGenre musicalGenre = optionalMusicalGenre.orElseThrow(() -> new NotFoundSpotifishException("MusicalGenreService - getObjectById("+id+")", "MusicalGenre", id));
        securityService.assertCanSee(musicalGenre);
        return musicalGenre;
    }
    public MusicalGenre getObjectBySlug(String slug) {
        Optional<MusicalGenre> optionalMusicalGenre = musicalGenreRepository.findBySlug(slug);
        MusicalGenre musicalGenre = optionalMusicalGenre.orElseThrow(() -> new NotFoundSpotifishException("MusicalGenreService - getObjectBySlug("+slug+")", "MusicalGenre", slug));
        securityService.assertCanSee(musicalGenre);
        return musicalGenre;
    }

    public Boolean delete(String id) {
        MusicalGenre musicalGenre = getObjectById(id);
        securityService.assertCanDelete(musicalGenre);
        musicalGenreRepository.delete(musicalGenre);
        return true;
    }

    public MusicalGenre persist(MusicalGenreDTO musicalGenreDTO) {
        return persist(musicalGenreDTO, null);
    }

    public MusicalGenre persist(MusicalGenreDTO musicalGenreDTO, String id) {
        MusicalGenre musicalGenre = new MusicalGenre();
        if (id != null) {
            musicalGenre = getObjectById(id);
            securityService.assertCanEdit(musicalGenre);
        }
        musicalGenre = getObjectFromDTO(musicalGenreDTO, musicalGenre);
        return musicalGenreRepository.saveAndFlush(musicalGenre);
    }

    public MusicalGenreDTO getDTOById(String id) {
        MusicalGenre musicalGenre = getObjectById(id);
        return getDTOFromObject(musicalGenre);
    }

    public MusicalGenreDTO getDTOFromObject(MusicalGenre musicalGenre) {
        MusicalGenreDTO musicalGenreDTO = new MusicalGenreDTO();
        musicalGenreDTO.setName(musicalGenre.getName());
        musicalGenreDTO.setDescription(musicalGenre.getDescription());
        musicalGenreDTO.setImage(musicalGenre.getImage());
        return musicalGenreDTO;
    }
    public MusicalGenre getObjectFromDTO(MusicalGenreDTO musicalGenreDTO) {
        return getObjectFromDTO(musicalGenreDTO, new MusicalGenre());
    }
    public MusicalGenre getObjectFromDTO(MusicalGenreDTO musicalGenreDTO, MusicalGenre musicalGenre) {
        musicalGenre.setName(musicalGenreDTO.getName());
        musicalGenre.setDescription(musicalGenreDTO.getDescription());
        musicalGenre.setImage(musicalGenreDTO.getImage());
        musicalGenre.setSlug("test");
        return musicalGenre;
    }
}