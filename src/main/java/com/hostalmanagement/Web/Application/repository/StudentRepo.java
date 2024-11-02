package com.hostalmanagement.Web.Application.repository;


import com.hostalmanagement.Web.Application.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    // Method to retrieve data from the view
    @Query(value = "SELECT * FROM StudentView", nativeQuery = true)
    List<Student> getStudentFromView();

}
