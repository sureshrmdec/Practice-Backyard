Q: Given a table for customer(customer ID, name) and table for order(order id, customerID)
   find the customers who have not ordered any product yet.

Sol: Select C.name from Customer C
     where C.ID NOT IN (Select O.customerID) from Order O;

     Select C.name from Customer C
     LEFT OUTER JOIN Order O on O.customerID = C.customerID
     where O.customerID = null;
