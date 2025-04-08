package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.LikeableItem;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.entity.UserLikeableItem;
import fr.donovan.spotifish.entity.embed.*;
import fr.donovan.spotifish.repository.UserLikeableItemRepository;
import fr.donovan.spotifish.dto.UserLikeableItemDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import fr.donovan.spotifish.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class UserLikeableItemService  {

    private final UserLikeableItemRepository userLikeableItemRepository;
    private final UserService userService;
    private final LikeableItemService likeableItemService;
    private final SecurityService securityService;
    public List<UserLikeableItem> findAll() {
        return this.userLikeableItemRepository.findAll();
    }

    public boolean like(String slugLikeableItem) {
        System.out.println("UserLikeableItemService.like");
        return persist(slugLikeableItem, securityService.getCurrentUser());
    }

    public boolean persist(String slugLikeableItem, User user) {
        UserLikeableItem userLikeableItem = new UserLikeableItem();
        LikeableItem likeableItem = likeableItemService.getObjectBySlug(slugLikeableItem);

        userLikeableItem.setLikeableItem(likeableItem);
        userLikeableItem.setUser(user);
        UserLikeableItemId userLikeableItemId = new UserLikeableItemId(user, likeableItem);
        userLikeableItem.setId(userLikeableItemId);
        userLikeableItem.setAddAt(LocalDateTime.now());

        userLikeableItemRepository.saveAndFlush(userLikeableItem);
        return true;
    }

    public Boolean delete(String slugLikeableItem) {
        LikeableItem likeableItem = likeableItemService.getObjectBySlug(slugLikeableItem);
        User user = securityService.getCurrentUser();
        UserLikeableItemId userLikeableItemId = new UserLikeableItemId(user, likeableItem);

        userLikeableItemRepository.deleteById(userLikeableItemId);
        return true;
    }

    public boolean isExist(UserLikeableItemId id) {
        return userLikeableItemRepository.existsById(id);
    }

}