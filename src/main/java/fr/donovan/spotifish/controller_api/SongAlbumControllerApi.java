package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.SongAlbum;
import fr.donovan.spotifish.entity.embed.*;
import fr.donovan.spotifish.custom_response.*;
import fr.donovan.spotifish.dto.SongAlbumDTO;
import fr.donovan.spotifish.service.SongAlbumService;
import fr.donovan.spotifish.json_view.JsonViews;
import fr.donovan.spotifish.mapping.UrlRoute;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(UrlRoute.URL_API)
public class SongAlbumControllerApi {
    
    private SongAlbumService songAlbumService;

    @GetMapping(path = UrlRoute.URL_SONGALBUM)
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    @JsonView(JsonViews.SongAlbumListJsonViews.class)
    public CustomResponse<List<SongAlbum>> list() {
        return CustomListResponse.success(songAlbumService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_SONGALBUM + "/{slug}")
    @JsonView(JsonViews.SongAlbumShowJsonViews.class)
    public CustomResponse<SongAlbum> show(@PathVariable String slug) {
        return CustomResponse.success(songAlbumService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_SONGALBUM_NEW)
    @JsonView(JsonViews.SongAlbumShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<SongAlbum> create(@Valid @RequestBody SongAlbumDTO songAlbumDTO) {
        return CustomResponse.created(songAlbumService.persist(songAlbumDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_SONGALBUM_EDIT + "/{id}")
    @JsonView(JsonViews.SongAlbumShowJsonViews.class)
    public CustomResponse<SongAlbum> update(@Valid @RequestBody SongAlbumDTO songAlbumDTO, @PathVariable SongAlbumId id) {
        return CustomResponse.success(songAlbumService.persist(songAlbumDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_SONGALBUM_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable SongAlbumId id) {
        return CustomResponse.success(songAlbumService.delete(id));
    }
}