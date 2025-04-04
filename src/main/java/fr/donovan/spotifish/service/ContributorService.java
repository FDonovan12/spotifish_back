package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.Contributor;
import fr.donovan.spotifish.entity.Playlist;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.entity.embed.*;
import fr.donovan.spotifish.repository.ContributorRepository;
import fr.donovan.spotifish.dto.ContributorDTO;
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
public class ContributorService  {

    private final ContributorRepository contributorRepository;
    private final UserService userService;
    private final PlaylistService playlistService;
    private final SecurityService securityService;
    public List<Contributor> findAll() {
        return this.contributorRepository.findAll();
    }

    public Contributor getObjectById(ContributorId id) {
        Optional<Contributor> optionalContributor = contributorRepository.findById(id);
        Contributor contributor = optionalContributor.orElseThrow(() -> new NotFoundSpotifishException("ContributorService - getObjectById("+id+")", "Contributor", id));
        securityService.assertCanSee(contributor);
        return contributor;
    }
    public Contributor getObjectBySlug(String slug) {
        Optional<Contributor> optionalContributor = contributorRepository.findBySlug(slug);
        Contributor contributor = optionalContributor.orElseThrow(() -> new NotFoundSpotifishException("ContributorService - getObjectBySlug("+slug+")", "Contributor", slug));
        securityService.assertCanSee(contributor);
        return contributor;
    }

    public Boolean delete(ContributorId id) {
        Contributor contributor = getObjectById(id);
        securityService.assertCanDelete(contributor);
        contributorRepository.delete(contributor);
        return true;
    }

    public Contributor persist(ContributorDTO contributorDTO) {
        return persist(contributorDTO, null);
    }

    public Contributor persist(ContributorDTO contributorDTO, ContributorId id) {
        Contributor contributor = new Contributor();
        contributor.setStillContributing(true);
        if (id != null) {
            contributor = getObjectById(id);
            securityService.assertCanEdit(contributor);
        }
        contributor = getObjectFromDTO(contributorDTO, contributor);

        ContributorId contributorId = new ContributorId(contributor.getUser().getUuid(), contributor.getPlaylist().getUuid());
        contributor.setId(contributorId);
        return contributorRepository.saveAndFlush(contributor);
    }

    public ContributorDTO getDTOById(ContributorId id) {
        Contributor contributor = getObjectById(id);
        return getDTOFromObject(contributor);
    }

    public ContributorDTO getDTOFromObject(Contributor contributor) {
        ContributorDTO contributorDTO = new ContributorDTO();
        contributorDTO.setIsOwner(contributor.getIsOwner());
        contributorDTO.setUserSlug(contributor.getUser().getUuid());
        contributorDTO.setPlaylistSlug(contributor.getPlaylist().getUuid());
        return contributorDTO;
    }
    public Contributor getObjectFromDTO(ContributorDTO contributorDTO) {
        return getObjectFromDTO(contributorDTO, new Contributor());
    }
    public Contributor getObjectFromDTO(ContributorDTO contributorDTO, Contributor contributor) {
        contributor.setIsOwner(contributorDTO.getIsOwner());
        contributor.setUser(userService.getObjectBySlug(contributorDTO.getUserSlug()));
        contributor.setPlaylist(playlistService.getObjectBySlug(contributorDTO.getPlaylistSlug()));
        contributor.setSlug("test");
        return contributor;
    }
}