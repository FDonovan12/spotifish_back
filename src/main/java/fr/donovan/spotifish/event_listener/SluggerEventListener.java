package fr.donovan.spotifish.event_listener;

import fr.donovan.spotifish.entity.interfaces.SluggerInterface;
import fr.donovan.spotifish.utils.Slugger;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SluggerEventListener {

    @PrePersist
    @PreUpdate
    public void generateSlug(Object entity) {
        if (!(entity instanceof SluggerInterface sluggerInterface)) return;

        if (sluggerInterface.getField() == null) return;

        sluggerInterface.setSlug(Slugger.slugify(sluggerInterface.getField()));
    }
}