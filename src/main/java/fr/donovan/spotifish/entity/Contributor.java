package fr.donovan.spotifish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.entity.embed.*;
import fr.donovan.spotifish.json_view.*;
import fr.donovan.spotifish.entity.interfaces.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Contributor implements SluggerInterface, PermissionEntityInterface {

    @EmbeddedId
    @JsonView(JsonViewsContributor.Id.class)
    private ContributorId id;

    @JsonView(JsonViewsUser.Uuid.class)
    private String uuid;

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
    @Column(nullable = false, unique = true)
    private String slug;

    @Override
    public String getField() {
        return this.playlist.getSlug() + '_' + this.user.getSlug();
    }

    @PrePersist
    public void prePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

    public String getIdToSerializer() {
        return this.uuid;
    }

    @Override
    public boolean canDelete(User user) {
        return this.canEdit(user);
    }

    @Override
    public boolean canEdit(User user) {
        if (user == null) return false;
        if (this.playlist.canEdit(user)) return true;
        if (this.user.canEdit(user)) return true;
        return false;
    }
}