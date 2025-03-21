package fr.donovan.spotifish.entity.interfaces;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.json_view.JsonViews;

public interface ImageInterface {

    @JsonView(JsonViews.AllJsonViews.class)
    default String getImage() {
        return "";
    }
}