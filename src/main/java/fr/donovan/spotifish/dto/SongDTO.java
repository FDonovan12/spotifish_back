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
public class SongDTO {
    
    @NotBlank(message = "The name can't be blank")
    private String name;
    
    @NotBlank(message = "The path can't be blank")
    private String path;
    
    @NotNull(message = "The duration can't be null")
    private int duration;
    
    @NotBlank(message = "The image can't be blank")
    private String image;
    
    @NotNull(message = "The createdAt can't be null")
    private LocalDate createdAt;
    
    @NotNull(message = "The numberOfListen can't be null")
    private Long numberOfListen;
}