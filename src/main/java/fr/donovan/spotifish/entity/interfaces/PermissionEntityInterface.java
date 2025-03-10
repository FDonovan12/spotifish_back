package fr.donovan.spotifish.entity.interfaces;

import fr.donovan.spotifish.entity.User;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

public interface PermissionEntityInterface {

    public default boolean canDelete(User user) {
        if (user == null) return false;
        if (user.isModerator()) return true;
        return false;
    }

    public default boolean canEdit(User user) {
        if (user == null) return false;
        if (user.isArtist()) return true;
        return false;
    }

}