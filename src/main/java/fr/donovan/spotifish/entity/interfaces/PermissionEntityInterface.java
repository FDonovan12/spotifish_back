package fr.donovan.spotifish.entity.interfaces;

import fr.donovan.spotifish.entity.User;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

public interface PermissionEntityInterface {

    public Object getIdToSerializer();

    public default boolean canSee(User user) {
        return true;
    }

    public boolean canEdit(User user);

    public boolean canDelete(User user);
}