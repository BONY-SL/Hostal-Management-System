-- Insert this file in the console to create the database and tables
INSERT INTO room (building_id, room_type, floor_number, room_capacity, room_number) VALUES
                                                                                        (1, 'Single room with AC', 1, 2, '101'),
                                                                                        (1, 'Dormitory with shared bathroom', 1, 4, '102'),
                                                                                        (1, 'Double room with balcony', 2, 1, '201'),
                                                                                        (1, 'Triple room with Wi-Fi', 2, 3, '202'),
                                                                                        (1, 'Single room with attached bathroom', 3, 2, '301'),
                                                                                        (1, 'Twin room with study desks', 3, 2, '302'),
                                                                                        (1, 'Dormitory with bunk beds', 4, 4, '401'),
                                                                                        (1, 'Single room with garden view', 4, 1, '402'),
                                                                                        (1, 'Triple room, air-conditioned', 5, 3, '501'),
                                                                                        (1, 'Double room near elevator', 5, 2, '502');


-- Insert this file in the console to create the database and tables
CREATE VIEW RoomView AS
SELECT
    id ,
    building_id ,
    room_type ,
    floor_number ,
    room_capacity ,
    room_number
FROM
    room;
DELIMITER $$

CREATE table notification (
                              notification_id INT PRIMARY KEY AUTO_INCREMENT,
                              message TEXT,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create a trigger to insert a new notification when a new complaint is added
DELIMITER //

CREATE TRIGGER after_complain_insert
    AFTER INSERT ON complain
    FOR EACH ROW
BEGIN
    -- Insert a new notification record
    INSERT INTO notification (message, created_at)
    VALUES (CONCAT('New complaint added with ID: ', NEW.complain_id), NOW());
END //

DELIMITER ;

-- Create a stored procedure to save a new complaint
CREATE TABLE power_consumption (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   room_id VARCHAR(10) NOT NULL,
                                   consumption_value DECIMAL(10, 2) NOT NULL,  -- Stores the power consumption value
                                   unit VARCHAR(10) DEFAULT 'kWh',  -- Unit of measurement (e.g., kWh for kilowatt-hours)
                                   recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Timestamp for when the data was recorded
                                   location VARCHAR(50),  -- Optional: location or area where the data was recorded
                                   remarks VARCHAR(255)  -- Optional: additional comments or notes
);

-- Assuming the week starts on 2024-11-01
INSERT INTO power_consumption (room_id, consumption_value, unit, recorded_at, location, remarks) VALUES
                                                                                                     ('R101', 5.75, 'kWh', '2024-11-01 08:00:00', 'Main Building', 'Daily power consumption'),
                                                                                                     ('R101', 6.10, 'kWh', '2024-11-02 08:00:00', 'Main Building', 'Daily power consumption'),
                                                                                                     ('R101', 5.95, 'kWh', '2024-11-03 08:00:00', 'Main Building', 'Daily power consumption'),
                                                                                                     ('R101', 6.30, 'kWh', '2024-11-04 08:00:00', 'Main Building', 'Daily power consumption'),
                                                                                                     ('R101', 5.85, 'kWh', '2024-11-05 08:00:00', 'Main Building', 'Daily power consumption'),
                                                                                                     ('R101', 6.50, 'kWh', '2024-11-06 08:00:00', 'Main Building', 'Daily power consumption'),
                                                                                                     ('R101', 5.90, 'kWh', '2024-11-07 08:00:00', 'Main Building', 'Daily power consumption');


-- Create a stored procedure to get power consumption data by room ID
DELIMITER //

CREATE PROCEDURE GetPowerConsumptionByRoomId(IN room_id_param VARCHAR(10))
BEGIN
SELECT consumption_value, recorded_at
FROM power_consumption
WHERE room_id = room_id_param;
END //

DELIMITER ;