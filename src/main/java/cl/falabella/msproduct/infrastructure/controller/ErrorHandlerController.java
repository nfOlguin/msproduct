package cl.falabella.msproduct.infrastructure.controller;

import cl.falabella.msproduct.application.ApplicationException;
import cl.falabella.msproduct.application.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class ErrorHandlerController {

	private ErrorResponse obtenerError(HttpStatus httpStatus, String errorMessage, String requestUri) {
		ErrorResponse error = new ErrorResponse();
		error.setCode(httpStatus.value());
		error.setMessage(errorMessage);
		error.setUrl(requestUri);

		return error;
	}
	    
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		StringBuilder errorMessage = new StringBuilder();
		fieldErrors.forEach(f -> errorMessage.append(f.getField() + " " + f.getDefaultMessage() + " | "));

		ErrorResponse error = obtenerError(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.toString(), request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse>  missingParameterExceptionHandler(HttpServletRequest request, MissingServletRequestParameterException e){
    	ErrorResponse error = obtenerError(HttpStatus.BAD_REQUEST, e.getMessage(),request.getRequestURI());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, ApplicationException e) {
		ErrorResponse error = obtenerError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

