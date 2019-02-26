package banking.api.controller.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import banking.api.constants.Constants.Messages;
import banking.api.exceptions.InvalidResourceException;
import banking.api.response.models.GenericResponse;

@ControllerAdvice
public class InvalidResourceAdvice {

	@ResponseBody
	@ExceptionHandler(InvalidResourceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	GenericResponse InvalidResourceAdvice(InvalidResourceException e) {
		return new GenericResponse(new Date().toString(), HttpStatus.BAD_REQUEST.toString(), e.getMessage(),
				Messages.REQUEST_FAILURE);
	}
}
