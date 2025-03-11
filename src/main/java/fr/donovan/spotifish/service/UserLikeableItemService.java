package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.UserLikeableItem;
import fr.donovan.spotifish.entity.embed.*;
import fr.donovan.spotifish.repository.UserLikeableItemRepository;
import fr.donovan.spotifish.dto.UserLikeableItemDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import fr.donovan.spotifish.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public UserLikeableItem getObjectById(UserLikeableItemId id) {
        Optional<UserLikeableItem> optionalUserLikeableItem = userLikeableItemRepository.findById(id);
        UserLikeableItem userLikeableItem = optionalUserLikeableItem.orElseThrow(() -> new NotFoundSpotifishException("UserLikeableItemService - getObjectById("+id+")", "UserLikeableItem", id));
        securityService.assertCanSee(userLikeableItem);
        return userLikeableItem;
    }
    public UserLikeableItem getObjectBySlug(String slug) {
        Optional<UserLikeableItem> optionalUserLikeableItem = userLikeableItemRepository.findBySlug(slug);
        UserLikeableItem userLikeableItem = optionalUserLikeableItem.orElseThrow(() -> new NotFoundSpotifishException("UserLikeableItemService - getObjectBySlug("+slug+")", "UserLikeableItem", slug));
        securityService.assertCanSee(userLikeableItem);
        return userLikeableItem;
    }

    public Boolean delete(UserLikeableItemId id) {
        UserLikeableItem userLikeableItem = getObjectById(id);
        securityService.assertCanDelete(userLikeableItem);
        userLikeableItemRepository.delete(userLikeableItem);
        return true;
    }

    public UserLikeableItem persist(UserLikeableItemDTO userLikeableItemDTO) {
        return persist(userLikeableItemDTO, null);
    }

    public UserLikeableItem persist(UserLikeableItemDTO userLikeableItemDTO, UserLikeableItemId id) {
        UserLikeableItem userLikeableItem = new UserLikeableItem();
        if (id != null) {
            userLikeableItem = getObjectById(id);
            securityService.assertCanEdit(userLikeableItem);
        }
        userLikeableItem = getObjectFromDTO(userLikeableItemDTO, userLikeableItem);
        return userLikeableItemRepository.saveAndFlush(userLikeableItem);
    }

    public UserLikeableItemDTO getDTOById(UserLikeableItemId id) {
        UserLikeableItem userLikeableItem = getObjectById(id);
        return getDTOFromObject(userLikeableItem);
    }

    public UserLikeableItemDTO getDTOFromObject(UserLikeableItem userLikeableItem) {
        UserLikeableItemDTO userLikeableItemDTO = new UserLikeableItemDTO();
        userLikeableItemDTO.setAddAt(userLikeableItem.getAddAt());
        userLikeableItemDTO.setUserId(userLikeableItem.getUser().getUuid());
        userLikeableItemDTO.setLikeableItemId(userLikeableItem.getLikeableItem().getUuid());
        return userLikeableItemDTO;
    }
    public UserLikeableItem getObjectFromDTO(UserLikeableItemDTO userLikeableItemDTO) {
        return getObjectFromDTO(userLikeableItemDTO, new UserLikeableItem());
    }
    public UserLikeableItem getObjectFromDTO(UserLikeableItemDTO userLikeableItemDTO, UserLikeableItem userLikeableItem) {
        userLikeableItem.setAddAt(userLikeableItemDTO.getAddAt());
        userLikeableItem.setUser(userService.getObjectById(userLikeableItemDTO.getUserId()));
        userLikeableItem.setLikeableItem(likeableItemService.getObjectById(userLikeableItemDTO.getLikeableItemId()));
        userLikeableItem.setId(new UserLikeableItemId(userLikeableItemDTO.getUserId(), userLikeableItemDTO.getLikeableItemId()));
        userLikeableItem.setSlug("test");
        return userLikeableItem;
    }


}