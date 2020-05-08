package librarymanagementsystemspring.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import librarymanagementsystemspring.dto.LibraryResponse;
import librarymanagementsystemspring.exception.LibraryException;

@RestControllerAdvice
public class RestControllerAdviceMain {

	@ExceptionHandler
	public LibraryResponse myExceptionHandler(LibraryException LibraryException) {
		LibraryResponse response = new LibraryResponse();
		response.setError(true);
		response.setMessage(LibraryException.getMessage());
		return response;
	}
}
