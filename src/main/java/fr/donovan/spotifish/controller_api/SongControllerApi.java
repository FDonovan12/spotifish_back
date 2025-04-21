package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.dto.ContributorDTO;
import fr.donovan.spotifish.dto.SongArtistDTO;
import fr.donovan.spotifish.entity.Artist;
import fr.donovan.spotifish.entity.Playlist;
import fr.donovan.spotifish.entity.Song;
import fr.donovan.spotifish.custom_response.*;
import fr.donovan.spotifish.dto.SongDTO;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.security.SecurityService;
import fr.donovan.spotifish.service.ConnectedUserService;
import fr.donovan.spotifish.service.SongArtistService;
import fr.donovan.spotifish.service.SongService;
import fr.donovan.spotifish.json_view.JsonViews;
import fr.donovan.spotifish.mapping.UrlRoute;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(UrlRoute.URL_API)
public class SongControllerApi {
    
    private final SongService songService;
    private final SecurityService securityService;
    private final SongArtistService songArtistService;

    @GetMapping(path = UrlRoute.URL_SONG)
    @PreAuthorize("hasAuthority('ROLE_ARTIST')")
    @JsonView(JsonViews.SongListJsonViews.class)
    public CustomResponse<List<Song>> list() {
        return CustomListResponse.success(songService.byUser());
    }

    @GetMapping(path = UrlRoute.URL_SONG + "/{slug}")
    @JsonView(JsonViews.SongShowJsonViews.class)
    public CustomResponse<Song> show(@PathVariable String slug) {
        return CustomResponse.success(songService.getObjectBySlug(slug));
    }

    @PostMapping(path = UrlRoute.URL_SONG_NEW)
    @PreAuthorize("hasAuthority('ROLE_ARTIST')")
    @JsonView(JsonViews.SongShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Song> create(@Valid @RequestBody SongDTO songDTO) {
        Song song = songService.persist(songDTO);
        this.songArtistService.createPrincipalArtistOfSong(song);
        return CustomResponse.created(song);
    }
    
    @PutMapping(path = UrlRoute.URL_SONG_EDIT + "/{slug}")
    @JsonView(JsonViews.SongShowJsonViews.class)
    public CustomResponse<Song> update(@Valid @RequestBody SongDTO songDTO, @PathVariable String slug) {
        return CustomResponse.success(songService.persist(songDTO, slug));
    }
    
    @DeleteMapping(path = UrlRoute.URL_SONG_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return CustomResponse.success(songService.delete(id));
    }
}