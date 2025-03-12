package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.Song;
import fr.donovan.spotifish.repository.SongRepository;
import fr.donovan.spotifish.dto.SongDTO;
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
public class SongService  {

    private final SongRepository songRepository;
    private final SecurityService securityService;

    public List<Song> findAll() {
        return this.songRepository.findAll();
    }

    public Song getObjectById(String id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        Song song = optionalSong.orElseThrow(() -> new NotFoundSpotifishException("SongService - getObjectById("+id+")", "Song", id));
        securityService.assertCanSee(song);
        return song;
    }

    public Boolean delete(String id) {
        Song song = getObjectById(id);
        securityService.assertCanDelete(song);
        songRepository.delete(song);
        return true;
    }

    public Song persist(SongDTO songDTO) {
        return persist(songDTO, null);
    }

    public Song persist(SongDTO songDTO, String id) {
        Song song = new Song();
        if (id != null) {
            song = getObjectById(id);
            securityService.assertCanEdit(song);
        }
        song = getObjectFromDTO(songDTO, song);
        return songRepository.saveAndFlush(song);
    }

    public SongDTO getDTOById(String id) {
        Song song = getObjectById(id);
        return getDTOFromObject(song);
    }

    public SongDTO getDTOFromObject(Song song) {
        SongDTO songDTO = new SongDTO();
        songDTO.setName(song.getName());
        songDTO.setPath(song.getPath());
        songDTO.setDuration(song.getDuration());
        songDTO.setImage(song.getImage());
        songDTO.setNumberOfListen(song.getNumberOfListen());
        return songDTO;
    }
    public Song getObjectFromDTO(SongDTO songDTO) {
        return getObjectFromDTO(songDTO, new Song());
    }
    public Song getObjectFromDTO(SongDTO songDTO, Song song) {
        song.setName(songDTO.getName());
        song.setPath(songDTO.getPath());
        song.setDuration(songDTO.getDuration());
        song.setCreatedAt(songDTO.getCreatedAt());
        song.setImage(songDTO.getImage());
        song.setNumberOfListen(songDTO.getNumberOfListen());
        song.setSlug("test");
        return song;
    }

    public List<Song> searchSongByEverything() {
        return this.songRepository.searchSongByEverything(); 
    }


}