package fr.donovan.spotifish.security;

import fr.donovan.spotifish.entity.Artist;
import fr.donovan.spotifish.entity.Moderator;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.entity.interfaces.PermissionEntityInterface;
import fr.donovan.spotifish.exception.AccessDeniedSpotifishException;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import fr.donovan.spotifish.service.ConnectedUserService;
import fr.donovan.spotifish.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityService {

    public void assertCanCreate(PermissionEntityInterface entity) {
        if (entity.canCreate(this.getCurrentUser())) return;
        throw new AccessDeniedSpotifishException(entity.getType(), "create");
    }

    public void assertCanSee(PermissionEntityInterface entity) {
        if (entity.canSee(this.getCurrentUser())) return;
        throw new AccessDeniedSpotifishException(entity.getType(), "show");
    }

    public void assertCanEdit(PermissionEntityInterface entity) {
        if (entity.canEdit(this.getCurrentUser())) return;
        throw new AccessDeniedSpotifishException(entity.getType(), "edit");
    }

    public void assertCanDelete(PermissionEntityInterface entity) {
        if (entity.canDelete(this.getCurrentUser())) return;
        throw new AccessDeniedSpotifishException(entity.getType(), "delete");
    }

    private ObjectProvider<SecurityServiceProvider> securityServiceProvider;

    private ConnectedUserService connectedUserService;

    public User getCurrentUser() {
        SecurityServiceProvider securityServiceProvide = securityServiceProvider.getIfAvailable();
        if (securityServiceProvide == null || securityServiceProvide.getCurrentUser() == null) {
            return null;
        }
        return connectedUserService.getByCurrentUser(securityServiceProvide.getCurrentUser());
    }

    public Artist getCurrentArtist() {
        User user = this.getCurrentUser();
        if (user.isArtist()) return (Artist) user;
        throw new AccessDeniedSpotifishException(user.getType(), "getCurrentUser");
    }

    public Moderator getCurrentModerator() {
        User user = this.getCurrentUser();
        if (user.isModerator()) return (Moderator) user;
        throw new AccessDeniedSpotifishException(user.getType(), "getCurrentUser");
    }


}
