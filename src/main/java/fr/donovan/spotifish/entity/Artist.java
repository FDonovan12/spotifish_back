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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ARTIST")
public class Artist extends User  {

    @OneToMany(mappedBy = "artist")
    @JsonView(JsonViewsArtist.SongArtists.class)
    private List<SongArtist> songArtists = new ArrayList<>();

    @OneToMany(mappedBy = "artist")
    @JsonView(JsonViewsArtist.Albums.class)
    private List<Album> albums = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "members_group",
        joinColumns = @JoinColumn(name="members_uuid"),
        inverseJoinColumns = @JoinColumn(name="group_uuid"))
    @JsonView(JsonViewsArtist.Members.class)
    private List<Artist> members = new ArrayList<>();

    @ManyToMany(mappedBy = "members")
    @JsonView(JsonViewsArtist.Group.class)
    private List<Artist> group = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ARTIST"));
    }
}