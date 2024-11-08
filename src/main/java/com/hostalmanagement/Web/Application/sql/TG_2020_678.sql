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


-- After Register Student Insert Default Student Details

DELIMITER $$

CREATE TRIGGER StudentRegisterTrigger
    AFTER INSERT ON user
    FOR EACH ROW
BEGIN
    DECLARE userRole VARCHAR(50);
            DECLARE tg VARCHAR(255);

            SET userRole = NEW.role;

    SELECT tgnumber INTO tg FROM studentmail WHERE email = NEW.email;

    IF (userRole = 'STUDENT') THEN
                INSERT INTO student
                (email,tg_no,user_id)
                VALUES
                (NEW.email,tg,NEW.id);
END IF ;

END $$
DELIMITER ;


-- Create View Get All System Manage Users (excepted Students)

CREATE OR REPLACE VIEW  GetAllSystemUsers AS
SELECT * FROM user WHERE role != 'STUDENT';


-- SaveStudentEmails

DELIMITER $$
CREATE PROCEDURE saveStudentEmails(

    IN InEmail VARCHAR(255),
    IN IntgNumber VARCHAR(5),
    OUT StatusMessage VARCHAR(255)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
BEGIN
            -- Set the status message for duplicate entry
            SET StatusMessage = 'Error: Duplicate email or TG Number detected.';
END;

    -- Attempt to insert the record
INSERT INTO studentmail (email, tgnumber) VALUES (InEmail, IntgNumber);

-- Set success message if no exception occurred
SET StatusMessage = 'Success: Email and TG saved successfully.';
END $$
DELIMITER ;

-- Update Trigger For Student Registration State

DELIMITER $$
CREATE TRIGGER UpdateStudentRegistrationState
    AFTER INSERT ON student
    FOR EACH ROW
BEGIN
    UPDATE studentmail SET is_registerd = true WHERE tgnumber = NEW.tg_no;
END $$
DELIMITER ;


-- Get All Registerd Students

-- Get All Registerd Students

DELIMITER $$
CREATE PROCEDURE getAllRegisterdStudents()
BEGIN
SELECT s.tg_no,
       s.department,
       s.email,
       r.is_registerd,
       CONCAT(u.firstname, ' ', u.lastname) AS fullname
FROM student s
         JOIN user u ON s.user_id = u.id
         LEFT JOIN studentmail r ON s.tg_no = r.tgnumber;
END $$
DELIMITER ;


-- get Admin Details

CREATE OR REPLACE VIEW showAllAdmins AS
SELECT id,firstname,lastname,email,password,role
FROM user
WHERE role = 'ADMIN';



-- Update Admin Profile Details

DELIMITER $$

CREATE PROCEDURE updateAdminProfilePro(
    IN idIn INT,
    IN firstnameIn VARCHAR(255),
    IN lastnameIn VARCHAR(255),
    IN emailIn VARCHAR(255),
    OUT resultMessage VARCHAR(255)
)
BEGIN
    DECLARE isEmailDuplicate BOOLEAN;
    DECLARE oldEmail VARCHAR(255);

    -- Get the old email for the user
SELECT email INTO oldEmail FROM user WHERE id = idIn;

-- Check if the new email is the same as the old email
IF emailIn = oldEmail THEN

UPDATE user
SET firstname = firstnameIn, lastname = lastnameIn, email = oldEmail
WHERE id = idIn;

-- If the new email is the same as the old email, don't update and set the message
SET resultMessage = 'Update User Details With Existing Email';
ELSE
        -- Check if the email is already in use by another user
        SET isEmailDuplicate = checkEmailIsExsistEmailWithOutOwnerUser(idIn, emailIn);

        IF isEmailDuplicate THEN
            SET resultMessage = 'Email already in use by another user';
ELSE
            -- Proceed to update the user details
UPDATE user
SET firstname = firstnameIn, lastname = lastnameIn, email = emailIn
WHERE id = idIn;

SET resultMessage = 'Profile updated successfully';
END IF;
END IF;
END $$

DELIMITER ;


-- The Function Get The User Email is Already Exsisit

DELIMITER $$

CREATE FUNCTION checkEmailIsExsistEmailWithOutOwnerUser(adminId INT, adminMail VARCHAR(255))
    RETURNS BOOLEAN
    DETERMINISTIC
BEGIN
    DECLARE emailExists BOOLEAN DEFAULT FALSE;

    -- Check if the email exists for any other user except the one with adminId
    IF EXISTS (SELECT 1 FROM user WHERE email = adminMail AND id != adminId) THEN
        SET emailExists = TRUE;
END IF;

RETURN emailExists;
END $$

DELIMITER ;


-- User Token Update After Update User Email

DELIMITER $$

CREATE PROCEDURE updateTokenAfterUpdatingUserEmail(IN idIn INT,IN tokenIn varchar(255))

BEGIN

UPDATE token SET token = tokenIn WHERE id = idIn AND revoked = false AND expired = false;

END $$

DELIMITER ;


-- Create Function Check password Matched
DELIMITER $$

CREATE FUNCTION checkPasswordIsMatched(idiN INT ,passwordIn VARCHAR(255))
    RETURNS BOOLEAN
    DETERMINISTIC
BEGIN
    DECLARE isPasswordMatched BOOLEAN DEFAULT FALSE;

    DECLARE CurrentPassword VARCHAR(255);

SELECT password INTO CurrentPassword FROM user WHERE id = idiN;

IF(CurrentPassword = passwordIn) THEN
        SET isPasswordMatched = true;
END IF ;

RETURN isPasswordMatched;

END $$

DELIMITER ;

-- create Room Procedure

DELIMITER $$

CREATE PROCEDURE createRoom(
    IN p_roomNumber VARCHAR(50),
    IN p_floorNumber INT,
    IN p_roomCapacity INT,
    IN p_description VARCHAR(255),
    IN p_buildingId BIGINT
)
BEGIN
INSERT INTO roomview (room_number, floor_number, room_capacity, room_type, building_id)
VALUES (p_roomNumber, p_floorNumber, p_roomCapacity, p_description, p_buildingId);
END $$

DELIMITER ;


-- Create View get All Rooms

CREATE OR REPLACE VIEW getAllRooms AS
SELECT * FROM room;

