---------------------------------------------  
-- Midterm Exam II part 2
-- Student name: 
---------------------------------------------
---------------------------------------------

-- run the following to create two tables
-- and add some initial data
---------------------------------------------

CREATE TABLE e2_vehicle
 (v_id  NUMBER(10) PRIMARY KEY,
  v_make VARCHAR2 (15) NOT NULL,
  cost_per_day NUMBER (6,2) CHECK (cost_per_day > 0));
  
CREATE TABLE e2_client
  (c_id NUMBER (10) PRIMARY KEY,
   f_name VARCHAR2(20) NOT NULL,
   l_name VARCHAR2 (20) NOT NULL,
   dob DATE NOT NULL,
   gender CHAR(1),
   comments VARCHAR2(200));

INSERT INTO e2_vehicle VALUES (100, 'FORD', 90.00);
INSERT INTO e2_vehicle VALUES (200, 'BMW', 120.00);

INSERT INTO e2_client
VALUES (1000, 'John', 'Smith',
  TO_DATE ('1970-09-09','YYYY-MM-DD'),
  'M','The best client ever in 2017!'); 
COMMIT;
--------------------------------------------
-------------------------------------------- 

-- Task 1 answer: sequence 

--------------------------------------------
--CREATE SEQUENCE e2_c_id_seq 

CREATE SEQUENCE E2_C_ID_SEQ
    START WITH 1001
    INCREMENT BY 1;

--------------------------------------------

-- Task 2  add yourself as client

--------------------------------------------

INSERT INTO e2_client (
    c_id,
    f_name,
    l_name,
    dob,
    gender,
    comments
) VALUES (
    E2_C_ID_SEQ.NEXTVAL,
    'Jagjit',
    'Bilkhu',
    to_date('05/10/1995','DD/MM/YYYY'),
    'M',
    'NO COMMENTS'
)

--COMMIT;

-----------------------------------------

-- Task 3  Create table e2_reservation

-----------------------------------------
   
CREATE TABLE E2_RESERVATION (
    R_ID NUMBER(10) PRIMARY KEY,
    START_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL,
    C_ID NUMBER(10) NOT NULL,
    V_ID NUMBER(10) NOT NULL,
    CONSTRAINT E2_DATES_CK CHECK(END_DATE>=START_DATE),
    CONSTRAINT E2_CLIENT_FK FOREIGN KEY (C_ID) REFERENCES E2_CLIENT(C_ID),
    CONSTRAINT E2_VEHICLE_FK FOREIGN KEY (V_ID) REFERENCES E2_VEHICLE(V_ID)
);

--------------------------------------------

-- Task 4  Add two reservations

--------------------------------------------

--INSERT INTO e2_reservation

INSERT INTO e2_reservation (
    r_id,
    start_date,
    end_date,
    c_id,
    v_id
) VALUES (
    200,
    SYSDATE,
    SYSDATE+2,
    (SELECT C_ID FROM E2_CLIENT 
        WHERE UPPER(F_NAME) = UPPER('JAGJIT') AND 
              UPPER(L_NAME) = UPPER('BILKHU') AND
              DOB = to_date('05/10/1995','DD/MM/YYYY')),
    100
);

--INSERT INTO e2_reservation
 
INSERT INTO e2_reservation (
    r_id,
    start_date,
    end_date,
    c_id,
    v_id
) VALUES (
    400,
    to_date('16/11/2017 19:00','DD/MM/YYYY HH24:MI'),
    to_date('16/11/2017 23:00','DD/MM/YYYY HH24:MI'),
    1000,
    200
);

--COMMIT;

-----------------------------------------

-- Task 5  Create Function to calculate cost

--------------------------------------------

CREATE OR REPLACE FUNCTION CALC_RESERVATION_COST(p_res_id NUMBER)
RETURN NUMBER AS
v_cost NUMBER;
v_days NUMBER;
BEGIN
    SELECT (TRUNC(END_DATE)-TRUNC(START_DATE)+1) INTO v_days FROM E2_RESERVATION
        WHERE R_ID = p_res_id;
    
    SELECT cost_per_day INTO v_cost FROM E2_VEHICLE JOIN E2_RESERVATION ON E2_VEHICLE.V_ID = E2_RESERVATION.V_ID
        WHERE R_ID = p_res_id;
    
RETURN v_days*v_cost;
END CALC_RESERVATION_COST;

SELECT CALC_RESERVATION_COST(400) FROM DUAL;

-----------------------------------------

-- Task 6  SELECT statment with function

-----------------------------------------

--SELECT 

SELECT cost_per_day, 
        to_char(start_date,'DD/MM/YYYY')"START DATE", 
        to_char(end_date,'DD/MM/YYYY')"END DATE", 
        to_char(ROUND(CALC_RESERVATION_COST(r_id),2),'$999.99')"TOTAL RESERVATION COST" 
    FROM E2_RESERVATION JOIN E2_VEHICLE ON E2_RESERVATION.V_ID = E2_VEHICLE.V_ID
    WHERE TRUNC(START_DATE) = TRUNC(SYSDATE);


-- Here are the reults from SELECT 

--RUN IT AS A SCRIPT TO GET TEXT RESULTS
COST_PER_DAY 	START DATE 	END DATE   	TOTAL RE
------------ ---------- ---------- --------
          90 	16/11/2017 	18/11/2017  $270.00
         120 	16/11/2017 	16/11/2017  $120.00
         120 	16/11/2017 	16/11/2017  $120.00                                  

-----------------------------------------

-- Task 7 SELECT statment to list clients 

-----------------------------------------


--SELECT


SELECT
    c_id,
    f_name,
    l_name
FROM
    e2_client
WHERE
    REGEXP_COUNT(comments,'[0-9]{4}')>0;



-- Here are the reults from SELECT 

  C_ID F_NAME               L_NAME              
---------- -------------------- --------------------
      1000 John                 Smith               
      1002 aaaa                 BBBB                
      1005 a                    s                   



-----------------------------------------

-- Task 8 Create Function to calculate avg age 

-----------------------------------------


--create or replace function

CREATE OR REPLACE FUNCTION E2_CALC_AVG_AGE(p_make VARCHAR2)
    RETURN NUMBER
    AS
    
    v_avg_age NUMBER;
    BEGIN
    
    SELECT AVG(CALC_AGE(dob)) INTO v_avg_age FROM 
    e2_client JOIN e2_reservation on E2_CLIENT.c_id = e2_reservation.c_id
    JOIN e2_vehicle on e2_reservation.v_id = e2_vehicle.v_id
    WHERE UPPER(v_make) = UPPER(p_make);
        
    RETURN NVL(v_avg_age,0);
END E2_CALC_AVG_AGE;


----------------------------------------

-- BONUS Create stored procedure to list reservations

-----------------------------------------
CREATE OR REPLACE PROCEDURE E2_LIST_RESERVATIONS
    IS
    CURSOR reservation_cursor IS
        SELECT
            r_id,
            start_date,
            end_date,
            e2_vehicle.v_id
        FROM
            e2_reservation JOIN
            e2_vehicle ON e2_reservation.v_id = e2_vehicle.v_id
        WHERE TRUNC(start_date) = TRUNC(SYSDATE)+1    ;
    BEGIN
    
    FOR ec IN reservation_cursor
    LOOP
        DBMS_OUTPUT.PUT_LINE (ec.r_id||' '||TRUNC(ec.start_date)||' '||TRUNC(ec.end_date)||' '||ec.v_id);
    END LOOP;
END;

--rememebr to use SET SERVEROUT ON to get resutls :) ----
-- results from procedure


300 17-NOV-17 23-NOV-17 100




