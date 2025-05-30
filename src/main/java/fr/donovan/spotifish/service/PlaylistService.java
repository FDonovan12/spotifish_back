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
        return getObjectBySlug(slug, true);
    }
    public Playlist getObjectBySlug(String slug, Boolean isAssert) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findBySlug(slug);
        Playlist playlist = optionalPlaylist.orElseThrow(() -> new NotFoundSpotifishException("PlaylistService - getObjectBySlug("+slug+")", "Playlist", slug));
        if (isAssert) {
            securityService.assertCanSee(playlist);
        }
        return playlist;
    }

    public Boolean delete(String id) {
        Playlist playlist = getObjectById(id);
        return delete(playlist);
    }

    public Boolean delete(Playlist playlist) {
        securityService.assertCanDelete(playlist);
        playlistRepository.delete(playlist);
        return true;
    }

    public Playlist persist(PlaylistDTO playlistDTO) {
        return persist(playlistDTO, null);
    }

    public Playlist persist(PlaylistDTO playlistDTO, String slug) {
        Playlist playlist = new Playlist();
        if (slug != null) {
            playlist = getObjectBySlug(slug);
            securityService.assertCanEdit(playlist);
        }
        playlist = getObjectFromDTO(playlistDTO, playlist);
        return playlistRepository.saveAndFlush(playlist);
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