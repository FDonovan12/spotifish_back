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
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class SongAlbum implements SluggerInterface, PermissionEntityInterface {

    @EmbeddedId
    @JsonView(JsonViewsSongAlbum.Id.class)
    private SongAlbumId id;

    @JsonView(JsonViewsUser.Uuid.class)
    private String uuid;

    @JsonView(JsonViewsSongAlbum.Position.class)
    @Column(nullable = false)
    private int position;

    @CreationTimestamp
    @JsonView(JsonViewsSongAlbum.CreatedAt.class)
    private LocalDateTime createdAt;

    @MapsId("songId")
    @ManyToOne
    @JsonView(JsonViewsSongAlbum.Song.class)
    @JoinColumn(nullable = false)
    private Song song;

    @MapsId("albumId")
    @ManyToOne
    @JsonView(JsonViewsSongAlbum.Album.class)
    @JoinColumn(nullable = false)
    private Album album;

    @JsonView(JsonViewsSongAlbum.Slug.class)
    @Column(nullable = false, unique = true)
    private String slug;

    @Override
    public String getField() {
        return this.song.getSlug() + '_' + this.album.getSlug();
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
        return this.album.canDelete(user);
    }

    @Override
    public boolean canEdit(User user) {
        return this.album.canEdit(user);
    }
}