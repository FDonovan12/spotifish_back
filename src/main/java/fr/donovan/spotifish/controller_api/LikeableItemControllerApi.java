package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.LikeableItem;
import fr.donovan.spotifish.custom_response.*;
import fr.donovan.spotifish.dto.LikeableItemDTO;
import fr.donovan.spotifish.service.LikeableItemService;
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
public class LikeableItemControllerApi {
    
    private LikeableItemService likeableItemService;

    @GetMapping(path = UrlRoute.URL_LIKEABLEITEM)
    @JsonView(JsonViews.LikeableItemListJsonViews.class)
    public CustomResponse<List<LikeableItem>> list() {
        return CustomListResponse.success(likeableItemService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_LIKEABLEITEM + "/{slug}")
    @JsonView(JsonViews.LikeableItemShowJsonViews.class)
    public CustomResponse<LikeableItem> show(@PathVariable String slug) {
        return CustomResponse.success(likeableItemService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_LIKEABLEITEM_NEW)
    @JsonView(JsonViews.LikeableItemShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<LikeableItem> create(@Valid @RequestBody LikeableItemDTO likeableItemDTO) {
        return CustomResponse.created(likeableItemService.persist(likeableItemDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_LIKEABLEITEM_EDIT + "/{id}")
    @JsonView(JsonViews.LikeableItemShowJsonViews.class)
    public CustomResponse<LikeableItem> update(@Valid @RequestBody LikeableItemDTO likeableItemDTO, @PathVariable String id) {
        return CustomResponse.success(likeableItemService.persist(likeableItemDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_LIKEABLEITEM_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return CustomResponse.success(likeableItemService.delete(id));
    }
}