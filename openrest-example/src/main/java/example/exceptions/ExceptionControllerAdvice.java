package example.exceptions;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.data.rest.webmvc.support.RepositoryConstraintViolationExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private Logger log = Logger.getLogger(ExceptionControllerAdvice.class);
	
	private MessageSourceAccessor messageSourceAccessor;
	
	@Autowired
	public ExceptionControllerAdvice(MessageSource messageSource) {
		this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
	}

	@ExceptionHandler(RepositoryConstraintViolationException.class)
	@ResponseBody
	public ResponseEntity<RepositoryConstraintViolationExceptionMessage> handleValidationException(RepositoryConstraintViolationException ex) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<RepositoryConstraintViolationExceptionMessage>(
				new RepositoryConstraintViolationExceptionMessage(ex, messageSourceAccessor), HttpStatus.BAD_REQUEST);
	}

}
