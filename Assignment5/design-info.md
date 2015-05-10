#                                                      Design Analysis

##Class StallManager
>This class acts as an entry into the system. The stall manager contains member functions which are capable of adding and editing a customer.
>The StallManager is also capable of intiating a transaction for a given customer whose details are already present in the system. 
>ListAllPreviousTransactions() is a method which provides a history of all the transactions which have been performed by a particular customer who is uniquely identified using a CustomerID.

##Class Customer
>This class contains all the customer specific data such as his name, email, zipcode. The class also specifies whether this customer is a gold status customer or not and what is the total amount of cash discount available for this customer to apply for his/her next transaction.
>Each customer is uniquely identified using a CustomerID.

##Class CreditCard
>This class contains the credit card specific information for a customer. It is assumed that a customer can have multiple credit cards on file.

##Class Transaction
>This is an association class between the StallManager and Customer. Whenever a StallManager intiates a transaction for a particular customer, Transaction class computes the cash discount, gold status discount and ultimately the Billing amount for an indivdual customer. 
>It then communicates with the AllTransactionsPerCustomer class (explained below) to store this transaction's data internally (for book-keeping) and notify the user of any reward activation such as cash discount or gold status if necessary.
>The class also interfaces with Payment Processing server to charge the customer's credit card for the purchase.

##Class AllTransactionsPerCustomer
>This class stores all the transactional level data for each transaction in an internal memory ( e.g. database) everytime 'Class Transaction' performs a transaction for an individual customer. If a particular transaction resulted in activation of cash discount or gold status, internal data structures are updated and an email is sent to the customer notifying the same. 
>The other aspect of this class is to deliver an aggregation of all historic transactions which have been performed by a particular customer to 'Class StallManager'.

##Class Discount
Each individual customer has unique discount levels activated for themselves. This class provides a generic way to determine which type of discounts this system offers.
