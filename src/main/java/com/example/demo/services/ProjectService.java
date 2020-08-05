package com.example.demo.services;


import com.example.demo.domain.Project;
import com.example.demo.exceptions.ProjectIdException;
import com.example.demo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

//@Service is used to mark the class as a service provider
@Service
public class ProjectService {

    //@Autowired enables you to inject the object dependency implicitly.
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {

        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception ex){
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }

    }

    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project " + projectId + " does not exist");
        }
        return project;
    }

    //The Java Iterable interface represents a collection of objects which is iterable - meaning which can be iterated.
    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project " + projectId + " does not exist");
        }
        //delete method import from CrudRepository interface class. since ProjectRepository extends it so we can directly
        // implement it.

        projectRepository.delete(project);

    }
}
