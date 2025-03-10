package fr.donovan.spotifish.security;

import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.entity.interfaces.PermissionEntityInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
public class CanEditService {

    private final SecurityService securityService;

    public boolean canEdit(PermissionEntityInterface permissionEntityInterface) {
        System.out.println("current user : " + securityService.getCurrentUser());
        return true;
    }
    public boolean canEdit(User user) {
        System.out.println("current user : " + securityService.getCurrentUser());
        return false    ;
    }
}
