# Task collection of Week 7

## Task 01:
Create a class hierarchy that is structured as follows:
Base class Employee with the following subclasses, Manager, Engineer and Admin. In addition, you create the subclass named Director for the Manager class.

The classes have the following attributes and methods
__Class Employee__ with attributes int empId, string name, string son (means social number), double salary, constructor with arguments mitarbId, name, sId, gehalt.

__Class Engineer__ and __Class Admin__ without additional attributes.

__Class Manager__ additionally with attribute String depName() (for department name), method String getDepName(), a constructor containing all parameters needed to create an object of this type.

__Class Director__ additionally with attribute double budget, method getBudget(), a con-structure containing all parameters needed to create an object of this type.

Develop a programme EmployeeTest that creates the following instances: 
- Engineer: "Jane Schmidt", empId 101, ssn 012-34-5678, salary 120345.67.
- Manager: "Barbara Janson", empId 207, sId 054-12-2367, salary 109501.36, depName "IT Service US".
- Admin: "Bill Moser", empId 304, ssn 108-23-6509, salary 75002.34.
- Director: "Susanne Weiler", empId 12, ssn 099-45-2340, salary 120567.36, depName "Global Marketing", budget: 1000000.00.

You can add more employees of different types as you see fit!

Add a method displayEmployee() to be able to display an employee object.

Further add the methods raiseSalary(double raise) or setName(). 

Salary increases are in principle only positive and may not exceed 10%, zero values are not allowed for the method setName().
Test your scenario.
## Task 02:

This task is a continuation of the previous task 1.
In the Manager class, overwrite the method displayEmployee() of the Employee class so that the attribute depName is also passed. 

Repeat the overwriting of the method displayEmployee() in the class Director so that the attribute budget is also displayed.
Display all employees of the company.

For individual employees, change the attribute salary or name using the methods raiseSalary() or setName() and then output the employee information on the console.

## Task 03:
This task uses the already existing class hierarchy of the previous tasks 1 and 2.

Implement a programme EmployeeStocksPlan.
This programme consists only of the method amountStocks(), which returns the valid number of stock options as an integer for the respective employee according to the following rule

__Amount of entitled stocks__

| Employee          | Amount Stocks |
|-------------------|---------------|
| Director          | 1000          |
| Manager           |  100          |
| Remaining         |   10          |