DROP DATABASE IF EXISTS VETAPP;
CREATE DATABASE VETAPP; 
USE VETAPP;


DROP TABLE IF EXISTS USERS; 
CREATE TABLE USERS ( 
     User_ID			 int not null,
     First_Name			 varchar (50) not null,
     Last_Name			 varchar (50) not null,
     User_Type			 varchar (100) not null,
     Username 			varchar (100) not null,
     Email			 varchar (100) not null,
     Phone_Number			 varchar (50) not null,
     User_Password			 varchar (50) not null,
     Start_Date			 	varchar (100) not null,
     User_Status			 varchar (50) not null,
     primary key (User_ID)
);

INSERT INTO USERS (User_ID, First_Name, Last_Name, User_Type, UserName,  Email, Phone_Number, User_Password, Start_Date, User_Status)
VALUES
(1, 'Alfy', 'Boyd', 'Admin', 'alfyBoyd1', 'alfred.boyd@vet.com', '403-123-4567', 'pa', '2020-05-03 00:00:00', 'Active'),
(2, 'Charles', 'Day', 'Animal Care Attendant', 'charlesDay1', 'charles.day@vet.com', '403-987-6543', 'WoRdPaSs231', '2020-06-30 00:00:00', 'Active'),
(3, 'Eve', 'Fast', 'Animal Health Technician', 'evest1', 'eve.fast@vet.com', '403-546-9565', 'HeLlo!51', '2020-05-05 00:00:00', 'Active'),
(4, 'Georgina', 'Hill', 'Teaching Technician', 'georgina2', 'georgina.hill@vet.com', '403-489-6213', 'wOrLd123!', '2020-05-06 00:00:00', 'Active'),
(5, 'Ivan', 'Jo', 'Student', 'ivanJo1', 'ivan.jo@vet.com', '403-651-8416', 'iVanIsCoOl9!', '2020-05-07 00:00:00', 'Blocked'),
(6, 'Krity', 'Lou', 'Student', 'krityLou1', 'kristy.lou@vet.com', '403-498-6596', 'kRISTYYYY1!1', '2020-05-08 00:00:00', 'Active'),
(7, 'Alfred', 'Boyd', 'Animal Health Technician', 'alfredBoyd1', 'alfred.boyd2@vet.com', '403-111-1111', 'HeLlo!52', '2020-09-05 00:00:00', 'Active'),
(8, 'Marles', 'No', 'Animal Health Technician', 'marlesNo1', 'marles.no@vet.com', '403-222-2222', 'marles111', '2020-10-05 00:00:00', 'Active'),
(9, 'Olivia', 'Phan', 'Animal Health Technician', 'oliviaPhan1', 'olivia.phan@vet.com', '403-333-3333', 'oLIVIA456', '2020-11-05 00:00:00', 'Blocked'),
(10, 'Hacker', 'One', 'Teaching Technician', 'Instructor_1', 'hacker1@vet.com', '403-444-4444', 'pt@123', '2020-05-12 00:00:00', 'Active'),
(11, 'Hacker', 'Two', 'Admin', 'Admin_1', 'hacket2@vet.com', '403-555-5555', 'pa', '2020-05-13 00:00:00', 'Active'),
(12, 'Hacker', 'Three', 'Animal Health Technician', 'Technician', 'hacker3@vet.com', '403-666-6666', 'pe', '2020-05-14 00:00:00', 'Active');


DROP TABLE IF EXISTS ANIMAL; 
CREATE TABLE ANIMAL ( 
     Animal_ID			 integer not null,
     Animal_Name			 varchar (50) not null,
     Species			 varchar (50) not null,
     Breed			 varchar (50) not null,
     Tattoo_Num			 varchar (50) not null,
     City_Tattoo			 varchar (50) not null,
     Birth_Date			 varchar (50) not null,
     Sex			 varchar (50) not null,
     RFID			 varchar (50) not null,
     Microchip			 varchar (50) not null,
	 Availability 		varchar(50) not null,
     Animal_Status			 varchar (50) not null, 
     Colour			 varchar (50) not null,
     Additional_Information			 varchar (50) not null,
     Length_Name			 integer not null,
     SearchKey_Name			 varchar (150) not null,
     primary key (Animal_ID)
);

