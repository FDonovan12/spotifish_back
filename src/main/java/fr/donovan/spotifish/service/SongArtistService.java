package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.SongArtist;
import fr.donovan.spotifish.entity.embed.*;
import fr.donovan.spotifish.repository.SongArtistRepository;
import fr.donovan.spotifish.dto.SongArtistDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
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

    public List<SongArtist> findAll() {
        return this.songArtistRepository.findAll();
    }

    public SongArtist getObjectById(SongArtistId id) {
        Optional<SongArtist> optionalSongArtist = songArtistRepository.findById(id);
        return optionalSongArtist.orElseThrow(() -> new NotFoundSpotifishException("SongArtistService - getObjectById("+id+")", "SongArtist", id));
    }
    public SongArtist getObjectBySlug(String slug) {
        Optional<SongArtist> optionalSongArtist = songArtistRepository.findBySlug(slug);
        return optionalSongArtist.orElseThrow(() -> new NotFoundSpotifishException("SongArtistService - getObjectBySlug("+slug+")", "SongArtist", slug));
    }

    public Boolean delete(SongArtistId id) {
        songArtistRepository.deleteById(id);
        return true;
    }

    public SongArtist persist(SongArtistDTO songArtistDTO) {
        return persist(songArtistDTO, null);
    }

    public SongArtist persist(SongArtistDTO songArtistDTO, SongArtistId id) {
        SongArtist songArtist = new SongArtist();
        if (id != null) {
            songArtist = getObjectById(id);
        }
        songArtist = getObjectFromDTO(songArtistDTO, songArtist);
        return songArtistRepository.saveAndFlush(songArtist);
    }

    public SongArtistDTO getDTOById(SongArtistId id) {
        SongArtist songArtist = getObjectById(id);
        return getDTOFromObject(songArtist);
    }

    public SongArtistDTO getDTOFromObject(SongArtist songArtist) {
        SongArtistDTO songArtistDTO = new SongArtistDTO();
        songArtistDTO.setIsPrincipalArtist(songArtist.getIsPrincipalArtist());
        songArtistDTO.setSongId(songArtist.getSong().getUuid());
        songArtistDTO.setArtistId(songArtist.getArtist().getUuid());
        return songArtistDTO;
    }
    public SongArtist getObjectFromDTO(SongArtistDTO songArtistDTO) {
        return getObjectFromDTO(songArtistDTO, new SongArtist());
    }
    public SongArtist getObjectFromDTO(SongArtistDTO songArtistDTO, SongArtist songArtist) {
        songArtist.setIsPrincipalArtist(songArtistDTO.getIsPrincipalArtist());
        songArtist.setSong(songService.getObjectById(songArtistDTO.getSongId()));
        songArtist.setArtist(artistService.getObjectById(songArtistDTO.getArtistId()));
        songArtist.setId(new SongArtistId(songArtistDTO.getSongId(), songArtistDTO.getArtistId()));
        songArtist.setSlug("test");
        return songArtist;
    }


}