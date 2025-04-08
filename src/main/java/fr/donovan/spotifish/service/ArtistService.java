package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.Artist;
import fr.donovan.spotifish.repository.ArtistRepository;
import fr.donovan.spotifish.dto.ArtistDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import fr.donovan.spotifish.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class ArtistService  {

    private final ArtistRepository artistRepository;
    private final SecurityService securityService;
    private BCryptPasswordEncoder passwordEncoder;
    public List<Artist> findAll() {
        return this.artistRepository.findAll();
    }

    public Artist getObjectById(String id) {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        Artist artist = optionalArtist.orElseThrow(() -> new NotFoundSpotifishException("ArtistService - getObjectById("+id+")", "Artist", id));
        securityService.assertCanSee(artist);
        return artist;
    }
    public Artist getObjectBySlug(String slug) {
        Optional<Artist> optionalArtist = artistRepository.findBySlug(slug);
        Artist artist = optionalArtist.orElseThrow(() -> new NotFoundSpotifishException("ArtistService - getObjectBySlug("+slug+")", "Artist", slug));
        securityService.assertCanSee(artist);
        return artist;
    }

    public Boolean delete(String id) {
        Artist artist = getObjectById(id);
        securityService.assertCanDelete(artist);
        artistRepository.delete(artist);
        return true;
    }

    public Artist persist(ArtistDTO artistDTO) {
        return persist(artistDTO, null);
    }

    public Artist persist(ArtistDTO artistDTO, String id) {
        Artist artist = new Artist();
        if (id != null) {
            artist = getObjectById(id);
            securityService.assertCanEdit(artist);
        }
        artist = getObjectFromDTO(artistDTO, artist);
        return artistRepository.saveAndFlush(artist);
    }

    public ArtistDTO getDTOById(String id) {
        Artist artist = getObjectById(id);
        return getDTOFromObject(artist);
    }

    public ArtistDTO getDTOFromObject(Artist artist) {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setName(artist.getName());
        artistDTO.setEmail(artist.getEmail());
        artistDTO.setPassword(artist.getPassword());
        artistDTO.setFirstName(artist.getFirstName());
        artistDTO.setLastName(artist.getLastName());
        artistDTO.setBirthAt(artist.getBirthAt());
        return artistDTO;
    }
    public Artist getObjectFromDTO(ArtistDTO artistDTO) {
        return getObjectFromDTO(artistDTO, new Artist());
    }
    public Artist getObjectFromDTO(ArtistDTO artistDTO, Artist artist) {
        artist.setName(artistDTO.getName());
        artist.setEmail(artistDTO.getEmail());
        artist.setPassword(passwordEncoder.encode(artistDTO.getPassword()));
        artist.setFirstName(artistDTO.getFirstName());
        artist.setLastName(artistDTO.getLastName());
        artist.setBirthAt(artistDTO.getBirthAt());
        artist.setSlug("test");
        return artist;
    }


    public List<Artist> search(String search) {
        return artistRepository.findBySearch(search);
    }
}