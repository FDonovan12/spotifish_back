package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.Shared;
import fr.donovan.spotifish.repository.SharedRepository;
import fr.donovan.spotifish.dto.SharedDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
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

    public List<Shared> findAll() {
        return this.sharedRepository.findAll();
    }

    public Shared getObjectById(String id) {
        Optional<Shared> optionalShared = sharedRepository.findById(id);
        return optionalShared.orElseThrow(() -> new NotFoundSpotifishException("SharedService - getObjectById("+id+")", "Shared", id));
    }
    public Shared getObjectBySlug(String slug) {
        Optional<Shared> optionalShared = sharedRepository.findBySlug(slug);
        return optionalShared.orElseThrow(() -> new NotFoundSpotifishException("SharedService - getObjectBySlug("+slug+")", "Shared", slug));
    }

    public Boolean delete(String id) {
        sharedRepository.deleteById(id);
        return true;
    }

    public Shared persist(SharedDTO sharedDTO) {
        return persist(sharedDTO, null);
    }

    public Shared persist(SharedDTO sharedDTO, String id) {
        Shared shared = new Shared();
        if (id != null) {
            shared = getObjectById(id);
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
        sharedDTO.setUserId(shared.getUser().getUuid());
        return sharedDTO;
    }
    public Shared getObjectFromDTO(SharedDTO sharedDTO) {
        return getObjectFromDTO(sharedDTO, new Shared());
    }
    public Shared getObjectFromDTO(SharedDTO sharedDTO, Shared shared) {
        shared.setExpireAt(sharedDTO.getExpireAt());
        shared.setRemainingInvitation(sharedDTO.getRemainingInvitation());
        shared.setUser(playlistService.getObjectById(sharedDTO.getUserId()));
        shared.setSlug("test");
        return shared;
    }


}