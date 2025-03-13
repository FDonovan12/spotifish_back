package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.Historical;
import fr.donovan.spotifish.repository.HistoricalRepository;
import fr.donovan.spotifish.dto.HistoricalDTO;
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
public class HistoricalService  {

    private final HistoricalRepository historicalRepository;
    private final UserService userService;
    private final SongService songService;
    private final SecurityService securityService;
    public List<Historical> findAll() {
        return this.historicalRepository.findAll();
    }

    public Historical getObjectById(String id) {
        Optional<Historical> optionalHistorical = historicalRepository.findById(id);
        Historical historical = optionalHistorical.orElseThrow(() -> new NotFoundSpotifishException("HistoricalService - getObjectById("+id+")", "Historical", id));
        securityService.assertCanSee(historical);
        return historical;
    }
    public Historical getObjectBySlug(String slug) {
        Optional<Historical> optionalHistorical = historicalRepository.findBySlug(slug);
        Historical historical = optionalHistorical.orElseThrow(() -> new NotFoundSpotifishException("HistoricalService - getObjectBySlug("+slug+")", "Historical", slug));
        securityService.assertCanSee(historical);
        return historical;
    }

    public Boolean delete(String id) {
        Historical historical = getObjectById(id);
        securityService.assertCanDelete(historical);
        historicalRepository.delete(historical);
        return true;
    }

    public Historical persist(HistoricalDTO historicalDTO) {
        return persist(historicalDTO, null);
    }

    public Historical persist(HistoricalDTO historicalDTO, String id) {
        Historical historical = new Historical();
        if (id != null) {
            historical = getObjectById(id);
            securityService.assertCanEdit(historical);
        }
        historical = getObjectFromDTO(historicalDTO, historical);
        return historicalRepository.saveAndFlush(historical);
    }

    public HistoricalDTO getDTOById(String id) {
        Historical historical = getObjectById(id);
        return getDTOFromObject(historical);
    }

    public HistoricalDTO getDTOFromObject(Historical historical) {
        HistoricalDTO historicalDTO = new HistoricalDTO();
        historicalDTO.setNumberOflisten(historical.getNumberOflisten());
        historicalDTO.setListenAt(historical.getListenAt());
        historicalDTO.setUserId(historical.getUser().getUuid());
        historicalDTO.setSongId(historical.getSong().getUuid());
        return historicalDTO;
    }
    public Historical getObjectFromDTO(HistoricalDTO historicalDTO) {
        return getObjectFromDTO(historicalDTO, new Historical());
    }
    public Historical getObjectFromDTO(HistoricalDTO historicalDTO, Historical historical) {
        historical.setNumberOflisten(historicalDTO.getNumberOflisten());
        historical.setListenAt(historicalDTO.getListenAt());
        historical.setUser(userService.getObjectById(historicalDTO.getUserId()));
        historical.setSong(songService.getObjectById(historicalDTO.getSongId()));
        historical.setSlug("test");
        return historical;
    }
}