package fr.donovan.spotifish.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.json_view.*;
import fr.donovan.spotifish.entity.interfaces.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("USER")
public class User extends LikeableItem implements UserDetails, ImageInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViewsUser.Uuid.class)
    private String uuid;

    @JsonView(JsonViewsUser.Email.class)
    @Column(nullable = false)
    private String email;

    @JsonView(JsonViewsUser.Password.class)
    @Column(nullable = false)
    private String password;

    @JsonView(JsonViewsUser.FirstName.class)
    @Column(nullable = false)
    private String firstName;

    @JsonView(JsonViewsUser.LastName.class)
    @Column(nullable = false)
    private String lastName;

    @JsonView(JsonViewsUser.BirthAt.class)
    @Column(nullable = false)
    private LocalDate birthAt;

    @CreationTimestamp
    @JsonView(JsonViewsUser.CreatedAt.class)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViewsUser.UserLikeableItems.class)
    private List<UserLikeableItem> userLikeableItems = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViewsUser.Contributors.class)
    private List<Contributor> contributors = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_USER")
        );
    }

    @JsonIgnore
    public boolean isArtist() {
        return this.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> authority.equals("ROLE_ARTIST"));
    }

    @JsonIgnore
    public boolean isModerator() {
        return this.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> authority.equals("ROLE_MODERATOR"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    public boolean isTheSameUser(User otherUser) {
        if (otherUser == null) return false;
        return this.getUsername().equals(otherUser.getUsername());
    }

    @Override
    public boolean canDelete(User user) {
        return this.canEdit(user);
    }

    @Override
    public boolean canEdit(User user) {
        if (user == null) return false;
        if (user.isTheSameUser(this)) return true;
        if (user.isModerator()) return true;
        return false;
    }
}