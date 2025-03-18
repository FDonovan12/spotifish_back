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
public class HistoricalDTO {
    
    @NotNull(message = "The numberOflisten can't be null")
    private Long numberOflisten;
    
    @NotNull(message = "The listenAt can't be null")
    private LocalDateTime listenAt;
    
    @NotNull(message = "The user can't be null")
    private String userSlug;
    
    @NotNull(message = "The song can't be null")
    private String songSlug;
}