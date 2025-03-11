package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.Artist;
import fr.donovan.spotifish.repository.ArtistRepository;
import fr.donovan.spotifish.dto.ArtistDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class ArtistService  {

    private final ArtistRepository artistRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public List<Artist> findAll() {
        return this.artistRepository.findAll();
    }

    public Artist getObjectById(String id) {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        return optionalArtist.orElseThrow(() -> new NotFoundSpotifishException("ArtistService - getObjectById("+id+")", "Artist", id));
    }

    public Boolean delete(String id) {
        artistRepository.deleteById(id);
        return true;
    }

    public Artist persist(ArtistDTO artistDTO) {
        return persist(artistDTO, null);
    }

    public Artist persist(ArtistDTO artistDTO, String id) {
        Artist artist = new Artist();
        if (id != null) {
            artist = getObjectById(id);
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
        artistDTO.setName(artist.getName());
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


}