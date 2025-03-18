package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.SongAlbum;
import fr.donovan.spotifish.entity.embed.*;
import fr.donovan.spotifish.repository.SongAlbumRepository;
import fr.donovan.spotifish.dto.SongAlbumDTO;
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
public class SongAlbumService  {

    private final SongAlbumRepository songAlbumRepository;
    private final SongService songService;
    private final AlbumService albumService;
    private final SecurityService securityService;
    public List<SongAlbum> findAll() {
        return this.songAlbumRepository.findAll();
    }

    public SongAlbum getObjectById(SongAlbumId id) {
        Optional<SongAlbum> optionalSongAlbum = songAlbumRepository.findById(id);
        SongAlbum songAlbum = optionalSongAlbum.orElseThrow(() -> new NotFoundSpotifishException("SongAlbumService - getObjectById("+id+")", "SongAlbum", id));
        securityService.assertCanSee(songAlbum);
        return songAlbum;
    }
    public SongAlbum getObjectBySlug(String slug) {
        Optional<SongAlbum> optionalSongAlbum = songAlbumRepository.findBySlug(slug);
        SongAlbum songAlbum = optionalSongAlbum.orElseThrow(() -> new NotFoundSpotifishException("SongAlbumService - getObjectBySlug("+slug+")", "SongAlbum", slug));
        securityService.assertCanSee(songAlbum);
        return songAlbum;
    }

    public Boolean delete(SongAlbumId id) {
        SongAlbum songAlbum = getObjectById(id);
        securityService.assertCanDelete(songAlbum);
        songAlbumRepository.delete(songAlbum);
        return true;
    }

    public SongAlbum persist(SongAlbumDTO songAlbumDTO) {
        return persist(songAlbumDTO, null);
    }

    public SongAlbum persist(SongAlbumDTO songAlbumDTO, SongAlbumId id) {
        SongAlbum songAlbum = new SongAlbum();
        if (id != null) {
            songAlbum = getObjectById(id);
            securityService.assertCanEdit(songAlbum);
        }
        songAlbum = getObjectFromDTO(songAlbumDTO, songAlbum);
        return songAlbumRepository.saveAndFlush(songAlbum);
    }

    public SongAlbumDTO getDTOById(SongAlbumId id) {
        SongAlbum songAlbum = getObjectById(id);
        return getDTOFromObject(songAlbum);
    }

    public SongAlbumDTO getDTOFromObject(SongAlbum songAlbum) {
        SongAlbumDTO songAlbumDTO = new SongAlbumDTO();
        songAlbumDTO.setPosition(songAlbum.getPosition());
        songAlbumDTO.setSongSlug(songAlbum.getSong().getUuid());
        songAlbumDTO.setAlbumSlug(songAlbum.getAlbum().getUuid());
        return songAlbumDTO;
    }
    public SongAlbum getObjectFromDTO(SongAlbumDTO songAlbumDTO) {
        return getObjectFromDTO(songAlbumDTO, new SongAlbum());
    }
    public SongAlbum getObjectFromDTO(SongAlbumDTO songAlbumDTO, SongAlbum songAlbum) {
        songAlbum.setPosition(songAlbumDTO.getPosition());
        songAlbum.setSong(songService.getObjectBySlug(songAlbumDTO.getSongSlug()));
        songAlbum.setAlbum(albumService.getObjectBySlug(songAlbumDTO.getAlbumSlug()));
        songAlbum.setSlug("test");
        return songAlbum;
    }
}