INSERT INTO ANIMAL (Animal_ID, Animal_Name, Species, Breed, Tattoo_Num, City_Tattoo, Birth_Date, Sex, RFID, Microchip, Availability, Animal_Status, Colour, Additional_Information, Length_Name, SearchKey_Name )
VALUES
(101, 'Bobby', 'Dog', 'Beagle', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Available', 'Healthy', 'Black and white', '', '5.0', 'a0-b3-c0-d0-e0-f0-g0-h0-i0-j0-k0-l0-m0-n0-o1-p0-q0-r0-s0-t0-u0-v0-w0-x0-y1-z0'),
(102, 'Daniel', 'Horse', 'Vanners', '564543', 'ABC Paris', '2018-08-31 00:00:00', 'F', '8987498179390.0', '5671876189197.0', 'Unavailable', 'Injured', 'Brown', 'Sprained right hind leg. Out for 2 weeks', '6.0', 'a1-b0-c0-d1-e1-f0-g0-h0-i1-j0-k0-l1-m0-n1-o0-p0-q0-r0-s0-t0-u0-v0-w0-x0-y0-z0'),
(103, 'Katherine', 'Cow', 'Abigar', '981733', 'CBH India', '2018-02-29', 'M', '83612863189.0', '812381931998.0', 'Available', 'Sick', 'Black', 'Unavilable for one week. Grass only diet', '9.0', 'a1-b0-c0-d0-e2-f0-g0-h1-i1-j0-k1-l0-m0-n1-o0-p0-q0-r1-s0-t1-u0-v0-w0-x0-y0-z0'),
(104, 'Bobby', 'Cat', 'leopard', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Unavailable', 'Passed', 'Black and white', '', '5.0', 'a0-b3-c0-d0-e0-f0-g0-h0-i0-j0-k0-l0-m0-n0-o1-p0-q0-r0-s0-t0-u0-v0-w0-x0-y1-z0'),
(2501, 'Satch', 'Dog', 'Beagle', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Available', 'Healthy', 'Black and white', '', '5.0', 'a1-b0-c1-d0-e0-f0-g0-h1-i0-j0-k0-l0-m0-n0-o0-p0-q0-r0-s1-t1-u0-v0-w0-x0-y0-z0'),
(1405, 'Adai', 'Dog', 'Beagle', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Available', 'Healthy', 'Black and white', '', '4.0', 'a2-b0-c0-d1-e0-f0-g0-h0-i1-j0-k0-l0-m0-n0-o0-p0-q0-r0-s0-t0-u0-v0-w0-x0-y0-z0'),
(1406, 'Bibbie', 'Dog', 'Beagle', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Unavailable', 'Healthy', 'Black and white', '', '6.0', 'a0-b3-c0-d0-e1-f0-g0-h0-i2-j0-k0-l0-m0-n0-o0-p0-q0-r0-s0-t0-u0-v0-w0-x0-y0-z0'),
(3000, 'Ada', 'Dog', 'Beagle', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Available', 'Healthy', 'Black and white', '', '3.0', 'a2-b0-c0-d1-e0-f0-g0-h0-i0-j0-k0-l0-m0-n0-o0-p0-q0-r0-s0-t0-u0-v0-w0-x0-y0-z0'),
(3001, 'Bibbies', 'Dog', 'Beagle', '234234', 'HOC London', '2018-08-15 00:00:00', 'M', '197839178371.0', '176387613813.0', 'Unavailable', 'Healthy', 'Black and white', '', '7.0', 'a0-b3-c0-d0-e1-f0-g0-h0-i2-j0-k0-l0-m0-n0-o0-p0-q0-r0-s1-t0-u0-v0-w0-x0-y0-z0');


DROP TABLE IF EXISTS COMMENTS; 
CREATE TABLE COMMENTS ( 
     Animal_ID			 int not null,
     Comment_ID			int not null,
     Upload_Time			 varchar (50) not null,
     User_ID			 int not null,
     Message			 varchar (50) not null,
     primary key (Comment_ID),
     foreign key (Animal_ID) references ANIMAL(Animal_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE,
     foreign key (User_ID) references USERS(User_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE
);

INSERT INTO COMMENTS (Animal_ID, Comment_ID, Upload_Time, User_ID, Message)
VALUES
(102, 1, '2021-11-17 09:07:00', 2, 'Can someone check on this horse sometime today?'),
('102', '2', '2021-11-17 10:35:00', '3', 'Yes, I will be doing a checkup at 11AM.'),
('101', '3', '2021-11-17 10:50:00', '4', 'Kristy, please accompany me to a checkup at noon.'),
('101', '4', '2021-11-20 15:00:00', '5', 'Looking good.'),
('101', '5', '2021-11-21 10:00:00', '6', 'Can someone please check on the foot?');


DROP TABLE IF EXISTS PHOTOS; 
CREATE TABLE PHOTOS ( 
     Animal_ID			 integer not null,
     Photo_ID			 integer not null,
     File_Path			 varchar (150) not null,
     User_ID			integer not null,
     Upload_Date			 varchar (100) not null,
     primary key (Photo_ID),
     foreign key (Animal_ID) references ANIMAL(Animal_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE,
     foreign key (User_ID) references USERS(User_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE
);

INSERT INTO PHOTOS (Animal_ID, Photo_ID, File_Path, User_ID, Upload_Date)
VALUES
('102', '1', 'src/resources/imag2.png', '2', '2020-12-14 00:00:00'),
('101', '2', 'src/resources/imag16.png', '2', '2020-12-22 00:00:00'),
('102', '3', 'src/resources/imag19.png', '2', '2021-01-20 00:00:00'),
('101', '4', 'src/resources/imag25.png', '10', '2021-10-15 00:00:00');

DROP TABLE IF EXISTS REMINDERS; 
CREATE TABLE REMINDERS ( 
     Animal_ID			 integer not null,
     Reminder_ID			 integer not null,
     Reminder_Status			 varchar (50) not null,
     Due_Date			 varchar (50) not null,
     Date_Performed			 varchar (50) not null,
     Author_ID			 integer not null,
     Notes			 varchar (50) not null,
     primary key (Reminder_ID),
     foreign key (Animal_ID) references ANIMAL(Animal_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE,
     foreign key (Author_ID) references USERS(User_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE
);

INSERT INTO REMINDERS (Animal_ID, Reminder_ID, Reminder_Status, Due_Date, Date_Performed, Author_ID, Notes)
VALUES
('101', '1', 'Incomplete', '2021-11-21 00:00:00', 'NaT', '1', 'Need to cut hair before end of the month'),
('102', '2', 'Complete', '2021-10-31 00:00:00', '2021-10-28 00:00:00', '1', 'Moved to stable 2 in preparation for winter'),
('101', '3', 'Complete', '2021-11-05 00:00:00', '2021-11-05 00:00:00', '2', 'Changing diet so no treats allowed');



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
     primary key (Request_ID),
     foreign key (Animal_ID) references ANIMAL(Animal_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE,
     foreign key (Requester_ID) references USERS(User_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE
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




DROP TABLE IF EXISTS DIAGNOSIS; 
CREATE TABLE DIAGNOSIS ( 
     Diagnosis_ID			int not null,
     Diagnosis_Date			 varchar (50) not null,
     Diagnosis			 varchar (50) not null,
     Diagnosis_Description			 varchar (500) not null,
     Diagnosis_Status			 varchar (50) not null,
     User_ID			 int not null,
     Animal_ID			 int not null,
     primary key (Diagnosis_ID),
     foreign key (Animal_ID) references ANIMAL(Animal_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE,
     foreign key (User_ID) references USERS(User_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE
);


INSERT INTO DIAGNOSIS (Diagnosis_ID, Diagnosis_Date, Diagnosis, Diagnosis_Description, Diagnosis_Status, User_ID, Animal_ID)
VALUES
('1', '2020-03-08 00:00:00', 'Twisted ankle', 'Dog in pain. Will be out minimum one week. Will heal naturally. ', 'Complete', '7', '101'),
('4', '2021-04-13 00:00:00', 'Chipped hoof', 'Chip in the two front hooves. Keep casts on until fully healed', 'Ongoing', '3', '102'),
('5', '2021-09-25 00:00:00', 'Sprained ankle', 'Serious sprain in right hind ankle. Will be out minimum two weeks.', 'Ongoing', '9', '102'),
('8', '2021-10-05 00:00:00', 'Flu', 'Keep in separate barn until fully healed. Medication prescribed in treatments.', 'Ongoing', '8', '103'),
('9', '2021-10-12 00:00:00', 'Flu', 'Feeling better, still needs to stay separated from rest of cows. Medication once a day', 'Ongoing', '3', '103');


DROP TABLE IF EXISTS TREATMENT; 
CREATE TABLE TREATMENT ( 
     Treatment_ID			int not null,
     Treatment_Date			 varchar (50) not null,
     Treatment			 varchar (50) not null,
     Treatment_Description			 varchar (500) not null,
     Treatment_Status			 varchar (50) not null,
     User_ID			 int not null,
     Animal_ID			 int not null,
     primary key (Treatment_ID),
     foreign key (Animal_ID) references ANIMAL(Animal_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE,
     foreign key (User_ID) references USERS(User_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE
);



INSERT INTO TREATMENT (Treatment_ID, Treatment_Date, Treatment, Treatment_Description, Treatment_Status, User_ID, Animal_ID)
VALUES
('1', '2020-03-08 00:00:00', 'Cone', 'Keep cone on until fleas are completely gone.', 'Complete', '7', '101'),
('4', '2021-04-13 00:00:00', 'Cast', 'Keep cast on until hooves are fully healed.', 'Ongoing', '3', '102'),
('5', '2021-09-25 00:00:00', 'Bandaging', 'Replace bandage daily after cleaning.', 'Ongoing', '9', '102'),
('8', '2021-10-05 00:00:00', 'Flu medicine', 'Twice daily, once in AM, once in PM. 8 hours apart.', 'Ongoing', '8', '103'),
('9', '2021-10-12 00:00:00', 'Flu medicine', 'Once daily after morning meal.', 'Ongoing', '3', '103');


DROP TABLE IF EXISTS WEIGHT_HISTORY; 
CREATE TABLE WEIGHT_HISTORY ( 
     Animal_ID			int not null,
     Date_Recorded		date,
     Weight				double,
     User_ID			int not null,
     primary key (Date_Recorded, Animal_ID),
     foreign key (Animal_ID) references ANIMAL(Animal_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE,
     foreign key (User_ID) references USERS(User_ID) 
     ON UPDATE CASCADE
     ON DELETE CASCADE
);

INSERT INTO WEIGHT_HISTORY(Animal_ID, Date_Recorded, Weight, User_ID)
VALUES
(103, DATE(20201001), 800.2, 1),
(103, DATE(20201101), 805.1, 1),
(103, DATE(20201201), 810.1, 1),
(103, DATE(20210101), 805.5, 2),
(103, DATE(20210201), 820.9, 1),
(103, DATE(20210301), 805.5, 5),
(103, DATE(20210401), 800.5, 1),
(102, DATE(20201001), 400.4, 4),
(102, DATE(20201101), 409.3, 1),
(102, DATE(20201201), 420.6, 3),
(102, DATE(20210101), 415.7, 1),
(102, DATE(20210201), 410.1, 2),
(102, DATE(20210301), 406.0, 1),
(102, DATE(20210401), 402.9, 2);