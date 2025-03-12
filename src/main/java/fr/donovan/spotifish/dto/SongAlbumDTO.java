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
public class SongAlbumDTO {
    
    @NotNull(message = "The position can't be null")
    private int position;
    
    @NotNull(message = "The song can't be null")
    private String songId;
    
    @NotNull(message = "The album can't be null")
    private String albumId;
}