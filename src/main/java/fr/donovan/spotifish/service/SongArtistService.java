package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.Artist;
import fr.donovan.spotifish.entity.Song;
import fr.donovan.spotifish.entity.SongArtist;
import fr.donovan.spotifish.entity.embed.*;
import fr.donovan.spotifish.repository.SongArtistRepository;
import fr.donovan.spotifish.dto.SongArtistDTO;
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
public class SongArtistService  {

    private final SongArtistRepository songArtistRepository;
    private final SongService songService;
    private final ArtistService artistService;
    private final SecurityService securityService;
    public List<SongArtist> findAll() {
        return this.songArtistRepository.findAll();
    }

    public SongArtist getObjectById(SongArtistId id) {
        Optional<SongArtist> optionalSongArtist = songArtistRepository.findById(id);
        SongArtist songArtist = optionalSongArtist.orElseThrow(() -> new NotFoundSpotifishException("SongArtistService - getObjectById("+id+")", "SongArtist", id));
        securityService.assertCanSee(songArtist);
        return songArtist;
    }
    public SongArtist getObjectBySlug(String slug) {
        Optional<SongArtist> optionalSongArtist = songArtistRepository.findBySlug(slug);
        SongArtist songArtist = optionalSongArtist.orElseThrow(() -> new NotFoundSpotifishException("SongArtistService - getObjectBySlug("+slug+")", "SongArtist", slug));
        securityService.assertCanSee(songArtist);
        return songArtist;
    }

    public Boolean delete(SongArtistId id) {
        SongArtist songArtist = getObjectById(id);
        securityService.assertCanDelete(songArtist);
        songArtistRepository.delete(songArtist);
        return true;
    }

    public SongArtist createPrincipalArtistOfSong(Song song) {
        Artist artist = securityService.getCurrentArtist();

        SongArtistDTO songArtistDTO = new SongArtistDTO();
        songArtistDTO.setArtistSlug(artist.getSlug());
        songArtistDTO.setSongSlug(song.getSlug());
        songArtistDTO.setIsPrincipalArtist(true);

        return this.persist(songArtistDTO);
    }

    public SongArtist persist(SongArtistDTO songArtistDTO) {
        return persist(songArtistDTO, null);
    }

    public SongArtist persist(SongArtistDTO songArtistDTO, SongArtistId id) {
        SongArtist songArtist = new SongArtist();
        if (id != null) {
            songArtist = getObjectById(id);
            securityService.assertCanEdit(songArtist);
        }
        songArtist = getObjectFromDTO(songArtistDTO, songArtist);

        SongArtistId songArtistId = new SongArtistId(songArtist.getSong().getUuid(), songArtist.getArtist().getUuid());
        songArtist.setId(songArtistId);
        return songArtistRepository.saveAndFlush(songArtist);
    }

    public SongArtistDTO getDTOById(SongArtistId id) {
        SongArtist songArtist = getObjectById(id);
        return getDTOFromObject(songArtist);
    }

    public SongArtistDTO getDTOFromObject(SongArtist songArtist) {
        SongArtistDTO songArtistDTO = new SongArtistDTO();
        songArtistDTO.setIsPrincipalArtist(songArtist.getIsPrincipalArtist());
        songArtistDTO.setSongSlug(songArtist.getSong().getUuid());
        songArtistDTO.setArtistSlug(songArtist.getArtist().getUuid());
        return songArtistDTO;
    }
    public SongArtist getObjectFromDTO(SongArtistDTO songArtistDTO) {
        return getObjectFromDTO(songArtistDTO, new SongArtist());
    }
    public SongArtist getObjectFromDTO(SongArtistDTO songArtistDTO, SongArtist songArtist) {
        songArtist.setIsPrincipalArtist(songArtistDTO.getIsPrincipalArtist());
        songArtist.setSong(songService.getObjectBySlug(songArtistDTO.getSongSlug()));
        songArtist.setArtist(artistService.getObjectBySlug(songArtistDTO.getArtistSlug()));
        songArtist.setSlug("test");
        return songArtist;
    }
}