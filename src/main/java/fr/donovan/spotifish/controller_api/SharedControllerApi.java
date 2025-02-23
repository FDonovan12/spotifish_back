package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.Shared;
import fr.donovan.spotifish.custom_response.CustomResponse;
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

    @GetMapping(path = UrlRoute.URL_SHARED)
    @JsonView(JsonViews.SharedListJsonViews.class)
    public CustomResponse<List<Shared>> list() {
        return new CustomResponse<>(HttpStatus.OK.value(), "SharedControllerApi - list()", "Shared", this.sharedService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_SHARED + "/{slug}")
    @JsonView(JsonViews.SharedShowJsonViews.class)
    public CustomResponse<Shared> show(@PathVariable String slug) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SharedControllerApi - show("+slug+")", "Shared", this.sharedService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_SHARED_NEW)
    @JsonView(JsonViews.SharedShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Shared> create(@Valid @RequestBody SharedDTO sharedDTO) {
        return new CustomResponse<>(HttpStatus.CREATED.value(), "SharedControllerApi - create()", "Shared", sharedService.persist(sharedDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_SHARED_EDIT + "/{id}")
    @JsonView(JsonViews.SharedShowJsonViews.class)
    public CustomResponse<Shared> update(@Valid @RequestBody SharedDTO sharedDTO, @PathVariable String id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SharedControllerApi - update("+id+")", "Shared", sharedService.persist(sharedDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_SHARED_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SharedControllerApi - delete("+id+")", "Shared", sharedService.delete(id));
    }
}