SELECT * FROM REQUEST AS R2;
SELECT * FROM REQUEST AS R2, Users as U WHERE R2.Requester_ID = U.User_ID;
SELECT R2.Animal_ID, R2.Request_ID, R2.Requester_ID, R2.Request_Date, R2.Checkout_Date, R2.Return_Date, 
R2.Reason, R2.Request_Status, U.First_Name, U.Last_Name,
A.Animal_Name, A.Species  FROM REQUEST AS R2, Users as U, ANIMAL AS A  WHERE R2.Requester_ID = U.User_ID AND R2.Animal_ID=A.Animal_ID 
ORDER BY R2.Request_ID ASC;

SELECT * FROM REQUEST AS R2 WHERE R2.Requester_ID = '10';

SELECT R2.Animal_ID, R2.Request_ID, R2.Requester_ID, R2.Request_Date, R2.Checkout_Date, R2.Return_Date, 
R2.Reason, R2.Request_Status, U.First_Name, U.Last_Name,
A.Animal_Name, A.Species  FROM REQUEST AS R2, Users as U, ANIMAL AS A  WHERE R2.Requester_ID = U.User_ID AND R2.Animal_ID=A.Animal_ID AND R2.Requester_ID = '10'
ORDER BY R2.Request_ID ASC;


