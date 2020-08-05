package com.example.demo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException{

    public ProjectIdException(String message) {
        //super keyword: The constructors of the subclass can initialize only the instance variables of the subclass.
        // Thus, when a subclass object is instantiated the subclass object must also automatically execute one of the
        // constructors of the superclass. To call a superclass constructor the super keyword is used.
        super(message);
    }
}
