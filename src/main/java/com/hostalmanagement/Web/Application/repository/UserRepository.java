package com.hostalmanagement.Web.Application.repository;
import com.hostalmanagement.Web.Application.dto.GetAdminProfileDetails;
import com.hostalmanagement.Web.Application.model.User;
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

    @Procedure(procedureName = "updateAdminProfilePro")
    String updateAdminProfile(
            @Param("idIn") Integer id,
            @Param("firstnameIn") String firstname,
            @Param("lastnameIn") String lastname,
            @Param("emailIn") String email
    );

    @Query(value = "SELECT checkPasswordIsMatched(:idId, :passwordIn)", nativeQuery = true)
    Boolean checkUserPassword(
            @Param("idId") Integer id,
            @Param("passwordIn") String password
    );


    @Query("SELECT u.password FROM User u WHERE u.id = :id")
    Optional<String> getEncodedPasswordById(@Param("id") Integer id);



    @Query(value = "SELECT * FROM GetAllSystemUsers", nativeQuery = true)
    List<User> getUsersExceptStudent();


    @Query(value = "SELECT new com.hostalmanagement.Web.Application.dto.GetAdminProfileDetails(u.id, u.firstname, u.lastname, u.email) " +
            "FROM User u WHERE u.id = :id")
    Optional<GetAdminProfileDetails> findAdminDetailsByEmail(@Param("id") Integer id);


}
