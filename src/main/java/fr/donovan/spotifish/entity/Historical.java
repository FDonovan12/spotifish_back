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
public class Historical implements SluggerInterface, PermissionEntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViewsHistorical.Uuid.class)
    private String uuid;

    @JsonView(JsonViewsHistorical.NumberOflisten.class)
    @Column(nullable = false)
    private Long numberOflisten;

    @JsonView(JsonViewsHistorical.ListenAt.class)
    @Column(nullable = false)
    private LocalDateTime listenAt;

    @ManyToOne
    @JsonView(JsonViewsHistorical.User.class)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JsonView(JsonViewsHistorical.Song.class)
    @JoinColumn(nullable = false)
    private Song song;

    @JsonView(JsonViewsHistorical.Slug.class)
    @Column(nullable = false)
    private String slug;

    @Override
    public String getField() {
        return "" + getUuid();
    }

    @Override
    public boolean canDelete(User user) {
        return false;
    }

    @Override
    public boolean canEdit(User user) {
        return false;
    }
}