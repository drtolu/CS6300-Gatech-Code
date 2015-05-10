Classes and Relationships

* Each customer has many transactions
* The association between a customer and a transaction is through the running of a credit card
* A transaction can have multiple discounts
* There is a Reward discount and a Gold Status discount
* After a transaction is created, the customer either has or does not have a reward discount

Attributes

* The discounts have their amounts hard coded
* The customer has a boolean that shows whether or not the gold status discount should be applied to that transaction
* The customer uses their transactions to check if they're in the gold status

Operations

* The customer can display all their transactions with the assiciation between the two
* The transaction sends the emails notifying rewards when either they have transaction amounts YTD totaling over $1000, changing them to gold status, or the single transaction was over $100, giving them $10 off next time
