package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.SongAlbum;
import fr.donovan.spotifish.entity.embed.*;
import fr.donovan.spotifish.repository.SongAlbumRepository;
import fr.donovan.spotifish.dto.SongAlbumDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
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

    public List<SongAlbum> findAll() {
        return this.songAlbumRepository.findAll();
    }

    public SongAlbum getObjectById(SongAlbumId id) {
        Optional<SongAlbum> optionalSongAlbum = songAlbumRepository.findById(id);
        return optionalSongAlbum.orElseThrow(() -> new NotFoundSpotifishException("SongAlbumService - getObjectById("+id+")", "SongAlbum", id));
    }
    public SongAlbum getObjectBySlug(String slug) {
        Optional<SongAlbum> optionalSongAlbum = songAlbumRepository.findBySlug(slug);
        return optionalSongAlbum.orElseThrow(() -> new NotFoundSpotifishException("SongAlbumService - getObjectBySlug("+slug+")", "SongAlbum", slug));
    }

    public Boolean delete(SongAlbumId id) {
        songAlbumRepository.deleteById(id);
        return true;
    }

    public SongAlbum persist(SongAlbumDTO songAlbumDTO) {
        return persist(songAlbumDTO, null);
    }

    public SongAlbum persist(SongAlbumDTO songAlbumDTO, SongAlbumId id) {
        SongAlbum songAlbum = new SongAlbum();
        if (id != null) {
            songAlbum = getObjectById(id);
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
        songAlbumDTO.setCreatedAt(songAlbum.getCreatedAt());
        songAlbumDTO.setSongId(songAlbum.getSong().getUuid());
        songAlbumDTO.setAlbumId(songAlbum.getAlbum().getUuid());
        return songAlbumDTO;
    }
    public SongAlbum getObjectFromDTO(SongAlbumDTO songAlbumDTO) {
        return getObjectFromDTO(songAlbumDTO, new SongAlbum());
    }
    public SongAlbum getObjectFromDTO(SongAlbumDTO songAlbumDTO, SongAlbum songAlbum) {
        songAlbum.setPosition(songAlbumDTO.getPosition());
        songAlbum.setCreatedAt(songAlbumDTO.getCreatedAt());
        songAlbum.setSong(songService.getObjectById(songAlbumDTO.getSongId()));
        songAlbum.setAlbum(albumService.getObjectById(songAlbumDTO.getAlbumId()));
        songAlbum.setId(new SongAlbumId(songAlbumDTO.getSongId(), songAlbumDTO.getAlbumId()));
        songAlbum.setSlug("test");
        return songAlbum;
    }


}