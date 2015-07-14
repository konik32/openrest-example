package example.exceptions;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import javax.validation.ValidationException;

import orest.exception.OrestException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.rest.webmvc.support.RepositoryConstraintViolationExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private Logger log = Logger.getLogger(ExceptionControllerAdvice.class);



	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	public ResponseEntity<String> handleValidationException(ValidationException ex) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}


}
