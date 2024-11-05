-- Validate The User Token
DELIMITER $$

CREATE PROCEDURE GetValidTokensByUserId(IN userId INT)
BEGIN
    SELECT t.*
    FROM token t
             INNER JOIN user u ON t.user_id = u.id
    WHERE u.id = userId AND (t.expired = FALSE OR t.revoked = FALSE);
END $$

DELIMITER ;


DELIMITER $$

-- Haddle The Transaction During User Login
CREATE PROCEDURE ManageUserTokens(IN userId INT, IN newToken VARCHAR(255), IN tokenType VARCHAR(6))
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            -- Rollback the transaction in case of error
            ROLLBACK;
        END;

    -- Start the transaction
    START TRANSACTION;

    -- Revoke all user tokens
    UPDATE token
    SET revoked = TRUE, expired = TRUE
    WHERE user_id = userId AND (expired = FALSE OR revoked = FALSE);

    -- Insert new token
    INSERT INTO token (expired, revoked, token, token_type, user_id) VALUES (FALSE, FALSE, newToken, tokenType, userId);

    -- Commit the transaction
    COMMIT;
END $$

DELIMITER ;

-- create User Procedure
DELIMITER $$

CREATE PROCEDURE saveNewUser(
    IN firstnameIn VARCHAR(255),
    IN lastnameIn VARCHAR(255),
    IN emailIn VARCHAR(255),
    IN passwordIn VARCHAR(255),
    IN roleIn VARCHAR(50)
)
BEGIN
    -- Check if email already exists
    IF EXISTS (SELECT 1 FROM user WHERE email = emailIn) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'User with this email already exists.';
    ELSE
        -- Insert new user if email is unique
        INSERT INTO user(firstname, lastname, email, password, role)
        VALUES (firstnameIn, lastnameIn, emailIn, passwordIn, roleIn);
    END IF;
END $$

DELIMITER ;