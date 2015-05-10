# Test Plan


**Author**: Team 01

## 1 Testing Strategy

### 1.1 Overall strategy

####Unit Testing

>This will be performed by the developer and the main tester. This will be an iterative process where  a module is built, tested while in development and then the activity will be passed to the tester for immediate testing with a few sample inputs. Basic functionality should be achieved, more rigorous testing will be done in the integration stage. Each class will be considered it's own unit, or more concisely each activity or fragment will be treated as it's own unit. 

####Integration Testing

>This will be mainly performed by the main tester. The developer will do appropriate testing while developing but should try and keep the testing to a minimum. The tester will need to keep track of inputs and outputs to be used later in the regression process. The integration testing process should follow at a high level what the Class UML path lays out e.g. which classes directly involve each other. 

####Regression Testing

>This will be performed by the main tester and by the developer as a result of a failed test in any of the previous stages of testing. Inputs used in the past should be reused and see if the old outputs or new expected outputs are achieved. 

####System Testing

>System testing will be performed by the main tester each time a new unit is finalized and has passed all of the previous stages. This is really a more rigorous version of regression testing, but with all units integrated together as a single entity.


### 1.2 Test Selection

>We plan to incorporate unit, integration, regression and system testing into our testing plans. Black box methods will be the standard for each type of testing. White box methods will be utilized for methods and operations that the developer deems necessary, mainly logic, branch, or division heavy operations. The developer will need to outline where the code becomes branch heavy and from there in conjunction with the main tester, white box methods will be employed. Using white box methods for the entire project is too exhaustive for the team size.

### 1.3 Adequacy Criterion

>Functional testing will be done at each level against the UML design. This will ensure that functionality is met and that the testing process does not create a bottleneck in the development process. In the sections pointed out by the developer as logic or branch heavy, structural methods may be applied.

### 1.4 Bug Tracking

>Bugs will be sent back to the developer or developer team. They will be tracked along team communication forum. This will initiate another round of unit, integration and regression tests when the specified bug(s) are fixed. Enhancement requests that come in will be handled in largely the same method; request, design, unit-->regression tests.

### 1.5 Technology

>The developer will most likely use jUnit for their automated testing since there shouldn't be a web interface (selenium). This will make the testing iterations from unit to regression much more streamlined due to the automation and input repeatability. 

## 2 Test Cases

###Data and UI
| Purpose | Inputs | prerequisites  |  Expected Result  |  Actual Result  |  Pass/Fail   | Notes|
|:------------ |:--------------- |:------------ |:--------------- |:------------ |:--------------- | :--------------- | 
| Add a customer | firstName:str, lastname:str, zipCode:str, email:str | None | new customer is created| N/A | N/A ||
| Edit a customer | customerId:int, customer attribute to edit | an existing customer | customer is edited | N/A | N/A ||
| Not gold status | customerId:int| an existing customer with <1k transaction history | False is returned | N/A | N/A ||
| Run Transaction | customerId:int, Transaction data| an existing customer | Approval Message | N/A | N/A ||
| Run Transaction >$100 | customerId:int, Transaction data| an existing customer| Approval Message, email, reward | N/A | N/A ||
| Get reward Transaction | customerId:int, Transaction data| an existing customer with a pending reward| $10 off current purchase | N/A | N/A ||
| Check pending rewards | customerId:int| an existing customer| applicable reward | N/A | N/A |Use this to check if customer has rewards and if rewards are being used|
| Gold status | customerId:int| an existing customer with >=1k transaction history | True is returned | N/A | N/A ||
| View Transaction History | customerId:int| an existing customer with transactions | History screen is populated | N/A | N/A |This should be done towards the end, just for time purposes|
| View calendar year tran history | customerId:int| an existing customer | History screen is populated with dates | N/A | N/A | | Branch validation needed, don't forget about Java months!|


###Fucntionality/Exploratory Testing
| Purpose | prerequisites  |  Expected Result  |  Actual Result  |  Pass/Fail   | Notes|
|:------------ |:------------ |:--------------- |:------------ |:--------------- | :--------------- | 
|Test the back stack | Get multiple activities or fragments deep, validate stack is popping | backward navigation/designed nav | N/A | N/A |This is done becuase multiple UI changes can sometimes get confusing and the back button can have undesired affects. Either use nav pane for navigation or manage the stack |
|Internet Conn | What happens w/ no internet | view cust, no trans | N/A | N/A | probably check internet conn before doing any transactions or editing (think tablets) |
| Rotation | a screen | the desired affect, either rotation or not | N/A | N/A | Either prepare for rotation or lock down the rotation in the manifest |
| Performance | A measureable that defines 'good performance' | no Force Closes | N/A | N/A | Use touch and mathematical tests to drive memory usage |
| Interface Testing | N/A | each component has the desired affect | N/A | N/A | Does each button and text box behave correctly (low level) |
