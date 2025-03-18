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
public class SongArtistDTO {
    
    @NotNull(message = "The isPrincipalArtist can't be null")
    private Boolean isPrincipalArtist;
    
    @NotNull(message = "The song can't be null")
    private String songSlug;
    
    @NotNull(message = "The artist can't be null")
    private String artistSlug;
}