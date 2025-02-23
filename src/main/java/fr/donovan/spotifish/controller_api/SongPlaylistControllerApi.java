package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.SongPlaylist;
import fr.donovan.spotifish.custom_response.CustomResponse;
import fr.donovan.spotifish.dto.SongPlaylistDTO;
import fr.donovan.spotifish.service.SongPlaylistService;
import fr.donovan.spotifish.json_view.JsonViews;
import fr.donovan.spotifish.mapping.UrlRoute;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(UrlRoute.URL_API)
public class SongPlaylistControllerApi {
    
    private SongPlaylistService songPlaylistService;

    @GetMapping(path = UrlRoute.URL_SONGPLAYLIST)
    @JsonView(JsonViews.SongPlaylistListJsonViews.class)
    public CustomResponse<List<SongPlaylist>> list() {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongPlaylistControllerApi - list()", "SongPlaylist", this.songPlaylistService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_SONGPLAYLIST + "/{slug}")
    @JsonView(JsonViews.SongPlaylistShowJsonViews.class)
    public CustomResponse<SongPlaylist> show(@PathVariable String slug) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongPlaylistControllerApi - show("+slug+")", "SongPlaylist", this.songPlaylistService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_SONGPLAYLIST_NEW)
    @JsonView(JsonViews.SongPlaylistShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<SongPlaylist> create(@Valid @RequestBody SongPlaylistDTO songPlaylistDTO) {
        return new CustomResponse<>(HttpStatus.CREATED.value(), "SongPlaylistControllerApi - create()", "SongPlaylist", songPlaylistService.persist(songPlaylistDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_SONGPLAYLIST_EDIT + "/{id}")
    @JsonView(JsonViews.SongPlaylistShowJsonViews.class)
    public CustomResponse<SongPlaylist> update(@Valid @RequestBody SongPlaylistDTO songPlaylistDTO, @PathVariable Long id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongPlaylistControllerApi - update("+id+")", "SongPlaylist", songPlaylistService.persist(songPlaylistDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_SONGPLAYLIST_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable Long id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongPlaylistControllerApi - delete("+id+")", "SongPlaylist", songPlaylistService.delete(id));
    }
}