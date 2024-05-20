package ua.com.alevel.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.request.DataTableRequest;
import ua.com.alevel.entity.user.User;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.NotValidDataException;
import ua.com.alevel.repository.user.UserRepository;
import ua.com.alevel.service.UserService;
import ua.com.alevel.util.ExceptionUtil;
import ua.com.alevel.util.ValidatorsUtil;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(User entity) {
        checkCorrectUser(entity);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND);
        }
        return optionalUser.get();
    }

    @Override
    public Page<User> findAll(DataTableRequest request) {
        return null;
    }

    private void checkCorrectUser(final User user) {
        checkIdIsNotNull(user.getId());
        checkEmailIsNull(user.getEmail());
        checkEmailIsValid(user.getEmail());
        checkEmailIsExists(user.getEmail());
        checkPasswordIsNull(user.getPassword());
    }

    private void checkIdIsNotNull(final Long id) {
        if (id != null) {
            throw new NotValidDataException(ExceptionUtil.USER_ALREADY_EXISTS);
        }
    }

    private void checkEmailIsNull(final String email) {
        if (email == null) {
            throw new NotValidDataException(ExceptionUtil.USER_EMAIL_IS_NOT_PRESENT);
        }
    }

    private void checkEmailIsValid(final String email) {
        if (!email.matches(ValidatorsUtil.EMAIL_REGEX_PATTERN)) {
            throw new NotValidDataException(ExceptionUtil.USER_EMAIL_IS_NOT_VALID);
        }
    }

    private void checkEmailIsExists(final String email) {
        if (userRepository.existsByEmail(email)) {
            throw new NotValidDataException(ExceptionUtil.USER_EMAIL_IS_EXISTS);
        }
    }

    private void checkPasswordIsNull(final String password) {
        if (password == null) {
            throw new NotValidDataException(ExceptionUtil.USER_PASSWORD_IS_NOT_PRESENT);
        }
    }
}
