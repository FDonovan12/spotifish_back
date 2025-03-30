package fr.donovan.spotifish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.event_listener.SluggerEventListener;
import fr.donovan.spotifish.json_view.*;
import fr.donovan.spotifish.entity.interfaces.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter

@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class LikeableItem implements SluggerInterface, PermissionEntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViewsLikeableItem.Uuid.class)
    private String uuid;

    @JsonView(JsonViewsLikeableItem.Name.class)
    @Column(nullable = false)
    private String name;

    @JsonView(JsonViewsLikeableItem.Slug.class)
    @Column(nullable = false, unique = true)
    private String slug;

    @Override
    public String getField() {
        return this.name + " " + this.getUuid().substring(0, 8);
    }

    @JsonView(JsonViews.AllJsonViews.class)
    public IsLiked isLiked() {
        return new IsLiked(false, this);
    }

    public String getIdToSerializer() {
        return this.getUuid();
    }
}