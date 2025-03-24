package fr.donovan.spotifish.dto;

import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.entity.embed.ContributorId;
import jakarta.validation.constraints.*;
import lombok.Data;
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
@Data
public class SongPlaylistDTO {
    
    @NotNull(message = "The song can't be null")
    private String songSlug;
    
    @NotNull(message = "The playlist can't be null")
    private String playlistSlug;
}