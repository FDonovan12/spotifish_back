package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.UserLikeableItem;
import fr.donovan.spotifish.entity.embed.*;
import fr.donovan.spotifish.custom_response.*;
import fr.donovan.spotifish.dto.UserLikeableItemDTO;
import fr.donovan.spotifish.service.UserLikeableItemService;
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
public class UserLikeableItemControllerApi {
    
    private UserLikeableItemService userLikeableItemService;

    @GetMapping(path = UrlRoute.URL_USERLIKEABLEITEM)
    @JsonView(JsonViews.UserLikeableItemListJsonViews.class)
    public CustomResponse<List<UserLikeableItem>> list() {
        return CustomListResponse.success(userLikeableItemService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_USERLIKEABLEITEM + "/{slug}")
    @JsonView(JsonViews.UserLikeableItemShowJsonViews.class)
    public CustomResponse<UserLikeableItem> show(@PathVariable String slug) {
        return CustomResponse.success(userLikeableItemService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_USERLIKEABLEITEM_NEW)
    @JsonView(JsonViews.UserLikeableItemShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<UserLikeableItem> create(@Valid @RequestBody UserLikeableItemDTO userLikeableItemDTO) {
        return CustomResponse.created(userLikeableItemService.persist(userLikeableItemDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_USERLIKEABLEITEM_EDIT + "/{id}")
    @JsonView(JsonViews.UserLikeableItemShowJsonViews.class)
    public CustomResponse<UserLikeableItem> update(@Valid @RequestBody UserLikeableItemDTO userLikeableItemDTO, @PathVariable UserLikeableItemId id) {
        return CustomResponse.success(userLikeableItemService.persist(userLikeableItemDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_USERLIKEABLEITEM_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable UserLikeableItemId id) {
        return CustomResponse.success(userLikeableItemService.delete(id));
    }
}