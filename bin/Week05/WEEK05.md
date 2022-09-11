# Task collection of Week 5

## Task 01:
Create the class shirt with the following attributes'
int shirtId,
string description,
char colorCode,
double price,
int quantity

Create a class “ShirtTest" This test class includes the method main(), in which various objects of the class shirt are created.

As entries for the f arbCode attribute,
- use b, as blue,
- r as red,
- and y as yellow.

Otherwise, the data for the individual objects are freely selectable. The test class also has the method d isplayInformation(), which displays a single object with all attributes on the console.

The color code should not be output as a code, but as a color name.
Example: color code b -> output blue.
Output all shirts on the console using the displayInformation()method.

## Task 02:
Implement a class OneDay with the private attributes
int day,year
string month

Write a test program that creates an object of the class and assigns a value to each attribute of the class.
The date entered is to be displayed on the console by retrieving the attribute contents.
To do this, provide appropriate setter/getter methods in the OneDay class.

## Task 03:
Create a class Customer with the attributes
int id,
String name,
String mailAdr
In addition, the class should have the method displayCustomer() to be able to display the customer information.
Write a program “CustomerTest" that creates and displays two different customers, for example, customer1 and customer2.
Insert the following statement before the execution part of the "Customer test" program

customer2 = customer1;
What do you find ?

In a next step, implement a method boolean isEqual() that compares whether the two customers are identical based on the entry for the eMail attribute.
Test its implementation by appropriate extensions of the "Customer Test" program.

## Task 04:
Extend the OneDay class from Task 2 by introducing a constructor OneDay(int d, String m, int y) that validates the input month:

Values Month from Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec.

In case of wrong entries, a corresponding error message will be generated. Add another method displayDate() to display the date on the console. Test the extended OneDay class by implementing a suitable test program.

## Task 05:
Create the class Truck with the following attributes
int truckId,
String identifier,
string identifier,
double loadVolume
The attribute truckId is to be automatically populated by the program with the value 1 in ascending order.

Create a class "TruckTest" This test class includes the method main(), in which various objects of the class T ruck are created.

The test class first outputs the total number of trucks.
Two trucks are then introduced with a load volume of 18.3 t and 22.7 t and the new total number of trucks is output in each case. Output the load volume of the truck with the largest load volume.
