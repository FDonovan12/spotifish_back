package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.Artist;
import fr.donovan.spotifish.custom_response.*;
import fr.donovan.spotifish.dto.ArtistDTO;
import fr.donovan.spotifish.service.ArtistService;
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
public class ArtistControllerApi {
    
    private ArtistService artistService;

    @GetMapping(path = UrlRoute.URL_ARTIST)
    @JsonView(JsonViews.ArtistListJsonViews.class)
    public CustomResponse<List<Artist>> list() {
        return CustomListResponse.success(artistService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_ARTIST + "/{slug}")
    @JsonView(JsonViews.ArtistShowJsonViews.class)
    public CustomResponse<Artist> show(@PathVariable String slug) {
        return CustomResponse.success(artistService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_ARTIST_NEW)
    @JsonView(JsonViews.ArtistShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Artist> create(@Valid @RequestBody ArtistDTO artistDTO) {
        return CustomResponse.created(artistService.persist(artistDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_ARTIST_EDIT + "/{id}")
    @JsonView(JsonViews.ArtistShowJsonViews.class)
    public CustomResponse<Artist> update(@Valid @RequestBody ArtistDTO artistDTO, @PathVariable String id) {
        return CustomResponse.success(artistService.persist(artistDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_ARTIST_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return CustomResponse.success(artistService.delete(id));
    }
}