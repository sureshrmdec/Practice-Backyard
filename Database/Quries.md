Q: Given a table for customer(customer ID, name) and table for order(order id, customerID)
   find the customers who have not ordered any product yet.

Sol: Select C.name from Customer C
     where C.ID NOT IN (Select O.customerID) from Order O;

     Select C.name from Customer C
     LEFT OUTER JOIN Order O on O.customerID = C.customerID
     where O.customerID = null;


Q: SQL Query to find second highest salary of employee ?
Sol : Using subquery :
      Select MAX(salary) from Employee where Salary NOT IN (select MAX(Salary) from employee);

Q: SQL query to find Max salary from each department
A: Select DeptId, MAX(Salary) from Employee GROUP BY DeptID;

Q: Select employee who are also manager ?
A: Select e.name , m.name from Employee e, Employee m where e.mgr_id = m.mgr_id;
