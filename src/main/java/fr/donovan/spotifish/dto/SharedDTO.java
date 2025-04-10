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
public class SharedDTO {
    
    @NotNull(message = "The expireAt can't be null")
    private LocalDate expireAt;
    
    @NotNull(message = "The remainingInvitation can't be null")
    private Integer remainingInvitation;

    @NotBlank(message = "The playlistSlug can't be blank")
    private String playlistSlug;
}