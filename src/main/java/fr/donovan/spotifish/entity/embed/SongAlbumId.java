package fr.donovan.spotifish.entity.embed;

import java.io.Serializable;
import fr.donovan.spotifish.entity.*;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongAlbumId implements Serializable {

    private String songId;

    private String albumId;
}