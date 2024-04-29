package ua.com.alevel.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.user.User;
import ua.com.alevel.repository.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User> {
    boolean existsByEmail(String email);
}
