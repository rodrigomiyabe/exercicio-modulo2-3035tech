package br.com.curso3035Tech.modulo2.controllers.exceptions;

import br.com.curso3035Tech.modulo2.services.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(CpfExistenteException.class)
	public ResponseEntity<StandardError> cpfExistente(CpfExistenteException e, HttpServletRequest request) {
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(StandardError
						.builder()
						.timestamp(Instant.now())
						.error(HttpStatus.CONFLICT.name())
						.message(List.of(e.getMessage())
						)
						.path(request.getRequestURI())
						.build()
				);
	}	
	
	@ExceptionHandler(EmailExistenteException.class)
	public ResponseEntity<StandardError> emailExistente(EmailExistenteException e, HttpServletRequest request) {
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(StandardError
						.builder()
						.timestamp(Instant.now())
						.error(HttpStatus.CONFLICT.name())
						.message(List.of(e.getMessage())
						)
						.path(request.getRequestURI())
						.build()
				);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(StandardError
						.builder()
						.timestamp(Instant.now())
						.error(HttpStatus.NOT_FOUND.name())
						.message(List.of(e.getMessage())
						)
						.path(request.getRequestURI())
						.build()
				);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(StandardError
						.builder()
						.timestamp(Instant.now())
						.error(HttpStatus.BAD_REQUEST.name())
						.message(List.of(e.getMessage())
						)
						.path(request.getRequestURI())
						.build()
				);
	}


	@ExceptionHandler(EntityAlreadyExists.class)
	public ResponseEntity<StandardError> entityAlreadyExists(DatabaseException e, HttpServletRequest request) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(StandardError
						.builder()
						.timestamp(Instant.now())
						.error(HttpStatus.BAD_REQUEST.name())
						.message(List.of(e.getMessage())
						)
						.path(request.getRequestURI())
						.build()
				);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> invalid(MethodArgumentNotValidException e, HttpServletRequest request) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(StandardError
						.builder()
						.timestamp(Instant.now())
						.error(HttpStatus.BAD_REQUEST.name())
						.message(e.getBindingResult()
								.getFieldErrors()
								.stream()
								.map(DefaultMessageSourceResolvable::getDefaultMessage)
								.collect(Collectors.toList())
						)
						.path(request.getRequestURI())
						.build()
				);
	}


}



