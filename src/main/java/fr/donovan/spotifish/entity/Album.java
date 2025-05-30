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
public class Album extends LikeableItem implements ImageInterface {

    @JsonView(JsonViewsAlbum.Description.class)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

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

    @Override
    public boolean canDelete(User user) {
        return this.canEdit(user);
    }

    @Override
    public boolean canEdit(User user) {
        if (user == null) return false;
        if (user.isModerator()) return true;
        if (user.isTheSameUser(this.artist)) return true;
        return false;
    }
}