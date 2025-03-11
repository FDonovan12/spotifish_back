package fr.donovan.spotifish.service;

import fr.donovan.spotifish.dto.UserDTO;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import fr.donovan.spotifish.repository.UserRepository;
import fr.donovan.spotifish.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ConnectedUserService {

    private final UserRepository userRepository;

    public User getByCurrentUser(Principal principal) {
        Optional<User> optionalUser = userRepository.findByEmail(principal.getName());
        return optionalUser.orElseThrow(() -> new NotFoundSpotifishException("UserService - getByCurrentUser("+principal.getName()+")", "User", principal.getName()));
    }

    public User getByCurrentUser(UserDetails userDetails) {
        Optional<User> optionalUser = userRepository.findByEmail(userDetails.getUsername());
        return optionalUser.orElseThrow(() -> new NotFoundSpotifishException("UserService - getByCurrentUser("+userDetails.getUsername()+")", "User", userDetails.getUsername()));
    }
}