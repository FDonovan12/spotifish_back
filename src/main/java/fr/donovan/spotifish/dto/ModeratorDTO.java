package fr.donovan.spotifish.dto;

import fr.donovan.spotifish.entity.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ModeratorDTO {
    
    @NotBlank(message = "The name can't be blank")
    private String name;
    
    @NotBlank(message = "The email can't be blank")
    private String email;
    
    @NotBlank(message = "The password can't be blank")
    private String password;

    @NotBlank(message = "The firstName can't be blank")
    private String firstName;
    
    @NotBlank(message = "The lastName can't be blank")
    private String lastName;
    
    @NotNull(message = "The birthAt can't be null")
    private LocalDate birthAt;
}