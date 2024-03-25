package ua.com.alevel.dao;

import ua.com.alevel.type.OrderType;

import java.util.Collections;
import java.util.Map;

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
