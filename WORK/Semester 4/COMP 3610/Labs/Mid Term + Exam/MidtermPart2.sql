-- Midterm part 2 Answers--
-- JAGJIT SINGH BILKHU - T00533766 

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

 CREATE TABLE e_client
	 (clientid NUMBER(10) PRIMARY KEY,
	  first_name VARCHAR2(20) NOT NULL,
	  last_name VARCHAR2(20) NOT NULL,
	  address VARCHAR2(50),
	  city VARCHAR2(20),
	  dob DATE
	 );
  

--2---------------sequence

 CREATE SEQUENCE e_client_id_seq
    START WITH 1
        INCREMENT BY 1;

--3------------------data for client

INSERT INTO e_client(CLIENTID, FIRST_NAME, LAST_NAME, DOB)
		VALUES(
		E_CLIENT_ID_SEQ.NEXTVAL,
		'Larry',
		'Ellison',
		to_date('17/08/1944','DD/MM/YYYY'));

INSERT INTO e_client(CLIENTID, FIRST_NAME, LAST_NAME, DOB)
		VALUES(
		E_CLIENT_ID_SEQ.NEXTVAL,
		'Scarlet',
		'O''Hara',
		to_date('19/10/1968','DD/MM/YYYY'));
	
--4---------------data for reservation

INSERT INTO e_room_reservation (
		resno,
		date_from,
		date_to,
		clientid,
		hotelid,
		roomno)
	VALUES (
		100,
		to_date('21/10/2017','DD/MM/YYYY'),
		to_date('23/10/2017','DD/MM/YYYY'),
		(SELECT clientid 
			FROM e_client 
			WHERE UPPER(e_client.FIRST_NAME)='LARRY' AND UPPER(e_client.LAST_NAME)='ELLISON'),
		(SELECT hotelid 
			FROM e_hotel 
			WHERE UPPER(e_hotel.H_NAME)='HILTON GARDEN INN' AND UPPER(e_hotel.H_CITY)='KAMLOOPS'),
		12
	);

--5--------------birthday this month
 
SELECT last_name,first_name, (EXTRACT(YEAR FROM SYSDATE)- EXTRACT(YEAR FROM dob)) AS Age
    FROM e_client
    WHERE EXTRACT(MONTH FROM DOB) = EXTRACT(MONTH FROM SYSDATE)

--6-------------total cost for Larry

SELECT (E_ROOM_RESERVATION.DATE_TO-E_ROOM_RESERVATION.DATE_FROM)*E_ROOM.ROOM_COST AS "TOTAL COST"
    FROM 
		E_CLIENT JOIN E_ROOM_RESERVATION ON E_CLIENT.CLIENTID = E_ROOM_RESERVATION.CLIENTID
        JOIN E_ROOM ON E_ROOM.ROOMNO = E_ROOM_RESERVATION.ROOMNO
		
    WHERE UPPER(E_CLIENT.FIRST_NAME) = 'LARRY'
        AND UPPER(E_CLIENT.LAST_NAME) = 'ELLISON'
        AND E_ROOM_RESERVATION.HOTELID = E_ROOM.HOTELID;

--7------------hotels and rooms

SELECT E_HOTEL.HOTELID,E_HOTEL.H_NAME,E_HOTEL.H_CITY, COUNT(ROOMNO) "ROOMS"
    FROM E_HOTEL LEFT OUTER JOIN E_ROOM ON E_ROOM.HOTELID = E_HOTEL.HOTELID
    GROUP BY E_HOTEL.HOTELID,E_HOTEL.H_NAME,E_HOTEL.H_CITY
    ORDER BY E_HOTEL.HOTELID;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	