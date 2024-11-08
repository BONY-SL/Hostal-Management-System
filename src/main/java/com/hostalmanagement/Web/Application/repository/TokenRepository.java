package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.Token;
import com.hostalmanagement.Web.Application.util.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Integer> {


    @Procedure(procedureName = "GetValidTokensByUserId")
    List<Token> findAllValidTokenByUser(@Param("userId") Integer userId);

    Optional<Token> findByToken(String token);

    @Procedure(procedureName = "updateTokenAfterUpdatingUserEmail")
    void updateToken(
            @Param("idIn") Integer id,
            @Param("tokenIn") String token
    );

}

