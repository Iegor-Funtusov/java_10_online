package ua.com.alevel.repository.version;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.version.ApiVersion;
import ua.com.alevel.repository.BaseRepository;

@Repository
public interface ApiVersionRepository extends BaseRepository<ApiVersion> {

    ApiVersion findTop1ByOrderByIdDesc();
}
