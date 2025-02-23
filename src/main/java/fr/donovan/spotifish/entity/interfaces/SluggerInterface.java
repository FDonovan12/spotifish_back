package fr.donovan.spotifish.entity.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public interface SluggerInterface {

    void setSlug(String slug);

    @JsonIgnore
    String getField();
}