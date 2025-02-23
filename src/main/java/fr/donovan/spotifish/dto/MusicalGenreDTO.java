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
public class MusicalGenreDTO {
    
    @NotBlank(message = "The name can't be blank")
    private String name;
    
    @NotBlank(message = "The description can't be blank")
    private String description;
    
    @NotBlank(message = "The image can't be blank")
    private String image;
}