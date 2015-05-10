# **Critique of Hunter's Design**

##1 Namit's comments 

- *Pros*: 
	- It covers all the talking points of the requirements, albeit difference is structure of classes from mine - which  is a subjective matter
- *Cons*: 
	- Transactions class may prove to be an additional feature into the system but again that is a subjective opinion.

##2 Ryan's comments 

- *Pros*: 
	- It looks good, and is easy to understand.
	- I think it's formatted well, and the process works.
- *Cons*: 
	- I don't know if I agree that credit card needs to roll up into transactions, but I may not understand it well enough
	- I don't think transaction and transactions need to be seperate classes.
	
##3 Alex's comments 

- *Pros*: 
	- I think it makes sense to aggregate transactions and rewards into customer
	- Good naming conventions and relationship definitions 
- *Cons*: 
	- Rewards has a lot of methods attached to it, some which might make more sense elsewhere
	- I also don't think transaction and transactions need to be separate
	
##4 Seth's comments 

- *Pros*: 
	- overall relationships work well
- *Cons*: 
	- i don't think we should hold onto credit cards. i see it as an assoc class with the paymentprocessing
	- i see rewards as a type of discount that should be connected with transaction
	- can remove transactions class
