package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.Moderator;
import fr.donovan.spotifish.repository.ModeratorRepository;
import fr.donovan.spotifish.dto.ModeratorDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class ModeratorService  {

    private final ModeratorRepository moderatorRepository;

    public List<Moderator> findAll() {
        return this.moderatorRepository.findAll();
    }

    public Moderator getObjectById(String id) {
        Optional<Moderator> optionalModerator = moderatorRepository.findById(id);
        return optionalModerator.orElseThrow(() -> new NotFoundSpotifishException("ModeratorService - getObjectById("+id+")", "Moderator", id));
    }

    public Boolean delete(String id) {
        moderatorRepository.deleteById(id);
        return true;
    }

    public Moderator persist(ModeratorDTO moderatorDTO) {
        return persist(moderatorDTO, null);
    }

    public Moderator persist(ModeratorDTO moderatorDTO, String id) {
        Moderator moderator = new Moderator();
        if (id != null) {
            moderator = getObjectById(id);
        }
        moderator = getObjectFromDTO(moderatorDTO, moderator);
        return moderatorRepository.saveAndFlush(moderator);
    }

    public ModeratorDTO getDTOById(String id) {
        Moderator moderator = getObjectById(id);
        return getDTOFromObject(moderator);
    }

    public ModeratorDTO getDTOFromObject(Moderator moderator) {
        ModeratorDTO moderatorDTO = new ModeratorDTO();
        moderatorDTO.setName(moderator.getName());
        moderatorDTO.setEmail(moderator.getEmail());
        moderatorDTO.setPassword(moderator.getPassword());
        moderatorDTO.setFirstName(moderator.getFirstName());
        moderatorDTO.setLastName(moderator.getLastName());
        moderatorDTO.setBirthAt(moderator.getBirthAt());
        moderatorDTO.setCreatedAt(moderator.getCreatedAt());
        moderatorDTO.setActivationCode(moderator.getActivationCode());
        moderatorDTO.setActivationCodeExpireAt(moderator.getActivationCodeExpireAt());
        return moderatorDTO;
    }
    public Moderator getObjectFromDTO(ModeratorDTO moderatorDTO) {
        return getObjectFromDTO(moderatorDTO, new Moderator());
    }
    public Moderator getObjectFromDTO(ModeratorDTO moderatorDTO, Moderator moderator) {
        moderator.setName(moderatorDTO.getName());
        moderator.setEmail(moderatorDTO.getEmail());
        moderator.setPassword(moderatorDTO.getPassword());
        moderator.setFirstName(moderatorDTO.getFirstName());
        moderator.setLastName(moderatorDTO.getLastName());
        moderator.setBirthAt(moderatorDTO.getBirthAt());
        moderator.setCreatedAt(moderatorDTO.getCreatedAt());
        moderator.setActivationCode(moderatorDTO.getActivationCode());
        moderator.setActivationCodeExpireAt(moderatorDTO.getActivationCodeExpireAt());
        moderator.setSlug("test");
        return moderator;
    }


}