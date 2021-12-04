DROP DATABASE IF EXISTS VETAPP;
CREATE DATABASE VETAPP; 
USE VETAPP;



DROP TABLE IF EXISTS USERS; 
CREATE TABLE USERS ( 
     User_ID			 int not null,
     First_Name			 varchar (50) not null,
     Last_Name			 varchar (50) not null,
     User_Type			 varchar (100) not null,
     UserName 			varchar (100) not null,
     Email			 varchar (100) not null,
     Phone_Number			 varchar (50) not null,
     User_Password			 varchar (50) not null,
     Start_Date			 	varchar (100) not null,
     User_Status			 varchar (50) not null,
     primary key (UserName)
);

INSERT INTO USERS (User_ID, First_Name, Last_Name, User_Type, UserName,  Email, Phone_Number, User_Password, Start_Date, User_Status)
VALUES
('1', 'Alfy', 'Boyd', 'Admin', 'alfyBoyd1', 'alfred.boyd@vet.com', '403-123-4567', 'pa', '2020-05-03 00:00:00', 'Active'),
('2', 'Charles', 'Day', 'Animal Care Attendant', 'charlesDay1', 'charles.day@vet.com', '403-987-6543', 'WoRdPaSs231', '2020-06-30 00:00:00', 'Active'),
('3', 'Eve', 'Fast', 'Animal Health Technician', 'evest1', 'eve.fast@vet.com', '403-546-9565', 'HeLlo!51', '2020-05-05 00:00:00', 'Active'),
('4', 'Georgina', 'Hill', 'Teaching Technician', 'georgina2', 'georgina.hill@vet.com', '403-489-6213', 'wOrLd123!', '2020-05-06 00:00:00', 'Active'),
('5', 'Ivan', 'Jo', 'Student', 'ivanJo1', 'ivan.jo@vet.com', '403-651-8416', 'iVanIsCoOl9!', '2020-05-07 00:00:00', 'Blocked'),
('6', 'Krity', 'Lou', 'Student', 'krityLou1', 'kristy.lou@vet.com', '403-498-6596', 'kRISTYYYY1!1', '2020-05-08 00:00:00', 'Active'),
('7', 'Alfred', 'Boyd', 'Animal Health Technician', 'alfredBoyd1', 'alfred.boyd2@vet.com', '403-111-1111', 'HeLlo!52', '2020-09-05 00:00:00', 'Active'),
('8', 'Marles', 'No', 'Animal Health Technician', 'marlesNo1', 'marles.no@vet.com', '403-222-2222', 'marles111', '2020-10-05 00:00:00', 'Active'),
('9', 'Olivia', 'Phan', 'Animal Health Technician', 'oliviaPhan1', 'olivia.phan@vet.com', '403-333-3333', 'oLIVIA456', '2020-11-05 00:00:00', 'Blocked'),
('10', 'Hacker', 'One', 'Teaching Technician', 'Instructor_1', 'hacker1@vet.com', '403-444-4444', 'pt@123', '2020-05-12 00:00:00', 'Active'),
('11', 'Hacker', 'Two', 'Admin', 'Admin_1', 'hacket2@vet.com', '403-555-5555', 'pa', '2020-05-13 00:00:00', 'Active'),
('12', 'Hacker', 'Three', 'Animal Health Technician', 'Technician', 'hacker3@vet.com', '403-666-6666', 'pe', '2020-05-14 00:00:00', 'Active');



DROP TABLE IF EXISTS REQUEST; 
CREATE TABLE REQUEST ( 
     Animal_ID			 int not null,
     Request_ID			 int not null,
     Requester_ID			 int not null,
     Request_Date			 varchar (50) not null,
     Checkout_Date			 varchar (50) ,
     Return_Date			 varchar (50) ,
     Reason			 varchar (350) ,
     Request_Status			 varchar (50) not null,
     primary key (Request_ID)
);

INSERT INTO REQUEST (Animal_ID, Request_ID, Requester_ID, Request_Date, Checkout_Date, Return_Date, Reason, Request_Status)
VALUES
('101', '1', '4', '2021-09-21 00:00:00', '2021-10-01 00:00:00', '2021-10-03 00:00:00', 'Teaching class 101', 'Approved'),
('102', '2', '10', '2021-10-31 00:00:00', '2021-10-28 00:00:00', '2021-10-28 00:00:00', 'Inspection request', 'Rejected'),
('101', '3', '4', '2021-11-05 00:00:00', '2021-11-05 00:00:00', '2021-11-12 00:00:00', 'Checking out for class 102 for one week', 'Rejected'),
('104', '4', '10', '2021-11-10 00:00:00', '2021-11-10 00:00:00', '2021-11-10 00:00:00', 'Need to check out for testing', 'Cancelled'),
('104', '5', '4', '2021-11-10 00:00:00', '2021-11-13 00:00:00', '2021-11-14 00:00:00', 'Need to check out for testing', 'Pending'),
('103', '6', '4', '2021-11-15 00:00:00', '2021-11-28 00:00:00', '2021-11-29 00:00:00', 'checkup on recovery', 'Pending'),
('103', '7', '4', '2021-11-16 00:00:00', '2021-11-25 00:00:00', '2021-11-30 00:00:00', 'Inspection request', 'Pending'),
('101', '8', '10', '2021-11-20 00:00:00', '2021-11-21 00:00:00', '2021-11-23 00:00:00', 'Checking the neck', 'Accepted'),
('102', '9', '4', '2021-11-20 00:00:00', '2021-11-21 00:00:00', '2021-11-23 00:00:00', 'Suspicious behavior', 'Accepted'),
('102', '10', '10', '2021-11-21 00:00:00', '2021-11-22 00:00:00', '2021-11-23 00:00:00', 'checking the head', 'Accepted');




