package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.MusicalGenre;
import fr.donovan.spotifish.custom_response.*;
import fr.donovan.spotifish.dto.MusicalGenreDTO;
import fr.donovan.spotifish.service.MusicalGenreService;
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
public class MusicalGenreControllerApi {
    
    private MusicalGenreService musicalGenreService;

    @GetMapping(path = UrlRoute.URL_MUSICALGENRE)
    @JsonView(JsonViews.MusicalGenreListJsonViews.class)
    public CustomResponse<List<MusicalGenre>> list() {
        return CustomListResponse.success(musicalGenreService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_MUSICALGENRE + "/{slug}")
    @JsonView(JsonViews.MusicalGenreShowJsonViews.class)
    public CustomResponse<MusicalGenre> show(@PathVariable String slug) {
        return CustomResponse.success(musicalGenreService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_MUSICALGENRE_NEW)
    @JsonView(JsonViews.MusicalGenreShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<MusicalGenre> create(@Valid @RequestBody MusicalGenreDTO musicalGenreDTO) {
        return CustomResponse.created(musicalGenreService.persist(musicalGenreDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_MUSICALGENRE_EDIT + "/{id}")
    @JsonView(JsonViews.MusicalGenreShowJsonViews.class)
    public CustomResponse<MusicalGenre> update(@Valid @RequestBody MusicalGenreDTO musicalGenreDTO, @PathVariable String id) {
        return CustomResponse.success(musicalGenreService.persist(musicalGenreDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_MUSICALGENRE_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return CustomResponse.success(musicalGenreService.delete(id));
    }
}