package org.funtushan.stockindex.docment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Document(indexName = "stock-index")
public class StockIndex {

    @Id
    private String id;
    private String name;
    private Double value;
}
