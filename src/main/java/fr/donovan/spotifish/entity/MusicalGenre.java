package fr.donovan.spotifish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.json_view.*;
import fr.donovan.spotifish.entity.interfaces.*;
import jakarta.persistence.*;
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
@Entity
@DiscriminatorValue("MUSICAL_GENRE")
public class MusicalGenre extends LikeableItem  {

    @JsonView(JsonViewsMusicalGenre.Description.class)
    @Column(columnDefinition = "TEXT")
    private String description;

    @JsonView(JsonViewsMusicalGenre.Image.class)
    private String image;
}