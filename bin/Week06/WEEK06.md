# Task collection of Week 6

## Task 01:
A program is to be developed that is to simulate the process of a travel booking by a customer in a travel agency.

For this purpose the following classes are to be implemented 
__Class Date__ with the attributes: int day,int month
__Class Trip__ with the attributes: string destination and date travelDate
__Class Customer__ with the attributes: string name, string firstname, trip bookedTrip, date bookingDate.

It must be ensured that the booking date is at least 5 days before the trip date. Note: For easier implementation of this condition, all months should be set uniformly with a length of 30 days.

A trip should also be bookable by multiple customers (i.e. multiple customers for one trip).
Output a list of all bookings (clear formatting, can be freely designed). Suitable test data is to be designed independently.

Extension: Output a list that lists the booked participants per trip.

## Task 02:
A program is to be developed that simulates the management of books in a library.
For this the following classes are to be implemented
__Class Date__ with the attributes: int day,String month
__Class Book__ with the attributes: String rackNo, String title, String author 
__Class BookInventory__ with the attributes: Book book, Date returnDate

The following functions are to be programmed:
- Search book by title and output attribute standNr
Add a new book to the collection, a maximum of 3 books per title are to be be included
- Mark book as lent (done by entering a return date).
Ensure that at least one copy of a book title is available (return date is available (i.e., return date is blank).
- Output the number of books in the inventory
- List all books

No user interface programming or similar is required to solve the task.

## Task 03:
A program is to be developed that is to manage different departments. For this the following classes are to be implemented.

__Class department__ with the attributes: String department, String teamLead, Employee employees (We will use a HashSet for this), double budget
__Class employee__ with the attributes: int empId, String name, String first name
- A department should contain at least information about, b e designation, nameLtr, budget.
- A maximum of 5 employees are assigned to a department
- All attributes of a department are outputable on console
List of all departments with their attributes can be output to the console.