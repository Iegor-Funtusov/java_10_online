package ua.com.alevel.dto;

import lombok.Data;

import java.util.Collections;
import java.util.Map;

@Data
public class DataTableRequest {
    private int page;
    private int size;
    private String column;
    private OrderType orderType;
    private Map<String, String> parameters;

    public DataTableRequest() {
        this.page = 1;
        this.size = 10;
        this.column = "id";
        this.orderType = OrderType.ASC;
        this.parameters = Collections.emptyMap();
    }
}
