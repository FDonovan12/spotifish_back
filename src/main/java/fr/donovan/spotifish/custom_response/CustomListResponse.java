package fr.donovan.spotifish.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.custom_response.CustomResponse;
import fr.donovan.spotifish.json_view.JsonViews;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@JsonView(JsonViews.AllJsonViews.class)
public class CustomListResponse<T> extends CustomResponse<List<T>> {

    private int pagesElement;

    private int currentPages;

    private Pageable previousPage;

    private Pageable firstPage;

    private Pageable nextPage;

    private Pageable lastPage;

    private int totalPages;

    private Long totalElements;

    public CustomListResponse(HttpStatus StatusCode, Page<T> page) {
        super(StatusCode, page.getContent());
        this.pagesElement = page.getNumberOfElements();
        this.currentPages = page.getNumber();
        this.previousPage = page.previousPageable();
        this.firstPage = page.previousOrFirstPageable();
        this.nextPage = page.nextPageable();
        this.lastPage = page.nextOrLastPageable();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }

    public CustomListResponse(HttpStatus StatusCode, List<T> list) {
        super(StatusCode, list);
        this.pagesElement = list.size();
        this.currentPages = 1;
        this.totalPages = 1;
        this.totalElements = (long) list.size();
    }

    public static <T> CustomListResponse<T> success(List<T> body) {
        return new CustomListResponse<>(HttpStatus.OK, body);
    }

    public static <T> CustomListResponse<T> success(Page<T> body) {
        return new CustomListResponse<>(HttpStatus.OK, body);
    }
}