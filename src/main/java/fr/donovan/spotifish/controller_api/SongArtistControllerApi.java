package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.SongArtist;
import fr.donovan.spotifish.entity.embed.*;
import fr.donovan.spotifish.custom_response.*;
import fr.donovan.spotifish.dto.SongArtistDTO;
import fr.donovan.spotifish.service.SongArtistService;
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
public class SongArtistControllerApi {
    
    private SongArtistService songArtistService;

    @GetMapping(path = UrlRoute.URL_SONGARTIST)
    @JsonView(JsonViews.SongArtistListJsonViews.class)
    public CustomResponse<List<SongArtist>> list() {
        return CustomListResponse.success(songArtistService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_SONGARTIST + "/{slug}")
    @JsonView(JsonViews.SongArtistShowJsonViews.class)
    public CustomResponse<SongArtist> show(@PathVariable String slug) {
        return CustomResponse.success(songArtistService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_SONGARTIST_NEW)
    @JsonView(JsonViews.SongArtistShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<SongArtist> create(@Valid @RequestBody SongArtistDTO songArtistDTO) {
        return CustomResponse.created(songArtistService.persist(songArtistDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_SONGARTIST_EDIT + "/{id}")
    @JsonView(JsonViews.SongArtistShowJsonViews.class)
    public CustomResponse<SongArtist> update(@Valid @RequestBody SongArtistDTO songArtistDTO, @PathVariable SongArtistId id) {
        return CustomResponse.success(songArtistService.persist(songArtistDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_SONGARTIST_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable SongArtistId id) {
        return CustomResponse.success(songArtistService.delete(id));
    }
}