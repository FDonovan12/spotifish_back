package fr.donovan.spotifish.security;

import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityService {

    private ObjectProvider<SecurityServiceProvider> securityServiceProvider;

    private UserService userService;

    public User getCurrentUser() {
        SecurityServiceProvider securityServiceProvide = securityServiceProvider.getIfAvailable();
        if (securityServiceProvide == null || securityServiceProvide.getCurrentUser() == null) {
            return null;
        }
        return userService.getByCurrentUser(securityServiceProvide.getCurrentUser());
    }
}
