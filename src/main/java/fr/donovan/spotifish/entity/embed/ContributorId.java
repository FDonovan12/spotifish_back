package fr.donovan.spotifish.entity.embed;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.json_view.JsonViews;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonView(JsonViews.AllJsonViews.class)
public class ContributorId implements Serializable {

    private String userId;

    private String playlistId;
}