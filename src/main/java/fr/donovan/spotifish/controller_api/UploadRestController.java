package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.custom_response.CustomResponse;
import fr.donovan.spotifish.custom_response.JwtTokenResponse;
import fr.donovan.spotifish.dto.RefreshTokenDTO;
import fr.donovan.spotifish.dto.UserDTO;
import fr.donovan.spotifish.dto.UserLoginDTO;
import fr.donovan.spotifish.entity.LikeableItem;
import fr.donovan.spotifish.entity.Song;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.json_view.JsonViews;
import fr.donovan.spotifish.mapping.UrlRoute;
import fr.donovan.spotifish.security.JwtAuthenticationService;
import fr.donovan.spotifish.service.UploadService;
import fr.donovan.spotifish.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping(UrlRoute.URL_API)
public class UploadRestController {

    private UploadService uploadService;

    @JsonView(JsonViews.LikeableItemShowJsonViews.class)
    @PostMapping(path = UrlRoute.URL_UPLOAD_IMAGE + "/{slug}")
    CustomResponse<LikeableItem> image(@RequestParam("file") MultipartFile file, @PathVariable String slug) {
        return CustomResponse.success(uploadService.uploadImage(file, slug));
    }

    @JsonView(JsonViews.SongShowJsonViews.class)
    @PostMapping(path = UrlRoute.URL_UPLOAD_SONG + "/{slug}")
    CustomResponse<Song> song(@RequestParam("file") MultipartFile file, @PathVariable String slug) {
        return CustomResponse.success(uploadService.uploadSong(file, slug));
    }
}