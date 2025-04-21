package fr.donovan.spotifish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.json_view.*;
import fr.donovan.spotifish.entity.interfaces.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Playlist extends LikeableItem implements ImageInterface {

    @JsonView(JsonViewsPlaylist.Description.class)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @JsonView(JsonViewsPlaylist.CreatedAt.class)
    private LocalDateTime createdAt;

    @JsonView(JsonViewsPlaylist.IsPrivate.class)
    @Column(nullable = false)
    private Boolean isPrivate;

    @OneToMany(mappedBy = "playlist")
    @JsonView(JsonViewsPlaylist.Contributors.class)
    private List<Contributor> contributors = new ArrayList<>();

    @OneToMany(mappedBy = "playlist")
    @JsonView(JsonViewsPlaylist.SongPlaylists.class)
    private List<SongPlaylist> songPlaylists = new ArrayList<>();

    @Override
    public boolean canDelete(User user) {
        return this.canEdit(user);
    }

    @Override
    public boolean canEdit(User user) {
        if (user == null) return false;
        if (
                this.getContributors().stream()
                        .filter(Contributor::getIsOwner)
                        .map(Contributor::getUser)
                        .anyMatch(user::isTheSameUser)
        ) return true;
        if (user.isModerator()) return true;
        return false;
    }

    @Override
    public boolean canSee(User user) {
        if (!this.isPrivate) return true;
        if (
                this.contributors.stream()
                        .map(Contributor::getUser)
                        .anyMatch(user::isTheSameUser)
        ) return true;
        return false;
    }
}