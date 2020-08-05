package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice which allows to handle exceptions across the whole application in one global handling component.
// It can be viewed as an interceptor of exceptions thrown by methods annotated with @RequestMapping and similar.
@ControllerAdvice
@RestController
//ResponseEntityExceptionHandler which is an abstract class, that is A convenient base class for @ControllerAdvice classes
// that wish to provide centralized exception handling across all @RequestMapping methods through @ExceptionHandler methods.
//This base class provides an @ExceptionHandler method for handling internal Spring MVC exceptions.
// This method returns a ResponseEntity for writing to the response with a message converter,
// in contrast to DefaultHandlerExceptionResolver which returns a ModelAndView.
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    //final keyword in a method declaration to indicate that the method cannot be overridden by subclasses.
    //WebRequest is an interface class that Mainly intended for generic web request interceptors, giving them access
    // to general request metadata, not for actual handling of the request.
    public final ResponseEntity<Object> handleProjectIdException(ProjectIdException ex, WebRequest request){
        ProjectIdExceptionResponse exceptionResponse = new ProjectIdExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
