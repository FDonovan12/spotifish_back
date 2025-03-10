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
public class Shared implements SluggerInterface, PermissionEntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViewsShared.Uuid.class)
    private String uuid;

    @JsonView(JsonViewsShared.ExpireAt.class)
    @Column(nullable = false)
    private LocalDateTime expireAt;

    @JsonView(JsonViewsShared.RemainingInvitation.class)
    @Column(nullable = false)
    private Integer remainingInvitation;

    @OneToOne
    @JsonView(JsonViewsShared.User.class)
    private Playlist user;

    @JsonView(JsonViewsShared.Slug.class)
    @Column(nullable = false)
    private String slug;

    @Override
    public String getField() {
        return "" + getUuid();
    }
}