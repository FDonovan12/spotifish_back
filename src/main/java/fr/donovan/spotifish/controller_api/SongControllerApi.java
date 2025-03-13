package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.Song;
import fr.donovan.spotifish.custom_response.*;
import fr.donovan.spotifish.dto.SongDTO;
import fr.donovan.spotifish.service.SongService;
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
public class SongControllerApi {
    
    private SongService songService;

    @GetMapping(path = UrlRoute.URL_SONG)
    @JsonView(JsonViews.SongListJsonViews.class)
    public CustomResponse<List<Song>> list() {
        return CustomListResponse.success(songService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_SONG + "/{slug}")
    @JsonView(JsonViews.SongShowJsonViews.class)
    public CustomResponse<Song> show(@PathVariable String slug) {
        return CustomResponse.success(songService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_SONG_NEW)
    @JsonView(JsonViews.SongShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Song> create(@Valid @RequestBody SongDTO songDTO) {
        return CustomResponse.created(songService.persist(songDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_SONG_EDIT + "/{id}")
    @JsonView(JsonViews.SongShowJsonViews.class)
    public CustomResponse<Song> update(@Valid @RequestBody SongDTO songDTO, @PathVariable String id) {
        return CustomResponse.success(songService.persist(songDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_SONG_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return CustomResponse.success(songService.delete(id));
    }

    @GetMapping(path = UrlRoute.URL_SONG+"/searchSongByEverything")
    @JsonView(JsonViews.SongListJsonViews.class)
    public CustomResponse<List<Song>> searchSongByEverything() {
        return CustomListResponse.success(songService.query());
    }
}