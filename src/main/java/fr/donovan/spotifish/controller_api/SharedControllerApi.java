package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.Playlist;
import fr.donovan.spotifish.entity.Shared;
import fr.donovan.spotifish.custom_response.*;
import fr.donovan.spotifish.dto.SharedDTO;
import fr.donovan.spotifish.service.SharedService;
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
public class SharedControllerApi {
    
    private SharedService sharedService;

    @GetMapping(path = UrlRoute.URL_SHARED + "/{slug}")
    @JsonView(JsonViews.PlaylistShowJsonViews.class)
    public CustomResponse<Playlist> addContributor(@PathVariable String slug) {
        return CustomResponse.success(sharedService.addContributor(slug));
    }

    @PostMapping(path = UrlRoute.URL_SHARED_NEW)
    @JsonView(JsonViews.SharedShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Shared> create(@Valid @RequestBody SharedDTO sharedDTO) {
        return CustomResponse.created(sharedService.persist(sharedDTO));
    }

    @DeleteMapping(path = UrlRoute.URL_SHARED_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return CustomResponse.success(sharedService.delete(id));
    }
}