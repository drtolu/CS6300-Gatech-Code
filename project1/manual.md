# Average Sentence Length Calculator

This program calculates the average number of words per sentence in a given file. With this program, you can specify your own custom sentence delimiters, 
as well as specify the minimum amount of characters a word needs to be counted towards the average. 

## Usage

### Basic Usage
To calculate the average number of words per sentence in your file, execute the program using java, 
and a filepath to a file containing text data as your first argument:

`java Main C:\Users\user1\essay1.txt`

This will print out a message displaying the average number of words per sentence using the default 
settings for the program.

### Custom Sentence Delimiters

You can use the -d flag to specify your own custom sentence delimiters:

`java Main C:\Users\user1\essay1.txt -d .!,;:`

This would count any of the characters after the -d flag (.!,;:) as a sentence delimiter. 
If the -d flag is not used, the program will use the default sentence delimiters (.!?).

### Custom Minimum Word Length

You can use the -l flag to specify a custom minimum word length:

`java Main C:\Users\user1\essay1.txt -l 5`

This would count only words that are at least 5 characters long towards a sentence's total word count. 
If the -l flag is not used, the program will use the default minimum word length of 3 characters.

### Help 

The -help command can be used to display basic information about the program:

`java Main -help`

## Credits

Average Sentence Length Calculator® was built by: 

-    Ryan Beall
-    Seth Anderson
-    Hunter Powers
-    Namit Gupta
-    Alex Politis
