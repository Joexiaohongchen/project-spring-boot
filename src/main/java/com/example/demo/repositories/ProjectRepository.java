package com.example.demo.repositories;

import com.example.demo.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//CrudRepository is a Spring Data interface for generic CRUD operations on a repository of a specific type.
// It provides several methods out of the box for interacting with a database.
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    //in spring, it allows user to specific the method once field is created in the domain to access the field.
    //for example, in Project of domain, since there is a field called projectIdentifier, we can declare the method called
    //findBy{field name in domain class}
    Project findByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();
}
