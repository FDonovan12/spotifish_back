package fr.donovan.spotifish.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.interfaces.PermissionEntityInterface;
import fr.donovan.spotifish.json_view.JsonViews;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonView(JsonViews.AllJsonViews.class)
public class IsLiked {

    private boolean isLiked;

    @JsonIgnore
    private LikeableItem likeableItem;

}
