package ua.com.alevel.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.user.User;
import ua.com.alevel.repository.BaseRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
