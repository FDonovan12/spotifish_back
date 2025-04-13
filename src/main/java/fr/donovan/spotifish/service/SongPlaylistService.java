package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.Playlist;
import fr.donovan.spotifish.entity.Song;
import fr.donovan.spotifish.entity.SongPlaylist;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.entity.embed.ContributorId;
import fr.donovan.spotifish.repository.SongPlaylistRepository;
import fr.donovan.spotifish.dto.SongPlaylistDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import fr.donovan.spotifish.security.SecurityService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class SongPlaylistService  {

    private final SongPlaylistRepository songPlaylistRepository;
    private final SongService songService;
    private final PlaylistService playlistService;
    private final ContributorService contributorService;
    private final SecurityService securityService;
    public List<SongPlaylist> findAll() {
        return this.songPlaylistRepository.findAll();
    }

    public SongPlaylist getObjectById(String id) {
        Optional<SongPlaylist> optionalSongPlaylist = songPlaylistRepository.findById(id);
        SongPlaylist songPlaylist = optionalSongPlaylist.orElseThrow(() -> new NotFoundSpotifishException("SongPlaylistService - getObjectById("+id+")", "SongPlaylist", id));
        securityService.assertCanSee(songPlaylist);
        return songPlaylist;
    }
    public SongPlaylist getObjectBySlug(String slug) {
        Optional<SongPlaylist> optionalSongPlaylist = songPlaylistRepository.findBySlug(slug);
        SongPlaylist songPlaylist = optionalSongPlaylist.orElseThrow(() -> new NotFoundSpotifishException("SongPlaylistService - getObjectBySlug("+slug+")", "SongPlaylist", slug));
        securityService.assertCanSee(songPlaylist);
        return songPlaylist;
    }

    public Boolean delete(String id) {
        System.out.println("song playlist id = " + id);
        SongPlaylist songPlaylist = getObjectById(id);
        securityService.assertCanDelete(songPlaylist);
        songPlaylistRepository.delete(songPlaylist);
        return true;
    }

    public SongPlaylist persist(SongPlaylistDTO songPlaylistDTO) {
        return persist(songPlaylistDTO, null);
    }

    public SongPlaylist persist(SongPlaylistDTO songPlaylistDTO, String id) {
        SongPlaylist songPlaylist = new SongPlaylist();
        if (id != null) {
            songPlaylist = getObjectById(id);
            securityService.assertCanEdit(songPlaylist);
        }
        songPlaylist = getObjectFromDTO(songPlaylistDTO, songPlaylist);
        return songPlaylistRepository.saveAndFlush(songPlaylist);
    }

    public SongPlaylistDTO getDTOById(String id) {
        SongPlaylist songPlaylist = getObjectById(id);
        return getDTOFromObject(songPlaylist);
    }

    public SongPlaylistDTO getDTOFromObject(SongPlaylist songPlaylist) {
        SongPlaylistDTO songPlaylistDTO = new SongPlaylistDTO();
        songPlaylistDTO.setSongSlug(songPlaylist.getSong().getUuid());
        songPlaylistDTO.setPlaylistSlug(songPlaylist.getPlaylist().getUuid());
        return songPlaylistDTO;
    }
    public SongPlaylist getObjectFromDTO(SongPlaylistDTO songPlaylistDTO) {
        return getObjectFromDTO(songPlaylistDTO, new SongPlaylist());
    }
    public SongPlaylist getObjectFromDTO(SongPlaylistDTO songPlaylistDTO, SongPlaylist songPlaylist) {
        Song song  = songService.getObjectBySlug(songPlaylistDTO.getSongSlug());
        User user  = securityService.getCurrentUser();
        songPlaylist.setSong(song);
        Playlist playlist = playlistService.getObjectBySlug(songPlaylistDTO.getPlaylistSlug());
        Hibernate.initialize(playlist.getSongPlaylists());
        List<SongPlaylist> songPlaylists = playlist.getSongPlaylists();
        songPlaylist.setPosition(songPlaylists.stream().mapToInt(SongPlaylist::getPosition).max().orElse(0)+1);
        songPlaylist.setPlaylist(playlist);
        ContributorId contributorId = new ContributorId(user.getUuid(), playlist.getUuid());
        songPlaylist.setContributor(contributorService.getObjectById(contributorId));
        songPlaylist.setSlug("test");
        return songPlaylist;
    }

    public void deleteFromPlaylist(Playlist playlist) {
        System.out.println("SongPlaylistService.deleteFromPlaylist " + playlist.getSongPlaylists());
        playlist.getSongPlaylists().stream()
                .map(SongPlaylist::getIdToSerializer)
                .forEach(id -> this.delete(id));
    }
}