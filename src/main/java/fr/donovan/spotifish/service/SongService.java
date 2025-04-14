package fr.donovan.spotifish.service;

import fr.donovan.spotifish.dto.ArtistDTO;
import fr.donovan.spotifish.dto.SongArtistDTO;
import fr.donovan.spotifish.entity.Artist;
import fr.donovan.spotifish.entity.Song;
import fr.donovan.spotifish.entity.SongArtist;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.exception.AccessDeniedSpotifishException;
import fr.donovan.spotifish.repository.SongRepository;
import fr.donovan.spotifish.dto.SongDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import fr.donovan.spotifish.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class SongService  {

    private final SongRepository songRepository;
    private final SecurityService securityService;
    private final FileManagerService fileManagerService;

    public List<Song> findAll() {
        return this.songRepository.findAll();
    }

    public List<Song> byUser() {
        User user = securityService.getCurrentUser();
        if (user.isModerator()) return this.findAll();
        if (user.isArtist()) return this.songRepository.findByUser((Artist) user);
        throw new AccessDeniedSpotifishException("song", "list");
    }

    public Song getObjectById(String id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        Song song = optionalSong.orElseThrow(
                () -> new NotFoundSpotifishException("SongService - getObjectById("+id+")", "Song", id));
        securityService.assertCanSee(song);
        return song;
    }
    public Song getObjectBySlug(String slug) {
        Optional<Song> optionalSong = songRepository.findBySlug(slug);
        Song song = optionalSong.orElseThrow(
                () -> new NotFoundSpotifishException("SongService - getObjectBySlug("+slug+")", "Song", slug));
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

    public Song persist(SongDTO songDTO, String slug) {
        Song song = new Song();
        song.setSlug("test");
        String oldPath = "";
        securityService.assertCanCreate(song);
        if (slug != null) {
            song = getObjectBySlug(slug);
            securityService.assertCanEdit(song);
            oldPath = song.getPath();
        }
        song = getObjectFromDTO(songDTO, song);
        song = songRepository.save(song);
        if (slug != null) {
            String newPath = song.getPath();
            this.fileManagerService.rename(oldPath, newPath);
        }
        return song;
    }

    public SongDTO getDTOById(String id) {
        Song song = getObjectById(id);
        return getDTOFromObject(song);
    }

    public SongDTO getDTOFromObject(Song song) {
        SongDTO songDTO = new SongDTO();
        songDTO.setName(song.getName());
        return songDTO;
    }
    public Song getObjectFromDTO(SongDTO songDTO) {
        return getObjectFromDTO(songDTO, new Song());
    }
    public Song getObjectFromDTO(SongDTO songDTO, Song song) {
        song.setName(songDTO.getName());
        song.setCreatedAt(songDTO.getCreatedAt());
        return song;
    }

    public List<Song> searchSongByEverything() {
        return this.songRepository.searchSongByEverything(); 
    }


    public List<Song> search(String search) {
        return songRepository.findBySearch(search);
    }

    @Transactional
    @Scheduled(cron = "0 */1 * * * *")
    public void updateNumberOfListen() {
        this.songRepository.updateNumberOfListen();
    }
}