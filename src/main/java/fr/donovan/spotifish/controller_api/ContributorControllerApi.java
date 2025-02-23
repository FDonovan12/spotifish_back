package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.Contributor;
import fr.donovan.spotifish.entity.embed.*;
import fr.donovan.spotifish.custom_response.CustomResponse;
import fr.donovan.spotifish.dto.ContributorDTO;
import fr.donovan.spotifish.service.ContributorService;
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
public class ContributorControllerApi {
    
    private ContributorService contributorService;

    @GetMapping(path = UrlRoute.URL_CONTRIBUTOR)
    @JsonView(JsonViews.ContributorListJsonViews.class)
    public CustomResponse<List<Contributor>> list() {
        return new CustomResponse<>(HttpStatus.OK.value(), "ContributorControllerApi - list()", "Contributor", this.contributorService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_CONTRIBUTOR + "/{slug}")
    @JsonView(JsonViews.ContributorShowJsonViews.class)
    public CustomResponse<Contributor> show(@PathVariable String slug) {
        return new CustomResponse<>(HttpStatus.OK.value(), "ContributorControllerApi - show("+slug+")", "Contributor", this.contributorService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_CONTRIBUTOR_NEW)
    @JsonView(JsonViews.ContributorShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Contributor> create(@Valid @RequestBody ContributorDTO contributorDTO) {
        return new CustomResponse<>(HttpStatus.CREATED.value(), "ContributorControllerApi - create()", "Contributor", contributorService.persist(contributorDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_CONTRIBUTOR_EDIT + "/{id}")
    @JsonView(JsonViews.ContributorShowJsonViews.class)
    public CustomResponse<Contributor> update(@Valid @RequestBody ContributorDTO contributorDTO, @PathVariable ContributorId id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "ContributorControllerApi - update("+id+")", "Contributor", contributorService.persist(contributorDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_CONTRIBUTOR_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable ContributorId id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "ContributorControllerApi - delete("+id+")", "Contributor", contributorService.delete(id));
    }
}