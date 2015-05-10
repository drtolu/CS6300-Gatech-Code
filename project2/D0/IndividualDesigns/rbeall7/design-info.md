###Abstract
 > The Stall Manager (SM) is the main interface to all other classes. It has the ability to view and add all customer items (Item Class), reward status (Reward Class), reward history and transaction history (Transaction Class). <br><br>The SM will inititate a new transaction with the transaction class. The transaction class will aggregate the items selected for purhcase, check for rewards and statuses, and handle the interactions with card swipe and card processor APIs. If the transaction is successful, the transaction class then handles the post transaction customer reward logic and communication. This inlcudes updating the customers status, and applying new rewards to the customer.  The customer, transactions, and rewards are all linked via the Customer Class attribute customerId. Because of this linkage, it's trivial to view the customers previous transactions and rewards.


 **Allmost all attributes will be private, those attributes are accessed via getters and setters which won't be covered**
****
<br/>
###Stall Manager
| Class Name          | Special Type|
|:------------ |:---------------:| 
| <H5>Stall Manager</H5>||


| Attribute Name          | Type |
|:------------ |:---------------| 
|- loggedIn | int|


| Operations | Description |
|:--------------- |:-------------|
|- viewItems()| Views all items available |
|- addItem()| interfaces with Item.addItem() method |
|- editItem()| interfaces with Item.edItem() method |
|- viewCustHistory()| Pulls all transactions/rewards for customer |
|- viewCustRewards()| Views customers current rewards |
|- addCustomer()| interfaces with Customer.addCustomer() method |
|- editCustomer()| interfaces with Customer.editCustomer() method|
|- viewCustomer()| view customer |
|- initiateTransaction()| Starts new transaction; adds items, and process card |

> This class is where the user initiates all of the different actions. It has an assocition to the Customer class, Items Class, Reward Class and Transaciton Class. It can View and Edit many attributes in these classes. 

****
<br/>
###Customer
| Class Name          | Special Type|
|:------------ |:---------------:| 
| <H5>Customer</H5> ||

| Attribute Name          | Type|
| :------------ |:---------------| 
|- customerId |int|
|- firstName| string|
|- lastName| string|
|- email| string|
|- zip| int|
|- status| int=0|

| Operations | Description |
|:--------------- |:-------------|
|+ editCustomer(int: id, string: firstName...)| edits an item|
|+ addCustomer(string: firstName, string: firstName...)| adds new cust (id is auto)|
|+ viewCustomer(int: id) : Customer | interface to view a specific Customer |

> This class is connected to almost all classes in the diagram via it's customerId attribute. This is how rewards, and transactions are connected to each other (customerId) for historical and tracking purposes. Each customer can have many transactions and rewards. (elipses are used in place of all trivial params, which in this case are basically any attribute). When status is updated to 1, customer is Gold

****

<br/>
###Transaction
| Class Name          | Special Type|
|:------------ |:---------------:| 
| <H5>Transaction</H5> ||

| Attribute Name          | Type|
|:------------ |:---------------:| 
|- id| int|
|- customerId| int|
|- date| date|
|- item| Item|
|- rewardId | int=0|
|- authorizationId | int=-1|


| Operations                      | Description |
|:--------------------------------|:-------------|
|- isTotalAbove100()| Checks if total>100 for reward purposes|
|- isAggPurchAbove1k()| If history>1k updateCustomerStatus() |
|- checkCustStatus()| check if customer already gold status| 
|- updateCustStatus()| edit customer to update customer status to gold|
|- sendEmail()| send email basedon different situations|
|- tranTotal()| create a total for current tran. Used for interfacing with card APIs|
|+ newTran()| process method to get items, checks if rewards are avail, card process, then post tran processes|
|+ getHistory(int: customerId)| returns all transactions for a customer|

> This class handles the purchases of items, the application and deduction of rewards, and finally the interactions with the payment method (card APIs). This class has a lot of responsibility due to the **events** which take place in this class. When a transaction is initiated, this event is the perfect time to check if there are any rewards for the customer, if the customers status should change to Gold, as well as handle the Card activity.

****
<br/>
###Item 
| Class Name          | Special Type|
|:------------ |:---------------:| 
| <H5>Item</H5>||

| Attribute Name          | Type|
| :------------ |:---------------| 
|- id| int|
|- name| string|
|- amount| float|

| Operations | Description |
|:--------------- |:-------------|
|+ editItem(int: id, string: name, float: amount)| edits an item|
|+ addItem(string: name, float: amount)| adds new item (id is auto)|
|+ viewItem(int: id) : Item | interface to view a specific item |

> This is a simple class that holds the items. Each transaction has at least 1 item and can consume many items, thus a transaction 'uses an Item'.

****
<br/>
###CardSwipe
| Class Name          | Special Type|
|:------------ |:---------------:| 
| <H5>CardSwipe</H5>|Utility|

| Attribute Name          | Type|
|:------------ |:---------------| 
|- name | string|
|- number| int|
|- expiration| date|
|- securityCode| int|

> The CardSwipe handles the card attributes, which it receives when the swype is initiated by the hardware. It then passes that information a long with info from the transaction to the Card Processor API, which then in turn hands an authorization Id back to the transaction

****
<br/>
###CardProcessor
| Class Name          | Special Type|
|:------------ |:---------------:| 
| <H5>CardProcessor</H5>|Association|

| Operations | Description |
|:--------------- |:-------------|
|+ getAuthorization(CardSwipe: card, float: amount): int| interfaces with processor for an authorization code|

> This is the interface with the 3rd party card payment processor. A card object along with an amount is passed through this class which handles the authorization with the process and returns an authorization id or -1 for a failure.

****
<br/>
###Reward
| Class Name          | Special Type|
|:------------ |:---------------:| 
| <H5>Reward</H5>||

| Attribute Name          | Type|
|------------ |---------------| 
| - rewardId | int |
| - customerId | int |
| - amount | float|
| - dateCreated | date |

| Operations | Description |
|:--------------- |:-------------|
| + addReward(int: customerId, float: amount)| adds or aggregates customer rewards | 
| + getReward(int: customerId) : float: amount | returns unused reward amount for  customer | 
| + reedemReward(int: customerId, float: amount)| decrements from cust reward total, and marks redeemed |

> Reward IDs are linked to their tranactions via rewardId, so that rewards are able to be viwed in transaction history for each customer. This would also help the Stall Manager be able to track when and on what rewards were used.

****

