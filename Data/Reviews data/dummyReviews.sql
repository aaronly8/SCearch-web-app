use fall2020schedule;
drop table if exists Review;
CREATE TABLE Review(
   UserEmail  VARCHAR(100) NOT NULL 
  ,ReviewContent  VARCHAR(10000) NOT NULL
  ,Instructor     VARCHAR(251) NOT NULL
);
Insert into Review (UserEmail, ReviewContent, Instructor)
Values ('a@usc.edu','great prof!', 'Sandra Batista'),
('b@usc.edu','Make sure to take good notes', 'Mark Redekopp'),
('c@usc.edu','Lots of homework', 'Sandra Batista'),
('d@usc.edu','Great lectures', 'Sandra Batista'),
('e@usc.edu','Hard midterms', 'Sandra Batista');

Select * from Review;