# Task collection of Week 8

## Task 01:
You are entrusted with a project to implement a banking software. The creation of term deposit accounts (also called deposit or time deposit accounts) is to be supported.

With this type of account, withdrawals are only possible when a certain maturity date is reached.
To do this, create the two classes Customer and Deposit Account, which are described here in a first draft.

The __Customer Class__ includes the attributes name, firstname, customerNo, customerPage (all of type String).
The Class shall comprise the methods
- addAccount() --- add an account
- getAccount() --- retrieve information about account.

The __Class DepositAccount__ includes the following attributes depositBalance (of type double), investmentDate and maturityDate (all of type String). It also has the methods withdraw(), deposit().
It must be possible to display the text "term deposit account".
Create a Customer Report programme that displays all customers and their accounts in a report. 

The structure of the report is something like this:
__CUSTOMER REPORT:__
Customer: Meier, Emil
- Term deposit account1 Maturity date1 Deposit amount1 Client: Yildirm, Mustafa
- Term deposit account1 Maturity date2 Investment amount2 Term deposit account2 Maturity date3 Investment amount3

## Task 02:
The bank board is so enthusiastic about the result achieved in task 1 that it is immediately decided to extend the software to current accounts as well.
It is worth considering introducing a general account class. Try this on your own.

The class current account has the additional attribute draftLimit (of type double) in relation to the deposit account. The attribute draftLimit is set inital equal to 0.
When debits are made, it must be checked that the overdraft limit is not exceeded.
The display of the text “Checking Account" must be possible.
Implement a test programme that creates time deposit accounts and current accounts for different customers and uses the methods withdraw() and deposit().
With the help of the method report(), a customer report with the following structure can be generated at any time

__CUSTOMER REPORT__
Customer: Meier, Emil
- Term deposit account1 Maturity date1 Deposit amount1
- Checking Account Current account balance 
Customer: Yildirm, Mustafa
- Term deposit account1 Maturity date2 Investment amount2 
- Term deposit account2 Maturity date3 Investment amount3 
- Checking Account account currentAccount balance

## Task 03:
Write a programme that is to support the customer and supplier relationships of a company.

This requires the __Classes Customer__ and __Supplier__ with the attributes (all of type String):
- Customer: Name, Address, OrderVolumeInFiscalYear.
- Supplier: Name, Address, OrderVolumeInFiscalYear

In order to be able to implement the payment flows with customers or suppliers, their account data is required, for which the new concept BankAccout with the attributes iban and nameBank (both of type String) is to be introduced.

Note: Logically, there is no inheritance relationship between the three concepts. But nevertheless it must be guaranteed that both classes customer and supplier refer to the concept BankAccout with regard to the account data.