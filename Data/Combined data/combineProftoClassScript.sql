DROP table IF EXISTS CombinedTable;
Create table CombinedTable as
select c.Course_number, c.Course_title, c.Units, c.Type, c.Time, c.Days, c.Instructor,
	p.Department, p.Rating_Class, p.Overall_Rating
	from  fall2020classes c
    left join AllProfessors p
    on c.Instructor = (concat(p.First_Name, ' ', p.Last_Name));