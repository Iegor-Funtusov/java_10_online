package org.funtushan.stockindex.type;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum StockIndexType {

    STOCK_INDEX_A("Stock Index A"),
    STOCK_INDEX_B("Stock Index B"),
    STOCK_INDEX_C("Stock Index C");

    private final String type;

    StockIndexType(String type) {
        this.type = type;
    }

    public static StockIndexType getStockIndexType(int index) {
        return Arrays.asList(StockIndexType.values()).get(index);
    }
}
