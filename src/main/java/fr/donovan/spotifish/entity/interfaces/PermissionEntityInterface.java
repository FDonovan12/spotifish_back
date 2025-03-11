package fr.donovan.spotifish.entity.interfaces;

import fr.donovan.spotifish.entity.User;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

public interface PermissionEntityInterface {

    public boolean canDelete(User user);

    public boolean canEdit(User user);

}