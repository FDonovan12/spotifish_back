package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.custom_response.*;
import fr.donovan.spotifish.dto.UserDTO;
import fr.donovan.spotifish.service.UserService;
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
public class UserControllerApi {
    
    private UserService userService;

    @GetMapping(path = UrlRoute.URL_USER)
    @JsonView(JsonViews.UserListJsonViews.class)
    public CustomResponse<List<User>> list() {
        return CustomListResponse.success(userService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_USER + "/{slug}")
    @JsonView(JsonViews.UserShowJsonViews.class)
    public CustomResponse<User> show(@PathVariable String slug) {
        return CustomResponse.success(userService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_USER_NEW)
    @JsonView(JsonViews.UserShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<User> create(@Valid @RequestBody UserDTO userDTO) {
        return CustomResponse.created(userService.persist(userDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_USER_EDIT + "/{id}")
    @JsonView(JsonViews.UserShowJsonViews.class)
    public CustomResponse<User> update(@Valid @RequestBody UserDTO userDTO, @PathVariable String id) {
        return CustomResponse.success(userService.persist(userDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_USER_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return CustomResponse.success(userService.delete(id));
    }
}