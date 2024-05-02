package ua.com.alevel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataTableRequest {

    private int page;
    private int size;
    private String sort;
    private String order;
}
