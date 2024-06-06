package ua.com.alevel.dto.response;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.store.SavedQuery;

@Getter
@Setter
public class SavedQueryResponse extends ApiResponse<String> {
    private String query;

    public SavedQueryResponse(SavedQuery savedQuery) {
        setId(savedQuery.getId());
        setQuery(savedQuery.getQuery());
    }
}
