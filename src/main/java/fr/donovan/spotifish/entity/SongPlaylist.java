package fr.donovan.spotifish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.entity.embed.SongArtistId;
import fr.donovan.spotifish.json_view.*;
import fr.donovan.spotifish.entity.interfaces.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
public class SongPlaylist implements SluggerInterface, PermissionEntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViewsSongPlaylist.Id.class)
    private String uuid;

    @JsonView(JsonViewsSongPlaylist.Position.class)
    @Column(nullable = false)
    private int position;

    @CreationTimestamp
    @JsonView(JsonViewsSongPlaylist.CreatedAt.class)
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonView(JsonViewsSongPlaylist.Song.class)
    @JoinColumn(nullable = false)
    private Song song;

    @ManyToOne
    @JsonView(JsonViewsSongPlaylist.Playlist.class)
    @JoinColumn(nullable = false)
    private Playlist playlist;

    @ManyToOne
    @JsonView(JsonViewsSongPlaylist.Contributor.class)
    @JoinColumns({
            @JoinColumn(referencedColumnName = "user_uuid"),
            @JoinColumn(referencedColumnName = "playlist_uuid")
    })
    private Contributor contributor;

    @JsonView(JsonViewsSongPlaylist.Slug.class)
    @Column(nullable = false, unique = true)
    private String slug;

    @Override
    public String getField() {
        return this.getSong().getSlug() + this.getPlaylist().getSlug() + this.position;
    }

    @Override
    public String getIdToSerializer() {
        return this.uuid;
    }

    @Override
    public boolean canEdit(User user) {
        if (user == null) return false;
        if (
                !this.playlist.getContributors().stream()
                        .map(Contributor::getUser)
                        .filter(user::isTheSameUser)
                        .toList().isEmpty()
        ) return true;
        if (user.isModerator()) return true;
        return false;
    }

    @Override
    public boolean canDelete(User user) {
        if (user == null) return false;
        if (
                user.isTheSameUser(this.contributor.getUser())  &&
                (this.contributor.getStillContributing() ||
                this.contributor.getIsOwner())
        ) return true;
        if (user.isModerator()) return true;
        return false;
    }
}