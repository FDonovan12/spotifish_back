package fr.donovan.spotifish.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.entity.embed.*;
import fr.donovan.spotifish.json_view.*;
import fr.donovan.spotifish.entity.interfaces.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
public class SongArtist implements SluggerInterface, PermissionEntityInterface {

    @EmbeddedId
    @JsonView(JsonViewsSongArtist.Id.class)
    private SongArtistId id;

    @JsonView(JsonViewsUser.Uuid.class)
    private String uuid;

    @JsonView(JsonViewsSongArtist.IsPrincipalArtist.class)
    @Column(nullable = false)
    private Boolean isPrincipalArtist;

    @MapsId("songId")
    @ManyToOne
    @JsonView(JsonViewsSongArtist.Song.class)
    @JoinColumn(nullable = false)
    private Song song;

    @MapsId("artistId")
    @ManyToOne
    @JsonView(JsonViewsSongArtist.Artist.class)
    @JoinColumn(nullable = false)
    private Artist artist;

    @JsonView(JsonViewsSongArtist.Slug.class)
    @Column(nullable = false, unique = true)
    private String slug;

    @Override
    public String getField() {
        return this.song.getSlug() + '_' + this.artist.getSlug();
    }

    @PrePersist
    public void prePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }
    @Override
    public String getIdToSerializer() {
        return this.uuid;
    }

    @Override
    public boolean canDelete(User user) {
        return this.artist.canDelete(user) || this.song.canDelete(user);
    }

    @Override
    public boolean canEdit(User user) {
        return this.artist.canEdit(user) || this.song.canEdit(user);
    }
}