# **Critique of Namit's Design**

##1 Alex's comments 

- *Pros*: 
	- I like the Cash/Gold discounts being broken into subclasses of "Discount"
	- Good naming conventions
- *Cons*: 
	- I don't think AllTransactionsPerCustomer necessarily needs to be a class, could just use transactions
	- Missing Cardholder from CreditCard? (Could be intentionally using customer as cardholder)

##2 Ryan's comments 

- *Pros*: 
	- Makes sense, easy to read, attributes and operators are named well
	- Flows well, if I had no idea about how the system works, this would explain it well.
- *Cons*: 
	- Hard coded values
	- I don't think the transaction and transaction history's need to be two different classes
	
##3 Hunter's comments 

- *Pros*: 
	- Easy to understand flow
	- Easy to understand what variables and methods do
- *Cons*: 
	- no swipe credit card method
	- lack of money utility class
	
##4 Seth's comments 

- *Pros*: 
	- i like the breakdown of the discounts
	- i like the transaction as an association class
- *Cons*: 
	- alltransactions class can be removed
	- credit card should be associated with transaction
