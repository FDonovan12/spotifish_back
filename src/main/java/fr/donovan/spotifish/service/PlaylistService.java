package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.LikeableItem;
import fr.donovan.spotifish.entity.Playlist;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.repository.PlaylistRepository;
import fr.donovan.spotifish.dto.PlaylistDTO;
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
public class PlaylistService  {

    private final PlaylistRepository playlistRepository;
    private final SecurityService securityService;
    public List<Playlist> findAll() {
        return this.playlistRepository.findAll();
    }

    public Playlist getObjectById(String id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        Playlist playlist = optionalPlaylist.orElseThrow(() -> new NotFoundSpotifishException("PlaylistService - getObjectById("+id+")", "Playlist", id));
        securityService.assertCanSee(playlist);
        return playlist;
    }
    public Playlist getObjectBySlug(String slug) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findBySlug(slug);
        Playlist playlist = optionalPlaylist.orElseThrow(() -> new NotFoundSpotifishException("PlaylistService - getObjectBySlug("+slug+")", "Playlist", slug));
        securityService.assertCanSee(playlist);
        return playlist;
    }

    public Boolean delete(String id) {
        Playlist playlist = getObjectById(id);
        securityService.assertCanDelete(playlist);
        playlistRepository.delete(playlist);
        return true;
    }

    public Playlist persist(PlaylistDTO playlistDTO) {
        return persist(playlistDTO, null);
    }

    public Playlist persist(PlaylistDTO playlistDTO, String id) {
        Playlist playlist = new Playlist();
        if (id != null) {
            playlist = getObjectById(id);
            securityService.assertCanEdit(playlist);
        }
        playlist = getObjectFromDTO(playlistDTO, playlist);
        return playlistRepository.saveAndFlush(playlist);
    }

    public PlaylistDTO getDTOById(String id) {
        Playlist playlist = getObjectById(id);
        return getDTOFromObject(playlist);
    }

    public PlaylistDTO getDTOFromObject(Playlist playlist) {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName(playlist.getName());
        playlistDTO.setDescription(playlist.getDescription());
        playlistDTO.setIsPrivate(playlist.getIsPrivate());
        return playlistDTO;
    }
    public Playlist getObjectFromDTO(PlaylistDTO playlistDTO) {
        return getObjectFromDTO(playlistDTO, new Playlist());
    }
    public Playlist getObjectFromDTO(PlaylistDTO playlistDTO, Playlist playlist) {
        playlist.setName(playlistDTO.getName());
        playlist.setDescription(playlistDTO.getDescription());
        playlist.setIsPrivate(playlistDTO.getIsPrivate());
        playlist.setSlug("test");
        return playlist;
    }


    public List<Playlist> search(String search) {
        return playlistRepository.findBySearch(search);
    }

    public List<Playlist> getByUser() {
        return this.getByUser(securityService.getCurrentUser());
    }

    public List<Playlist> getByUser(User user) {
        return playlistRepository.getByUser(user);
    }
}