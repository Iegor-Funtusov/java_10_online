package ua.com.alevel.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import ua.com.alevel.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DataTableResponse<RES extends ApiResponse> {
    private int page;
    private int size;
    private String sort;
    private String order;
    private int totalPages;
    private long totalElements;
    private boolean hasContent;
    private boolean first;
    private boolean last;
    private boolean next;
    private boolean previous;
    private List<RES> items;

    public DataTableResponse() {
        page = 1;
        size = 10;
        sort = "id";
        order = "asc";
        totalPages = 0;
        totalElements = 0;
        hasContent = false;
        first = false;
        last = false;
        next = false;
        previous = false;
        items = new ArrayList<>();
    }

    public <E extends BaseEntity> DataTableResponse(Page<E> page) {
        this.page = page.getNumber() + 1;
        this.size = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.hasContent = page.hasContent();
        this.first = page.isFirst();
        this.last = page.isLast();
        this.next = page.hasNext();
        this.previous = page.hasPrevious();
    }
}
