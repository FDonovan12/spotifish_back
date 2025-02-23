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
public class ContributorDTO {
    
    @NotNull(message = "The isOwner can't be null")
    private Boolean isOwner;
    
    @NotNull(message = "The stillContributing can't be null")
    private Boolean stillContributing;
    
    @NotNull(message = "The user can't be null")
    private String userId;
    
    @NotNull(message = "The playlist can't be null")
    private String playlistId;
}