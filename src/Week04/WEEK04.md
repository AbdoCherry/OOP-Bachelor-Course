# Task collection of Week 4

## Task 01:
Write a program that reads in a string.
From this string, filter out the part between the two brackets ( and )
and output it to the console. Note possible cases in the string like "a)(avc)”.

If the two brackets do not occur in the string, then the empty string "" must be output.

## Task 02:
Write a program that reads account balances from the file "CheckingAccounts.txt" into an array and outputs all account balances over 100€.

As preparation, put the file “CheckingAccounts.txt" on your computer or project folder.

## Task 03:
Write a program that displays the account balances read from the file "CheckingAccounts.txt" into an array as a histogram (one * corresponds to 10 Euro).

What is a histogram ? For each account balance, * are displayed in one line. The number of * is defined by the amount of the account balance.

As a solution variant, try to introduce a method that realizes the histogram output.

## Task 04:
First, implement a program that examines whether a text entered matches a given user name.
Extend the existing program so that the user is prompted to enter a password.
This password will only be accepted if it is at least 8 characters long.
This requirement specifies a minimum security level of the password, which is indicated to the user by the output of the security level with only one * (Security level: *).

Extend your program with the following features:
The user should have the option to increase the security level to level 2 or 3 (Display -> Security level ** or ***).

- Security level 2 is reached when one character in the password is a capital letter and one character is a number.

- Security level 3 is reached when the first character in the password is also an uppercase letter and one character is a special character.

Only then should the password be accepted by the program with a corresponding output.

## Task 05:
Write a program that reads the file "Textpassage.txt”.

Output all words and their number, all numbers and their number as well as all special characters and their number and display the frequency of the words, numbers and special characters as a histogram (frequency defines number *).

It is a good idea to program the display form "Histogram" as a method.
In preparation, save the file "Textpassage.txt" on your computer.

## Task 06:
Write again a simple program for text encryption.
For this purpose, each letter of a message is to be shifted by a fixed value n according to the following examples:

- For example, if n=2, A is shifted to C, B is shifted to D, and Z is shifted to B.
- Digits and special characters are not shifted.
- Input parameters are the word to be encoded and the number n which specifies the shift.

Both parameters are now passed as transfer parameters in the form of command line arguments when the program is called.

## Task 07:
A recurring task is the import of so-called "comma separated values" (.csv), as they can be exported for example from spreadsheet programs (Excel or similar), from calendar programs or also from databases. These files usually have the following form:

Order volume;Discount;Net volume;Currency;
1003,45;1,5;;Euro; 12500;2,25;;Euro; 26011;3,1;;€; 6035,44;1,9;;€; 71500;7,6;;Euro

Not all fields must be occupied per line (e.g. the Net Volume field), but the number of fields is always the same.
Some fields contain numbers (e.g. Order volume double) , some character strings (e.g. "€" or "Euro" in the Currency field).

In csv format the header is not obligatory. But it is used to name the columns.
Write a program that reads the file "Orderlist.csv" (see above) and outputs it in the same form to the standard output, but additionally with the
calculated amount at the end of the term and the uniform representation of the currency as EUR.

As a preparation, store the file "Ordlerlist.csv" on your computer or store it in your project folder.