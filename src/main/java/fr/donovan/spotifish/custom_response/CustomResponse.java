package fr.donovan.spotifish.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.json_view.JsonViews;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonView(JsonViews.AllJsonViews.class)
public class CustomResponse<T> {

    private int code;
    
    private String message;

    private String entity;

    private T value;


}