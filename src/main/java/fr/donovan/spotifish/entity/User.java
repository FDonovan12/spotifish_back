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
import org.springframework.security.core.userdetails.UserDetails;

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
@DiscriminatorValue("USER")
public class User extends LikeableItem implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViewsUser.Uuid.class)
    private String uuid;

    @JsonView(JsonViewsUser.Email.class)
    private String email;

    @JsonView(JsonViewsUser.Password.class)
    private String password;

    @JsonView(JsonViewsUser.Username.class)
    private String username;

    @JsonView(JsonViewsUser.FirstName.class)
    private String firstName;

    @JsonView(JsonViewsUser.LastName.class)
    private String lastName;

    @JsonView(JsonViewsUser.BirthAt.class)
    private LocalDate birthAt;

    @JsonView(JsonViewsUser.CreatedAt.class)
    private LocalDateTime createdAt;

    @JsonView(JsonViewsUser.ActivationCode.class)
    private String activationCode;

    @JsonView(JsonViewsUser.ActivationCodeExpireAt.class)
    private LocalDateTime activationCodeExpireAt;

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViewsUser.UserLikeableItems.class)
    private List<UserLikeableItem> userLikeableItems = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViewsUser.Contributors.class)
    private List<Contributor> contributors = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}