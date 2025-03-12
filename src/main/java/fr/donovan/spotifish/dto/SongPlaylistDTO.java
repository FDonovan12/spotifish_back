package fr.donovan.spotifish.dto;

import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.entity.embed.ContributorId;
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
public class SongPlaylistDTO {
    
    @NotNull(message = "The position can't be null")
    private int position;

    @NotNull(message = "The song can't be null")
    private String songId;
    
    @NotNull(message = "The playlist can't be null")
    private String playlistId;
    
    @NotNull(message = "The user can't be null")
    private String userId;
}