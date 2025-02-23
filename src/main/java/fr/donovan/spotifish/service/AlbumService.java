package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.Album;
import fr.donovan.spotifish.repository.AlbumRepository;
import fr.donovan.spotifish.dto.AlbumDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class AlbumService  {

    private final AlbumRepository albumRepository;
    private final ArtistService artistService;

    public List<Album> findAll() {
        return this.albumRepository.findAll();
    }

    public Album getObjectById(String id) {
        Optional<Album> optionalAlbum = albumRepository.findById(id);
        return optionalAlbum.orElseThrow(() -> new NotFoundSpotifishException("AlbumService - getObjectById("+id+")", "Album", id));
    }

    public Boolean delete(String id) {
        albumRepository.deleteById(id);
        return true;
    }

    public Album persist(AlbumDTO albumDTO) {
        return persist(albumDTO, null);
    }

    public Album persist(AlbumDTO albumDTO, String id) {
        Album album = new Album();
        if (id != null) {
            album = getObjectById(id);
        }
        album = getObjectFromDTO(albumDTO, album);
        return albumRepository.saveAndFlush(album);
    }

    public AlbumDTO getDTOById(String id) {
        Album album = getObjectById(id);
        return getDTOFromObject(album);
    }

    public AlbumDTO getDTOFromObject(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setName(album.getName());
        albumDTO.setDescription(album.getDescription());
        albumDTO.setImage(album.getImage());
        albumDTO.setCreatedAt(album.getCreatedAt());
        albumDTO.setArtistId(album.getArtist().getUuid());
        return albumDTO;
    }
    public Album getObjectFromDTO(AlbumDTO albumDTO) {
        return getObjectFromDTO(albumDTO, new Album());
    }
    public Album getObjectFromDTO(AlbumDTO albumDTO, Album album) {
        album.setName(albumDTO.getName());
        album.setDescription(albumDTO.getDescription());
        album.setImage(albumDTO.getImage());
        album.setCreatedAt(albumDTO.getCreatedAt());
        album.setArtist(artistService.getObjectById(albumDTO.getArtistId()));
        album.setSlug("test");
        return album;
    }


}