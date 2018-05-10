-- Midterm part 2 Answers--
-- Student name 

CREATE TABLE e_hotel
(hotelid NUMBER(10) PRIMARY KEY,
 H_Name VARCHAR2(40) NOT NULL,
 H_City VARCHAR2(30) NOT NULL);
 
CREATE TABLE e_room
(hotelid NUMBER(10) REFERENCES e_hotel(hotelid),
 roomno NUMBER (5) NOT NULL,
 room_cost NUMBER (6,2) NOT NULL,
 CONSTRAINT room_pk PRIMARY KEY (hotelid, roomno));
 
 CREATE TABLE e_room_reservation
 (resno NUMBER(10) PRIMARY KEY,
  date_from DATE NOT NULL,
  date_to DATE NOT NULL,
  clientid NUMBER(10), 
  hotelid NUMBER (10),
  roomno NUMBER (5),
  CONSTRAINT refer_hotel_room_fk FOREIGN KEY (hotelid, roomno) REFERENCES e_room (hotelid, roomno));
  
INSERT INTO e_hotel VALUES(123, 'Hilton Garden Inn','Edmonton');
INSERT INTO e_hotel VALUES(223, 'Hilton Garden Inn','Kamloops');
INSERT INTO e_hotel VALUES(333, 'Hilton South','Kamloops');
INSERT INTO e_room VALUES (123,12,320.00);
INSERT INTO e_room VALUES (123,13,500.00); 
INSERT INTO e_room VALUES (123,14,150.00);
INSERT INTO e_room VALUES (223,10,150.00); 
INSERT INTO e_room VALUES (223,13,150.00);  
insert into e_room values (223,12,500.00);
COMMIT;

----------------START YOUR ANSWERS HERE
 


--1----------------client






--2---------------sequence







--3------------------data for client










--4---------------data for reservation











--5--------------birthday this month
 







--6-------------total cost for Larry








--7------------hotels and rooms
