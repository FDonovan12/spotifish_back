package fr.donovan.spotifish.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.json_view.JsonViews;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonView(JsonViews.AllJsonViews.class)
public class CustomResponse<T> {

    private int code;
    
    private T body;

    protected CustomResponse(HttpStatus httpStatus, T body) {
        this.body = body;
        this.code = httpStatus.value();
    }

    public static <T> CustomResponse<T> success(T body) {
        return new CustomResponse<>(HttpStatus.OK, body);
    }

    public static <T> CustomResponse<T> created(T body) {
        return new CustomResponse<>(HttpStatus.CREATED, body);
    }
}