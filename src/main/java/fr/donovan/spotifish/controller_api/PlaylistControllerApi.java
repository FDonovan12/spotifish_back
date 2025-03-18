package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.Playlist;
import fr.donovan.spotifish.custom_response.*;
import fr.donovan.spotifish.dto.PlaylistDTO;
import fr.donovan.spotifish.service.PlaylistService;
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
public class PlaylistControllerApi {
    
    private PlaylistService playlistService;

    @GetMapping(path = UrlRoute.URL_PLAYLIST)
    @JsonView(JsonViews.PlaylistShowJsonViews.class)
    public CustomResponse<List<Playlist>> list() {
        return CustomListResponse.success(playlistService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_PLAYLIST + "/{slug}")
    @JsonView(JsonViews.PlaylistShowJsonViews.class)
    public CustomResponse<Playlist> show(@PathVariable String slug) {
        return CustomResponse.success(playlistService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_PLAYLIST_NEW)
    @JsonView(JsonViews.PlaylistShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Playlist> create(@Valid @RequestBody PlaylistDTO playlistDTO) {
        return CustomResponse.created(playlistService.persist(playlistDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_PLAYLIST_EDIT + "/{id}")
    @JsonView(JsonViews.PlaylistShowJsonViews.class)
    public CustomResponse<Playlist> update(@Valid @RequestBody PlaylistDTO playlistDTO, @PathVariable String id) {
        return CustomResponse.success(playlistService.persist(playlistDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_PLAYLIST_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return CustomResponse.success(playlistService.delete(id));
    }
}