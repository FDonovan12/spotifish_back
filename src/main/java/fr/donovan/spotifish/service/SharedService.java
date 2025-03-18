package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.Shared;
import fr.donovan.spotifish.repository.SharedRepository;
import fr.donovan.spotifish.dto.SharedDTO;
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
public class SharedService  {

    private final SharedRepository sharedRepository;
    private final PlaylistService playlistService;
    private final SecurityService securityService;
    public List<Shared> findAll() {
        return this.sharedRepository.findAll();
    }

    public Shared getObjectById(String id) {
        Optional<Shared> optionalShared = sharedRepository.findById(id);
        Shared shared = optionalShared.orElseThrow(() -> new NotFoundSpotifishException("SharedService - getObjectById("+id+")", "Shared", id));
        securityService.assertCanSee(shared);
        return shared;
    }
    public Shared getObjectBySlug(String slug) {
        Optional<Shared> optionalShared = sharedRepository.findBySlug(slug);
        Shared shared = optionalShared.orElseThrow(() -> new NotFoundSpotifishException("SharedService - getObjectBySlug("+slug+")", "Shared", slug));
        securityService.assertCanSee(shared);
        return shared;
    }

    public Boolean delete(String id) {
        Shared shared = getObjectById(id);
        securityService.assertCanDelete(shared);
        sharedRepository.delete(shared);
        return true;
    }

    public Shared persist(SharedDTO sharedDTO) {
        return persist(sharedDTO, null);
    }

    public Shared persist(SharedDTO sharedDTO, String id) {
        Shared shared = new Shared();
        if (id != null) {
            shared = getObjectById(id);
            securityService.assertCanEdit(shared);
        }
        shared = getObjectFromDTO(sharedDTO, shared);
        return sharedRepository.saveAndFlush(shared);
    }

    public SharedDTO getDTOById(String id) {
        Shared shared = getObjectById(id);
        return getDTOFromObject(shared);
    }

    public SharedDTO getDTOFromObject(Shared shared) {
        SharedDTO sharedDTO = new SharedDTO();
        sharedDTO.setExpireAt(shared.getExpireAt());
        sharedDTO.setRemainingInvitation(shared.getRemainingInvitation());
        sharedDTO.setPlaylistSlug(shared.getPlaylist().getUuid());
        return sharedDTO;
    }
    public Shared getObjectFromDTO(SharedDTO sharedDTO) {
        return getObjectFromDTO(sharedDTO, new Shared());
    }
    public Shared getObjectFromDTO(SharedDTO sharedDTO, Shared shared) {
        shared.setExpireAt(sharedDTO.getExpireAt());
        shared.setRemainingInvitation(sharedDTO.getRemainingInvitation());
        shared.setPlaylist(playlistService.getObjectBySlug(sharedDTO.getPlaylistSlug()));
        shared.setSlug("test");
        return shared;
    }
}