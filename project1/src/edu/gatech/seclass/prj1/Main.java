package edu.gatech.seclass.prj1;

/*
 * This code assumes that - ',' is not a default delimiter. User can specify
 * ',' to act as a delimiter if and only if it is specified after the -d
 * command in the CLI. ';' will never be considered as a command line
 * parameter delimiter even after it is specified after '-d' option. '.',
 * '!' and '?' are default delimiters. Use '-l' command to specify the
 * minimum length of a valid word. The code assumes that the path to the
 * input.txt file contains no spaces.
 * 
 * Authored by Namit Gupta on 02/02/15 for Project1
 */

public class Main {

	static final String HELP = "-help";

	public static void main(String[] args) {
		if (args.length>0 && args[0].equals(HELP)) {
			displayHelp();
			return;
		}
			
		if (AvgSentenceLength.checkArgs(args))
			userInteface(args);
	}

	private static void userInteface(String[] args) {
		AvgSentenceLength asl = new AvgSentenceLength(args[0]);

		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case AvgSentenceLength.LENGTH:
					asl.setMinWordLength(args[i + 1]);
					break;
				case AvgSentenceLength.DELIMITERS:
					asl.setSentenceDelimiters(args[i + 1]);
					break;
				case HELP:
					displayHelp();
					break;
			}
		}
		
		asl.printAverageLengthPerSentence();
	}
	
	private static void displayHelp() 
	{
		String helpString = "This is the help menu, there a few simple options available to you, to change the way sentnces are counted.\n\n"
				+ "You can easily change what constitues a sentence by adding a custom delimiter.\n"
				+ "For example:"
				+ "		'java calcAvgWordsPerSentence C:\\essay1.txt -d .!,;:'\n"
				+ "This would add periods, exclamation marks, commas, colons and semi colons as sentence breaks.\n"
				+ "\n"
				+ "You can also change the minimum letter count by utilizing the -l option.\n"
				+ "For example:"
				+ "		'java calcAvgWordsPerSentence C:\\essay1.txt -l 5'\n"
				+ "This would only count words that have 5 or more letters.\n";
		
		System.out.println(helpString);

	}
}