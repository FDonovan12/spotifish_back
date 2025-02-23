package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.dto.UserLoginDTO;
import fr.donovan.spotifish.dto.UserDTO;
import fr.donovan.spotifish.custom_response.JwtTokenResponse;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.json_view.JsonViews;
import fr.donovan.spotifish.mapping.UrlRoute;
import fr.donovan.spotifish.service.UserService;
import fr.donovan.spotifish.security.JwtAuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class SecurityRestController {

    private UserService userService;

    private JwtAuthenticationService jwtAuthenticationService;

    @PostMapping(path = UrlRoute.URL_REGISTER)
    @JsonView(JsonViews.UserShowJsonViews.class)
    User create(@Valid @RequestBody UserDTO userDTO) {
        return userService.persist(userDTO);
    }

    @PostMapping(path = UrlRoute.URL_LOGIN)
    ResponseEntity<JwtTokenResponse> persist(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return jwtAuthenticationService.authenticate(userLoginDTO);
    }

}