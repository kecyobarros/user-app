package br.com.kecyo.userapp.http.exception;

import br.com.kecyo.userapp.usescases.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ExceptionProvider {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> resourceDeviceNotFound(final UserNotFoundException ex) {
        log.info("Handler Exception DeviceNotFound: {}", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.TEXT_PLAIN)
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> resourceMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        log.info("Handler Exception MethodArgumentNotValidException: {}", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.TEXT_PLAIN)
                .body(ex.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(final Exception ex){
        log.info("Handler Exception: {}", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.TEXT_PLAIN)
                .body(ex.getMessage());
    }
}
