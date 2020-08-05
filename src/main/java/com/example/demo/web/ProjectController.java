package com.example.demo.web;


import com.example.demo.domain.Project;
import com.example.demo.services.MapValidationError;
import com.example.demo.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//API
//@RestController is a convenience annotation that is itself annotated with @Controller and @ResponseBody .
// This annotation is applied to a class to mark it as a request handler. Spring RestController annotation is used to create RESTful web services using Spring MVC.
@RestController
//@RequestMapping maps HTTP requests to handler methods of MVC and REST controllers.
@RequestMapping("api/project")
public class ProjectController {

    //step1: insert Project object to allow us to create a new project
    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationError mapValidationErrors;

    //step2: create the route for us to create a new project
    //@PostMapping return a response entity of type of project
    @PostMapping("")
    //ResponseEntity is a type that allows us to have more control on our Json responses and response status.
    //@Valid pass to the request body means validate the request body.
    //BindingResult holds the result of a validation and binding and contains errors that may have occurred.
    // The BindingResult must come right after the model object that is validated or else Spring fails to validate the object and throws an exception.
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrors.mapValidationError(result);
        if (errorMap != null){
            return errorMap;
        }
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
}
