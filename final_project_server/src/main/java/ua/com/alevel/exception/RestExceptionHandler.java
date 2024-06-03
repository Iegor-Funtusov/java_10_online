package ua.com.alevel.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleNotValidDataException(NotValidDataException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                new ErrorContainer(Collections.singletonList(exception.getMessage())));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorContainer(Collections.singletonList(exception.getMessage())));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInvalidApiVersionException(InvalidApiVersionException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorContainer(Collections.singletonList(exception.getMessage())));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(new ErrorContainer(errors));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception) {
        exception.getConstraintViolations().forEach(error -> System.out.println("error = " + error));
        List<String> errors = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();
        return ResponseEntity.badRequest().body(new ErrorContainer(errors));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        MethodParameter parameter = exception.getParameter();
        String parameterName = parameter.getParameterName();
        String message = exception.getMessage();
        return ResponseEntity.badRequest().body(
                new ErrorContainer(Collections.singletonList(parameterName + ": " + message)));
        // only for java 21
//        String error = STR."\{parameterName}: \{message}";
//        return ResponseEntity.badRequest().body(
//                new ErrorContainer(Collections.singletonList(error)));
    }
}
