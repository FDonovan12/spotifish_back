package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.annotation.InvokeMethod;
import fr.donovan.spotifish.annotation.ResolveEntity;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.custom_response.CustomResponse;
import fr.donovan.spotifish.dto.UserDTO;
import fr.donovan.spotifish.service.UserService;
import fr.donovan.spotifish.json_view.JsonViews;
import fr.donovan.spotifish.mapping.UrlRoute;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.datafaker.providers.base.Music;
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
        return new CustomResponse<>(HttpStatus.OK.value(), "UserControllerApi - list()", "User", this.userService.findAll());
    }

//    @ModelAttribute("user")
//    public User getUser(@PathVariable String slug) {
//        return this.userService.getObjectById(slug);
//    }

    @GetMapping(path = UrlRoute.URL_USER + "/{user}")
    @JsonView(JsonViews.UserShowJsonViews.class)
    public CustomResponse<User> show(@InvokeMethod(targetClass = UserService.class, methodName = "getObjectById") User user) {
        return new CustomResponse<>(HttpStatus.OK.value(), "UserControllerApi - show("+user+")", "User", user);
//        return new CustomResponse<>(HttpStatus.OK.value(), "UserControllerApi - show("+slug+")", "User", this.userService.getObjectById(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_USER_NEW)
    @JsonView(JsonViews.UserShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<User> create(@Valid @RequestBody UserDTO userDTO) {
        return new CustomResponse<>(HttpStatus.CREATED.value(), "UserControllerApi - create()", "User", userService.persist(userDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_USER_EDIT + "/{id}")
    @JsonView(JsonViews.UserShowJsonViews.class)
    public CustomResponse<User> update(@Valid @RequestBody UserDTO userDTO, @PathVariable String id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "UserControllerApi - update("+id+")", "User", userService.persist(userDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_USER_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "UserControllerApi - delete("+id+")", "User", userService.delete(id));
    }
}