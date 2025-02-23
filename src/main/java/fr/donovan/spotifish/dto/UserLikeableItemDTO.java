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
public class UserLikeableItemDTO {
    
    @NotNull(message = "The addAt can't be null")
    private LocalDateTime addAt;
    
    @NotNull(message = "The user can't be null")
    private String userId;
    
    @NotNull(message = "The likeableItem can't be null")
    private String likeableItemId;
}