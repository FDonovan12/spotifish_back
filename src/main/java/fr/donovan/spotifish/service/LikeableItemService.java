package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.repository.LikeableItemRepository;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import fr.donovan.spotifish.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.*;

@AllArgsConstructor
@Service
public class LikeableItemService  {

    private final LikeableItemRepository likeableItemRepository;
    private final SecurityService securityService;
    private final SongService songService;
    private final PlaylistService playlistService;
    private final ArtistService artistService;
    private final ConnectedUserService connectedUserService;

    public List<LikeableItem> findAll() {
        return this.likeableItemRepository.findAll();
    }

    public LikeableItem getObjectById(String id) {
        Optional<LikeableItem> optionalLikeableItem = likeableItemRepository.findById(id);
        LikeableItem likeableItem = optionalLikeableItem.orElseThrow(() -> new NotFoundSpotifishException("LikeableItemService - getObjectById("+id+")", "LikeableItem", id));
        securityService.assertCanSee(likeableItem);
        return likeableItem;
    }
    public LikeableItem getObjectBySlug(String slug) {
        Optional<LikeableItem> optionalLikeableItem = likeableItemRepository.findBySlug(slug);
        LikeableItem likeableItem = optionalLikeableItem.orElseThrow(() -> new NotFoundSpotifishException("LikeableItemService - getObjectBySlug("+slug+")", "LikeableItem", slug));
        securityService.assertCanSee(likeableItem);
        return likeableItem;
    }

    public Boolean delete(String id) {
        LikeableItem likeableItem = getObjectById(id);
        securityService.assertCanDelete(likeableItem);
        likeableItemRepository.delete(likeableItem);
        return true;
    }
//
//    public LikeableItem persist(LikeableItemDTO likeableItemDTO) {
//        return persist(likeableItemDTO, null);
//    }
//
//    public LikeableItem persist(LikeableItemDTO likeableItemDTO, String id) {
//        LikeableItem likeableItem = new LikeableItem();
//        if (id != null) {
//            likeableItem = getObjectById(id);
//            securityService.assertCanEdit(likeableItem);
//        }
//        likeableItem = getObjectFromDTO(likeableItemDTO, likeableItem);
//        return likeableItemRepository.saveAndFlush(likeableItem);
//    }
//
//    public LikeableItemDTO getDTOById(String id) {
//        LikeableItem likeableItem = getObjectById(id);
//        return getDTOFromObject(likeableItem);
//    }
//
//    public LikeableItemDTO getDTOFromObject(LikeableItem likeableItem) {
//        LikeableItemDTO likeableItemDTO = new LikeableItemDTO();
//        likeableItemDTO.setName(likeableItem.getName());
//        return likeableItemDTO;
//    }
//    public LikeableItem getObjectFromDTO(LikeableItemDTO likeableItemDTO) {
//        return getObjectFromDTO(likeableItemDTO, new LikeableItem());
//    }
//    public LikeableItem getObjectFromDTO(LikeableItemDTO likeableItemDTO, LikeableItem likeableItem) {
//        likeableItem.setName(likeableItemDTO.getName());
//        likeableItem.setSlug("test");
//        return likeableItem;
//    }

    public Map<String, List<? extends LikeableItem>> search(String search) {
        Map<String, List<? extends LikeableItem>> result = new HashMap<>();

        List<Song> songs = songService.search(search);
        List<Playlist> playlists = playlistService.search(search);
        List<Artist> artists = artistService.search(search);

        result.put("songs", songs);
        result.put("playlists", playlists);
        result.put("artists", artists);

        return result;
    }

    public List<LikeableItem> getAllLiked(Principal principal) {
        User user = connectedUserService.getByCurrentUser(principal);
        return likeableItemRepository.findWithoutSong(user);
    }

    public long getSongsLikedNumber(Principal principal) {
        User user = connectedUserService.getByCurrentUser(principal);
        return likeableItemRepository.countSongsLiked(user);
    }

    public List<Song> getSongsLiked(Principal principal) {
        User user = connectedUserService.getByCurrentUser(principal);
        return likeableItemRepository.getSongsLiked(user);
    }
}