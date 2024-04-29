package ua.com.alevel.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTableRequest {

    private int page;
    private int size;
    private String sort;
    private String order;
}
