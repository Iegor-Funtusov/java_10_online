package ua.com.alevel.store;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Document(collection = "saved_queries")
public class SavedQuery {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Indexed
    private String query;

    public SavedQuery(String query) {
        this.query = query;
    }
}
