package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.custom_response.CustomListResponse;
import fr.donovan.spotifish.entity.Album;
import fr.donovan.spotifish.custom_response.CustomResponse;
import fr.donovan.spotifish.dto.AlbumDTO;
import fr.donovan.spotifish.json_view.JsonViewsLikeableItem;
import fr.donovan.spotifish.service.AlbumService;
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
public class AlbumControllerApi {
    
    private AlbumService albumService;

    @GetMapping(path = UrlRoute.URL_ALBUM)
    @JsonView(JsonViews.AlbumListJsonViews.class)
    public CustomResponse<List<Album>> list() {
        return CustomListResponse.success(albumService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_ALBUM + "/{id}")
    @JsonView(JsonViews.AlbumShowJsonViews.class)
    public CustomResponse<Album> show(@PathVariable String id) {
        return CustomResponse.success(albumService.getObjectById(id));
    }
    
    @PostMapping(path = UrlRoute.URL_ALBUM_NEW)
    @JsonView(JsonViews.AlbumShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Album> create(@Valid @RequestBody AlbumDTO albumDTO) {
        return CustomResponse.created(albumService.persist(albumDTO));
    }
    
//    @PutMapping(path = UrlRoute.URL_ALBUM_EDIT + "/{id}")
//    @JsonView(JsonViews.AlbumShowJsonViews.class)
//    public CustomResponse<Album> update(@Valid @RequestBody AlbumDTO albumDTO, @PathVariable String id) {
//        return new CustomResponse<>(HttpStatus.OK.value(), "AlbumControllerApi - update("+id+")", "Album", albumService.persist(albumDTO, id));
//    }
//
//    @DeleteMapping(path = UrlRoute.URL_ALBUM_DELETE + "/{id}")
//    public CustomResponse<Boolean> delete(@PathVariable String id) {
//        return new CustomResponse<>(HttpStatus.OK.value(), "AlbumControllerApi - delete("+id+")", "Album", albumService.delete(id));
//    }
}