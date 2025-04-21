package fr.donovan.spotifish.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginDTO {

    @NotBlank(message = "This should be a valid username")
    private String username;

    @NotBlank(message = "This should be a valid password")
    private String password;
}