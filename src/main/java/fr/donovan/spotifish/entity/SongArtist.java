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
public class SongArtist implements SluggerInterface {

    @EmbeddedId
    @JsonView(JsonViewsSongArtist.Id.class)
    private SongArtistId id;

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
    @Column(nullable = false)
    private String slug;

    @Override
    public String getField() {
        return "" + getId();
    }
}