package fr.donovan.spotifish.security;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
@Getter
public class SecurityServiceProvider {

    private final UserDetails currentUser;

    public SecurityServiceProvider() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            this.currentUser = (UserDetails) authentication.getPrincipal();
        } else {
            this.currentUser = null;
        }
    }
}
