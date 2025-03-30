package fr.donovan.spotifish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.entity.embed.SongArtistId;
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
@DiscriminatorValue("SONG")
public class Song extends LikeableItem implements ImageInterface {

    @JsonView(JsonViewsSong.Duration.class)
    @Column(nullable = false)
    private long duration;

    @JsonView(JsonViewsSong.CreatedAt.class)
    @Column(nullable = false)
    private LocalDate createdAt;

    @JsonView(JsonViewsSong.NumberOfListen.class)
    @Column(nullable = false)
    private Long numberOfListen = 0L;

    @OneToMany(mappedBy = "song")
    @JsonView(JsonViewsSong.SongArtists.class)
    private List<SongArtist> songArtists = new ArrayList<>();

    @OneToMany(mappedBy = "song")
    @JsonView(JsonViewsSong.SongAlbums.class)
    private List<SongAlbum> songAlbums = new ArrayList<>();

    @OneToMany(mappedBy = "song")
    @JsonView(JsonViewsSong.SongPlaylists.class)
    private List<SongPlaylist> songPlaylists = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "song_musicalGenre",
        joinColumns = @JoinColumn(name="song_uuid"),
        inverseJoinColumns = @JoinColumn(name="musicalGenre_uuid"))
    @JsonView(JsonViewsSong.MusicalGenres.class)
    private List<MusicalGenre> musicalGenres = new ArrayList<>();

    @JsonView(JsonViewsSong.Path.class)
    public String getPath() {
        return "songs/" + getSlug() + ".mp3";
    }

    @Override
    public boolean canCreate(User user) {
//        if (user.isArtist()) return true;
        return true;
    }

    @Override
    public boolean canDelete(User user) {
        return this.canEdit(user);
    }

    @Override
    public boolean canEdit(User user) {
        if (user == null) return false;
        if (
                !this.songArtists.stream()
                        .filter(SongArtist::getIsPrincipalArtist)
                        .map(SongArtist::getArtist)
                        .filter(user::isTheSameUser)
                        .toList().isEmpty()
        ) return true;
        if (user.isModerator()) return true;
        return false;
    }

    public void addArtist(Artist artist) {
        SongArtist songArtist = new SongArtist();
        songArtist.setArtist(artist);
        songArtist.setSong(this);
//        songArtist.setSlug("this");
        songArtist.setIsPrincipalArtist(true);
//        SongArtistId songArtistId = new SongArtistId(this.getUuid(), artist.getUuid());
//        songArtist.setId(songArtistId);
        this.songArtists.add(songArtist);
    }
}