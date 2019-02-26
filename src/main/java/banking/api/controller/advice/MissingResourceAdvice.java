package banking.api.controller.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import banking.api.constants.Constants.Messages;
import banking.api.exceptions.MissingResourceException;
import banking.api.response.models.GenericResponse;

@ControllerAdvice
public class MissingResourceAdvice {
	
	@ResponseBody
	@ExceptionHandler(MissingResourceException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	GenericResponse customerNotFoundHandler(MissingResourceException e) {
		return new GenericResponse(new Date().toString(), HttpStatus.BAD_REQUEST.toString(), e.getMessage(),
				Messages.REQUEST_FAILURE);
	}

}
