use use fall2020schedule;
drop table if exists Review; 
create table Review (
  id INT primary key AUTO_INCREMENT, 
  userEmail varchar(100), 
  class varchar(100), 
  prof varchar(50),
  major varchar (50),
  yearUpload INT,
  reviewContent varchar(10000)
  );