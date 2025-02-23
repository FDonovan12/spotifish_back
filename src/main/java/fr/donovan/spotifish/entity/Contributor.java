package fr.donovan.spotifish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.entity.embed.*;
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
public class Contributor implements SluggerInterface {

    @EmbeddedId
    @JsonView(JsonViewsContributor.Id.class)
    private ContributorId id;

    @JsonView(JsonViewsContributor.IsOwner.class)
    @Column(nullable = false)
    private Boolean isOwner;

    @JsonView(JsonViewsContributor.StillContributing.class)
    @Column(nullable = false)
    private Boolean stillContributing;

    @MapsId("userId")
    @ManyToOne
    @JsonView(JsonViewsContributor.User.class)
    @JoinColumn(nullable = false)
    private User user;

    @MapsId("playlistId")
    @ManyToOne
    @JsonView(JsonViewsContributor.Playlist.class)
    @JoinColumn(nullable = false)
    private Playlist playlist;

    @JsonView(JsonViewsContributor.Slug.class)
    @Column(nullable = false)
    private String slug;

    @Override
    public String getField() {
        return "" + getId();
    }
}