DROP TABLE IF EXISTS ANIMAL; 
CREATE TABLE ANIMAL ( 
     Animal_ID			 integer not null,
     Animal_Name			 varchar (50) not null,
     Species			 varchar (50) not null,
     Breed			 varchar (50) not null,
     Tattoo_Num			 varchar (50) not null,
     City_Tattooo			 varchar (50) not null,
     Birth_Date			 varchar (50) not null,
     Sex			 varchar (50) not null,
     RFID			 varchar (50) not null,
     Microchip			 varchar (50) not null,
     Animal_Status			 varchar (50) not null, 
     Colour			 varchar (50) not null,
     Weight			double not null,
     Additional_Information			 varchar (50) not null,
     Length_Name			 integer not null,
     SearchKey_Name			 varchar (150) not null,
     primary key (Animal_ID)
);




INSERT INTO ANIMAL (Animal_ID, Animal_Name, Species, Breed, Tattoo_Num, City_Tattooo, Birth_Date, Sex, RFID, Microchip, Animal_Status, Colour, Weight, Additional_Information, Length_Name, SearchKey_Name)
VALUES
('101.0', 'Bobby', 'Dog', 'Beagle', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Available', 'Black and white', '10.1', 'nan', '5.0', 'a0-b3-c0-d0-e0-f0-g0-h0-i0-j0-k0-l0-m0-n0-o1-p0-q0-r0-s0-t0-u0-v0-w0-x0-y1-z0'),
('102.0', 'Daniel', 'Horse', 'Vanners', '564543', 'ABC Paris', '2018-08-31 00:00:00', 'F', '8987498179390.0', '5671876189197.0', 'Injured', 'Brown', '11.1', 'Sprained right hind leg. Out for 2 weeks', '6.0', 'a1-b0-c0-d1-e1-f0-g0-h0-i1-j0-k0-l1-m0-n1-o0-p0-q0-r0-s0-t0-u0-v0-w0-x0-y0-z0'),
('103.0', 'Katherine', 'Cow', 'Abigar', '981733', 'CBH India', '2018-02-29', 'M', '83612863189.0', '812381931998.0', 'Sick', 'Black', '12.1', 'Unavilable for one week. Grass only diet', '9.0', 'a1-b0-c0-d0-e2-f0-g0-h1-i1-j0-k1-l0-m0-n1-o0-p0-q0-r1-s0-t1-u0-v0-w0-x0-y0-z0'),
('104.0', 'Bobby', 'Cat', 'leopard', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Available', 'Black and white', '13.1', 'nan', '5.0', 'a0-b3-c0-d0-e0-f0-g0-h0-i0-j0-k0-l0-m0-n0-o1-p0-q0-r0-s0-t0-u0-v0-w0-x0-y1-z0'),
('2501.0', 'Satch', 'Dog', 'Beagle', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Available', 'Black and white', '14.1', 'nan', '5.0', 'a1-b0-c1-d0-e0-f0-g0-h1-i0-j0-k0-l0-m0-n0-o0-p0-q0-r0-s1-t1-u0-v0-w0-x0-y0-z0'),
('1405.0', 'Adai', 'Dog', 'Beagle', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Available', 'Black and white', '15.1', 'nan', '4.0', 'a2-b0-c0-d1-e0-f0-g0-h0-i1-j0-k0-l0-m0-n0-o0-p0-q0-r0-s0-t0-u0-v0-w0-x0-y0-z0'),
('1406.0', 'Bibbie', 'Dog', 'Beagle', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Available', 'Black and white', '16.1', 'nan', '6.0', 'a0-b3-c0-d0-e1-f0-g0-h0-i2-j0-k0-l0-m0-n0-o0-p0-q0-r0-s0-t0-u0-v0-w0-x0-y0-z0'),
('3000.0', 'Ada', 'Dog', 'Beagle', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Available', 'Black and white', '17.1', 'nan', '3.0', 'a2-b0-c0-d1-e0-f0-g0-h0-i0-j0-k0-l0-m0-n0-o0-p0-q0-r0-s0-t0-u0-v0-w0-x0-y0-z0'),
('3001.0', 'Bibbies', 'Dog', 'Beagle', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Available', 'Black and white', '18.1', 'nan', '7.0', 'a0-b3-c0-d0-e1-f0-g0-h0-i2-j0-k0-l0-m0-n0-o0-p0-q0-r0-s1-t0-u0-v0-w0-x0-y0-z0');
