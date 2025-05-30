package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.Moderator;
import fr.donovan.spotifish.custom_response.*;
import fr.donovan.spotifish.dto.ModeratorDTO;
import fr.donovan.spotifish.service.ModeratorService;
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
public class ModeratorControllerApi {
    
    private ModeratorService moderatorService;

    @GetMapping(path = UrlRoute.URL_MODERATOR)
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    @JsonView(JsonViews.ModeratorListJsonViews.class)
    public CustomResponse<List<Moderator>> list() {
        return CustomListResponse.success(moderatorService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_MODERATOR + "/{slug}")
    @JsonView(JsonViews.ModeratorShowJsonViews.class)
    public CustomResponse<Moderator> show(@PathVariable String slug) {
        return CustomResponse.success(moderatorService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_MODERATOR_NEW)
    @JsonView(JsonViews.ModeratorShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Moderator> create(@Valid @RequestBody ModeratorDTO moderatorDTO) {
        return CustomResponse.created(moderatorService.persist(moderatorDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_MODERATOR_EDIT + "/{id}")
    @JsonView(JsonViews.ModeratorShowJsonViews.class)
    public CustomResponse<Moderator> update(@Valid @RequestBody ModeratorDTO moderatorDTO, @PathVariable String id) {
        return CustomResponse.success(moderatorService.persist(moderatorDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_MODERATOR_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return CustomResponse.success(moderatorService.delete(id));
    }
}