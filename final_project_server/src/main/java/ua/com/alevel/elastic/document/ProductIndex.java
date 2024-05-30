package ua.com.alevel.elastic.document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Builder(toBuilder = true)
@Document(indexName = "productindex")
public class ProductIndex {

    @Id
    private String id;

    @Field(name = "productInfo", type = FieldType.Text)
    private String productInfo;

    @Field(name = "productId", type = FieldType.Long)
    private Long productId;
}
