package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.LikeableItem;
import fr.donovan.spotifish.repository.LikeableItemRepository;
import fr.donovan.spotifish.dto.LikeableItemDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class LikeableItemService  {

    private final LikeableItemRepository likeableItemRepository;

    public List<LikeableItem> findAll() {
        return this.likeableItemRepository.findAll();
    }

    public LikeableItem getObjectById(String id) {
        Optional<LikeableItem> optionalLikeableItem = likeableItemRepository.findById(id);
        return optionalLikeableItem.orElseThrow(() -> new NotFoundSpotifishException("LikeableItemService - getObjectById("+id+")", "LikeableItem", id));
    }
    public LikeableItem getObjectBySlug(String slug) {
        Optional<LikeableItem> optionalLikeableItem = likeableItemRepository.findBySlug(slug);
        return optionalLikeableItem.orElseThrow(() -> new NotFoundSpotifishException("LikeableItemService - getObjectBySlug("+slug+")", "LikeableItem", slug));
    }

    public Boolean delete(String id) {
        likeableItemRepository.deleteById(id);
        return true;
    }

    public LikeableItem persist(LikeableItemDTO likeableItemDTO) {
        return persist(likeableItemDTO, null);
    }

    public LikeableItem persist(LikeableItemDTO likeableItemDTO, String id) {
        LikeableItem likeableItem = new LikeableItem();
        if (id != null) {
            likeableItem = getObjectById(id);
        }
        likeableItem = getObjectFromDTO(likeableItemDTO, likeableItem);
        return likeableItemRepository.saveAndFlush(likeableItem);
    }

    public LikeableItemDTO getDTOById(String id) {
        LikeableItem likeableItem = getObjectById(id);
        return getDTOFromObject(likeableItem);
    }

    public LikeableItemDTO getDTOFromObject(LikeableItem likeableItem) {
        LikeableItemDTO likeableItemDTO = new LikeableItemDTO();
        likeableItemDTO.setName(likeableItem.getName());
        return likeableItemDTO;
    }
    public LikeableItem getObjectFromDTO(LikeableItemDTO likeableItemDTO) {
        return getObjectFromDTO(likeableItemDTO, new LikeableItem());
    }
    public LikeableItem getObjectFromDTO(LikeableItemDTO likeableItemDTO, LikeableItem likeableItem) {
        likeableItem.setName(likeableItemDTO.getName());
        likeableItem.setSlug("test");
        return likeableItem;
    }


}