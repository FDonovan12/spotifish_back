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

@NoArgsConstructor
@Getter
@Setter
@Entity
public class SongAlbum implements SluggerInterface, EntityInterface {

    @EmbeddedId
    @JsonView(JsonViewsSongAlbum.Id.class)
    private SongAlbumId id;

    @JsonView(JsonViewsSongAlbum.Position.class)
    @Column(nullable = false)
    private int position;

    @CreationTimestamp
    @JsonView(JsonViewsSongAlbum.CreatedAt.class)
    @Column(nullable = false)
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
    @Column(nullable = false)
    private String slug;

    @Override
    public String getField() {
        return "" + getId();
    }
}