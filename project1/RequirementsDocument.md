# **Requirements Document -- Team 01**

## 1 User Requirements
### 1.1 Software Interfaces
  
External systems/libraries that our program will interact with: 
    
* Linux terminal
* Java
* The JVM
* Files containing text data
* Java text processing libraries
* Java standard libraries

### 1.2 User Interfaces


The user will interact with the software using a command line interface. To run the software, the student will provide the path to their text file (input essay) and any additional flags for optional parameters. 

The sample command below illustrates a user specifying commas as the sole sentence delimiter: 

`java calcAvgWordsPerSentence C:\Users\user1\essay1.txt -d ,`

If the user does not type '-d ,' in the command line, the default delimiters of ".?!" will be used. 

The sample command below illustrates a user specifying a custom minimum word length of 5 characters:

`java calcAvgWordsPerSentence C:\Users\user1\essay1.txt -l 5`

If the user does not add the '-l' flag, the minimum word length will default to 3 characters.

The program will compute the average number of words per sentence in the specified file, and print out the result in the command line.

If there are issues while executing the program, user friendly error messages would be displayed on the command line. Sample error messages are as follows: 

* "The file name you have entered does not exist. Please check your path."

* "This is a blank file. Please enter a few sentences."


### 1.3 User Characteristics


It is assumed that the users of the end product are students who are attending college. Their experience with computers could range from no technical experience to highly proficient. Some students in the class could be computer science majors and some of them could have never worked with a programming language before. The software we are building should be user friendly so that students with rudimentary technical experience would not feel overwhelmed while using it.

## 2 System Requirements   

### 2.1 Functional Requirements

* It should calculate the average sentence length rounded down to the nearest integer from a provided txt file.
* It should output the result to the command line.
* It should be executable from the command line.
* It should process raw txt files.
* It should require an essay file path via a command line argument.
* It should accept a command line argument of -help to show the documenation.
* It should accept a command line argument of -d to specific which delimiters count as sentence seperators.
* It should default to a sentence delimiter list of period, question mark, exclamation mark, comma, and semi-colon.
* It should acccept a command line argulment of -l to specify the lower word length limit.
* It should default to a lower word length limit of 3.
* It should only count words that are above the lower word length limit.
* It should compile with Vanilla Java 1.7.
* It should compile with javac and no options.
    
### 2.2 Non-Functional Requirements
    
* It should be portable and run on mac, windows, and linux operating systems.
* It should provide error messaging that is easy for non-technical users to comprehend.
	- This should include help messages providing a summary of correct inputs.
* It should be documented in a manual.
* It should have modest computing requirements.
* It should have sufficent test coverage.
    
    
    
    
