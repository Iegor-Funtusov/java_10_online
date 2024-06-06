package ua.com.alevel.elastic.document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Setter
@Getter
@Document(indexName = "query_searches")
public class QuerySearch {

    @Id
    private String id;

    @Field(name = "query", type = FieldType.Text)
    private String query;
}
