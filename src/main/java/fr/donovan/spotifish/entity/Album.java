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
@DiscriminatorValue("ALBUM")
public class Album extends LikeableItem  {

    @JsonView(JsonViewsAlbum.Description.class)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @JsonView(JsonViewsAlbum.Image.class)
    @Column(nullable = false)
    private String image;

    @JsonView(JsonViewsAlbum.CreatedAt.class)
    @Column(nullable = false)
    private LocalDate createdAt;

    @ManyToOne
    @JsonView(JsonViewsAlbum.Artist.class)
    @JoinColumn(nullable = false)
    private Artist artist;

    @OneToMany(mappedBy = "album")
    @JsonView(JsonViewsAlbum.SongAlbums.class)
    private List<SongAlbum> songAlbums = new ArrayList<>();
}