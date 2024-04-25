package ua.com.alevel.unit.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.entity.User;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.NotValidDataException;
import ua.com.alevel.repository.UserRepository;
import ua.com.alevel.service.impl.UserServiceImpl;
import ua.com.alevel.util.ExceptionUtil;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    final Long id = 1L;
    final String correctEmail = "username@domain.com";
    final String correctPassword = "password";
    final String incorrectEmail = "username@domain";

    @Test
    public void shouldBeCreateUserWhereIdIsNotNull() {
        // given
        final User user = new User();
        user.setId(id);

        // when
        Exception thrown = Assertions.assertThrows(NotValidDataException.class, () -> userService.create(user));

        // then
        assertThat(thrown).isInstanceOf(NotValidDataException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.USER_ALREADY_EXISTS);
    }

    @Test
    public void shouldBeCreateUserWhereEmailIsNull() {
        // given
        final User user = new User();
        user.setEmail(null);

        // when
        Exception thrown = Assertions.assertThrows(NotValidDataException.class, () -> userService.create(user));

        // then
        assertThat(thrown).isInstanceOf(NotValidDataException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.USER_EMAIL_IS_NOT_PRESENT);
    }

    @Test
    public void shouldBeCreateUserWhereEmailIsValid() {
        // given
        final User user = new User();
        user.setEmail(incorrectEmail);

        // when
        Exception thrown = Assertions.assertThrows(NotValidDataException.class, () -> userService.create(user));

        // then
        assertThat(thrown).isInstanceOf(NotValidDataException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.USER_EMAIL_IS_NOT_VALID);
    }

    @Test
    public void shouldBeCreateUserWhereEmailIsExists() {
        // given

        final User user = new User();
        user.setEmail(correctEmail);
        Mockito.when(userRepository.existsByEmail(correctEmail)).thenReturn(true);

        // when
        Exception thrown = Assertions.assertThrows(NotValidDataException.class, () -> userService.create(user));

        // then
        assertThat(thrown).isInstanceOf(NotValidDataException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.USER_EMAIL_IS_EXISTS);
    }

    @Test
    public void shouldBeCreateUserWherePasswordIsNull() {
        // given
        final User user = new User();
        user.setEmail(correctEmail);
        user.setPassword(null);
        Mockito.when(userRepository.existsByEmail(correctEmail)).thenReturn(false);

        // when
        Exception thrown = Assertions.assertThrows(NotValidDataException.class, () -> userService.create(user));

        // then
        assertThat(thrown).isInstanceOf(NotValidDataException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.USER_PASSWORD_IS_NOT_PRESENT);
    }

    @Test
    public void shouldBeCreateUserWhereEmailAndPasswordIsCorrect() {
        // given
        final User user = new User();
        user.setEmail(correctEmail);
        user.setPassword(correctPassword);
        Mockito.when(userRepository.existsByEmail(correctEmail)).thenReturn(false);

        // when
        userService.create(user);

        // then
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void shouldBeFindUserByIdWhereIdIsNotExists() {
        // given
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        // when
        Exception thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> userService.findById(id));

        // then
        assertThat(thrown).isInstanceOf(EntityNotFoundException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.ENTITY_NOT_FOUND);
    }

    @Test
    public void shouldBeFindUserByIdWhereIdIsExists() {
        // given
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(new User()));

        // when
        User user = userService.findById(id);

        // then
        Assertions.assertInstanceOf(User.class, user);
    }
}
