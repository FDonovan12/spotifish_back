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
public class UserLikeableItem implements SluggerInterface, PermissionEntityInterface {

    @EmbeddedId
    @JsonView(JsonViewsUserLikeableItem.Id.class)
    private UserLikeableItemId id;

    @JsonView(JsonViewsUserLikeableItem.AddAt.class)
    @Column(nullable = false)
    private LocalDateTime addAt;

    @MapsId("userId")
    @ManyToOne
    @JsonView(JsonViewsUserLikeableItem.User.class)
    @JoinColumn(nullable = false)
    private User user;

    @MapsId("likeableItemId")
    @ManyToOne
    @JsonView(JsonViewsUserLikeableItem.LikeableItem.class)
    @JoinColumn(nullable = false)
    private LikeableItem likeableItem;

    @JsonView(JsonViewsUserLikeableItem.Slug.class)
    @Column(nullable = false)
    private String slug;

    @Override
    public String getField() {
        return "" + getId();
    }
}