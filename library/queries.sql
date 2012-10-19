select d.Dname,d.Dnumber,count(*) as no_of_employees,sum(e.Salary) as sum_salary from Employee e,Department d where d.Dnumber = e.Dno group by d.Dnumber,d.Dname ;

select p.Pname,p.Pnumber,count(*) no_of_employees ,sum(w.Hours) as total_hours from project p,works_on w where w.Pno = p.Pnumber group by  p.Pname,p.Pnumber;

select e.Ssn,e.Fname,e.Lname,p.Pnumber,p.Pname,w.Hours from Employee e,Project p,works_on w where e.Dno = p.Dnum and p.Pnumber = w.Pno and e.Dno = 8;

select d.Dname,d.Dnumber from Department d,Employee e where d.Dnumber = e.Dno group by d.Dnumber,d.Dname having count(*) > 5;

select e.Lname,e.Fname from Employee e,Works_on w where e.ssn = w.essn and w.hours > 40 ;

select e.Lname,e.Fname,s.Lname,s.Fname from Employee e,Employee s where e.ssn = s.super_ssn and e.Dno = 8;

select e.ssn,e.Lname,e.Fname from Employee e,works_on w where e.ssn = w.essn and w.Pno = 10 
and EXISTS (select 1 from Employee e,works_on w where e.ssn = w.essn and w.Pno = 20);


