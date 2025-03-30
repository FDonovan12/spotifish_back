package fr.donovan.spotifish.entity.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.Permission;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.json_view.JsonViews;
import fr.donovan.spotifish.utils.Slugger;

public interface PermissionEntityInterface {

    @JsonView(JsonViews.AllJsonViews.class)
    public default String getType() {
        return Slugger.slugify(this.getClass().getSimpleName());
    }

    public Object getIdToSerializer();

    public default boolean canCreate(User user) {
        return true;
    }

    public default boolean canSee(User user) {
        return true;
    }

    public boolean canEdit(User user);

    public boolean canDelete(User user);

    public default Permission computePermission(User user) {
        boolean canDelete = this.canDelete(user);
        Object idEntity = canDelete ? this.getIdToSerializer() : null;
        return new Permission(this.canEdit(user), canDelete, idEntity, this);
    }

    @JsonView(JsonViews.AllJsonViews.class)
    public default Permission getPermission() {
        return new Permission(false, false, null, this);
    }
}