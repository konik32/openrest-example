package example.exceptions;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.openrest.dto.validation.constraint.DtoConstraintViolationException;
import pl.openrest.dto.validation.constraint.DtoConstraintViolationExceptionMessage;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private Logger log = Logger.getLogger(ExceptionControllerAdvice.class);

    private MessageSourceAccessor messageSourceAccessor;

    @Autowired
    public ExceptionControllerAdvice(MessageSource messageSource) {
        this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
    }

    @ExceptionHandler(DtoConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<DtoConstraintViolationExceptionMessage> handleValidationException(DtoConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<DtoConstraintViolationExceptionMessage>(new DtoConstraintViolationExceptionMessage(ex,
                messageSourceAccessor), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<String>("Access denied exception", HttpStatus.FORBIDDEN);
    }

}
