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

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(UrlRoute.URL_API)
public class UserLikeableItemControllerApi {
    
    private UserLikeableItemService userLikeableItemService;

    @PostMapping(path = UrlRoute.URL_USERLIKEABLEITEM_NEW + "/{slug}")
    @JsonView(JsonViews.UserLikeableItemShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Boolean> create(@PathVariable String slug) {
        System.out.println("UserLikeableItemControllerApi.create");
        return CustomResponse.created(userLikeableItemService.like(slug));
    }

    @DeleteMapping(path = UrlRoute.URL_USERLIKEABLEITEM_DELETE + "/{slug}")
    public CustomResponse<Boolean> delete(@PathVariable String slug) {
        System.out.println("UserLikeableItemControllerApi.delete");
        return CustomResponse.success(userLikeableItemService.delete(slug));
    }
}