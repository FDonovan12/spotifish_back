package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.Historical;
import fr.donovan.spotifish.custom_response.*;
import fr.donovan.spotifish.dto.HistoricalDTO;
import fr.donovan.spotifish.service.HistoricalService;
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
public class HistoricalControllerApi {
    
    private HistoricalService historicalService;

    @GetMapping(path = UrlRoute.URL_HISTORICAL)
    @JsonView(JsonViews.HistoricalListJsonViews.class)
    public CustomResponse<List<Historical>> list() {
        return CustomListResponse.success(historicalService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_HISTORICAL + "/{slug}")
    @JsonView(JsonViews.HistoricalShowJsonViews.class)
    public CustomResponse<Historical> show(@PathVariable String slug) {
        return CustomResponse.success(historicalService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_HISTORICAL_NEW)
    @JsonView(JsonViews.HistoricalShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Historical> create(@Valid @RequestBody HistoricalDTO historicalDTO) {
        return CustomResponse.created(historicalService.persist(historicalDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_HISTORICAL_EDIT + "/{id}")
    @JsonView(JsonViews.HistoricalShowJsonViews.class)
    public CustomResponse<Historical> update(@Valid @RequestBody HistoricalDTO historicalDTO, @PathVariable String id) {
        return CustomResponse.success(historicalService.persist(historicalDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_HISTORICAL_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return CustomResponse.success(historicalService.delete(id));
    }
}