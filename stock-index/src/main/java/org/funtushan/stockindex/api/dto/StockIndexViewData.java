package org.funtushan.stockindex.api.dto;

public record StockIndexViewData(
        String name,
        double currentValue,
        double maxValue,
        double minValue
) { }
