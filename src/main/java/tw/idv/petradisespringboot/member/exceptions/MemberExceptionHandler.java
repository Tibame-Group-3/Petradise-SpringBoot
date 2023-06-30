package tw.idv.petradisespringboot.member.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class MemberExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            MemberNotFoundException.class,
            ChangePasswordException.class
    })
    public ResponseEntity<?> handleMemberNotFoundException(
            Exception ex,
            WebRequest request
    ) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<?> handleAccountAlreadyExistsException(
            Exception ex,
            WebRequest request
    ) {
        return buildErrorResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({
            LoginException.class,
            MemberNotVerifiedException.class,
            VerificationException.class,
    })
    public ResponseEntity<?> handleLoginException(
            Exception ex,
            WebRequest request
    ) {
        return buildErrorResponse(ex, HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<?> buildErrorResponse(Exception ex, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, status);
    }


}
