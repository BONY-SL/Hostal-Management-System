package com.hostalmanagement.Web.Application.repository;
import com.hostalmanagement.Web.Application.model.Fine;
import com.hostalmanagement.Web.Application.model.User;
import org.hibernate.annotations.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);


    @Procedure(procedureName = "saveNewUser")
    void saveUser(
            @Param("firstnameIn") String firstname,
            @Param("lastnameIn") String lastname,
            @Param("emailIn") String email,
            @Param("passwordIn") String password,
            @Param("roleIn") String role  // Accept as String
    );

    @Query(value = "SELECT * FROM GetAllSystemUsers", nativeQuery = true)
    List<User> getUsersExceptStudent();



}
