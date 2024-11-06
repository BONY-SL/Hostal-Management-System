package com.hostalmanagement.Web.Application.repository;


import com.hostalmanagement.Web.Application.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s WHERE s.tg_no = :tgNo")
    boolean existsByTgNo(@Param("tgNo") String tgNo);



    @Query(value = "SELECT * FROM StudentView", nativeQuery = true)
    List<Student> getStudentFromView();

    @Query(value = "select * from total_student_count",nativeQuery = true)
    List<Student>getCountofStudent();


}
