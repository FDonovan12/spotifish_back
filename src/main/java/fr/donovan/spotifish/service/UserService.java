package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.repository.UserRepository;
import fr.donovan.spotifish.dto.UserDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import fr.donovan.spotifish.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final SecurityService securityService;
    private BCryptPasswordEncoder passwordEncoder;
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User getObjectById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new NotFoundSpotifishException("UserService - getObjectById("+id+")", "User", id));
        securityService.assertCanSee(user);
        return user;
    }
    public User getObjectBySlug(String slug) {
        Optional<User> optionalUser = userRepository.findBySlug(slug);
        User user = optionalUser.orElseThrow(() -> new NotFoundSpotifishException("UserService - getObjectBySlug("+slug+")", "User", slug));
        securityService.assertCanSee(user);
        return user;
    }

    public Boolean delete(String id) {
        User user = getObjectById(id);
        securityService.assertCanDelete(user);
        userRepository.delete(user);
        return true;
    }

    public User persist(UserDTO userDTO) {
        return persist(userDTO, null);
    }

    public User persist(UserDTO userDTO, String id) {
        User user = new User();
        if (id != null) {
            user = getObjectById(id);
            securityService.assertCanEdit(user);
        }
        user = getObjectFromDTO(userDTO, user);
        return userRepository.saveAndFlush(user);
    }

    public UserDTO getDTOById(String id) {
        User user = getObjectById(id);
        return getDTOFromObject(user);
    }

    public UserDTO getDTOFromObject(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBirthAt(user.getBirthAt());
        return userDTO;
    }
    public User getObjectFromDTO(UserDTO userDTO) {
        return getObjectFromDTO(userDTO, new User());
    }
    public User getObjectFromDTO(UserDTO userDTO, User user) {
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthAt(userDTO.getBirthAt());
        user.setSlug("test");
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        fr.donovan.spotifish.entity.User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Les cochons sont dans la baie"));

        return user;
    }
}