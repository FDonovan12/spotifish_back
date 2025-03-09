package fr.donovan.spotifish.configuration;

import fr.donovan.spotifish.entity.interfaces.EntityInterface;
import fr.donovan.spotifish.security.CanEditService;
import lombok.AllArgsConstructor;
import org.springframework.boot.jackson.JsonMixin;

@JsonMixin(EntityInterface.class)
@AllArgsConstructor
public class CanEditMixin {

    private CanEditService canEditService;

    public boolean isCanEdit() {
        return true;
    }
}
