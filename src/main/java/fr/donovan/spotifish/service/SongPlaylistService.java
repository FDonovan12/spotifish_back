package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.SongPlaylist;
import fr.donovan.spotifish.entity.embed.ContributorId;
import fr.donovan.spotifish.repository.SongPlaylistRepository;
import fr.donovan.spotifish.dto.SongPlaylistDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import lombok.AllArgsConstructor;
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

    public List<SongPlaylist> findAll() {
        return this.songPlaylistRepository.findAll();
    }

    public SongPlaylist getObjectById(Long id) {
        Optional<SongPlaylist> optionalSongPlaylist = songPlaylistRepository.findById(id);
        return optionalSongPlaylist.orElseThrow(() -> new NotFoundSpotifishException("SongPlaylistService - getObjectById("+id+")", "SongPlaylist", id));
    }
    public SongPlaylist getObjectBySlug(String slug) {
        Optional<SongPlaylist> optionalSongPlaylist = songPlaylistRepository.findBySlug(slug);
        return optionalSongPlaylist.orElseThrow(() -> new NotFoundSpotifishException("SongPlaylistService - getObjectBySlug("+slug+")", "SongPlaylist", slug));
    }

    public Boolean delete(Long id) {
        songPlaylistRepository.deleteById(id);
        return true;
    }

    public SongPlaylist persist(SongPlaylistDTO songPlaylistDTO) {
        return persist(songPlaylistDTO, null);
    }

    public SongPlaylist persist(SongPlaylistDTO songPlaylistDTO, Long id) {
        SongPlaylist songPlaylist = new SongPlaylist();
        if (id != null) {
            songPlaylist = getObjectById(id);
        }
        songPlaylist = getObjectFromDTO(songPlaylistDTO, songPlaylist);
        return songPlaylistRepository.saveAndFlush(songPlaylist);
    }

    public SongPlaylistDTO getDTOById(Long id) {
        SongPlaylist songPlaylist = getObjectById(id);
        return getDTOFromObject(songPlaylist);
    }

    public SongPlaylistDTO getDTOFromObject(SongPlaylist songPlaylist) {
        SongPlaylistDTO songPlaylistDTO = new SongPlaylistDTO();
        songPlaylistDTO.setPosition(songPlaylist.getPosition());
        songPlaylistDTO.setCreatedAt(songPlaylist.getCreatedAt());
        songPlaylistDTO.setSongId(songPlaylist.getSong().getUuid());
        songPlaylistDTO.setPlaylistId(songPlaylist.getPlaylist().getUuid());
        songPlaylistDTO.setUserId(songPlaylist.getContributor().getUser().getUuid());
        return songPlaylistDTO;
    }
    public SongPlaylist getObjectFromDTO(SongPlaylistDTO songPlaylistDTO) {
        return getObjectFromDTO(songPlaylistDTO, new SongPlaylist());
    }
    public SongPlaylist getObjectFromDTO(SongPlaylistDTO songPlaylistDTO, SongPlaylist songPlaylist) {
        songPlaylist.setPosition(songPlaylistDTO.getPosition());
        songPlaylist.setCreatedAt(songPlaylistDTO.getCreatedAt());
        songPlaylist.setSong(songService.getObjectById(songPlaylistDTO.getSongId()));
        songPlaylist.setPlaylist(playlistService.getObjectById(songPlaylistDTO.getPlaylistId()));
        songPlaylist.setContributor(contributorService.getObjectById(new ContributorId(songPlaylistDTO.getUserId(), songPlaylistDTO.getPlaylistId())));
        songPlaylist.setSlug("test");
        return songPlaylist;
    }


}