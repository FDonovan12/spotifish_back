package fr.donovan.spotifish.security;

import fr.donovan.spotifish.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityService {

    private ObjectProvider<SecurityServiceProvider> securityServiceProvider;

    private UserService userService;

    public UserDetails getCurrentUser() {
        SecurityServiceProvider securityServiceProvide = securityServiceProvider.getIfAvailable();
        if (securityServiceProvide == null) {
            return null;
        }
        return userService.getByCurrentUser(securityServiceProvide.getCurrentUser());
    }
}
