package edu.gatech.seclass.prj1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AvgSentenceLength {

  public static final String LENGTH = "-l";
  public static final String DELIMITERS = "-d";
  private static final int DEFAULT_MIN_WORD_LENGTH = 3;
  private static final String DEFAULT_DELIMETERS_LIST = "\\.\\s*|\\?\\s*|\\!\\s*";

  int minWordLength;
  String fileLocation;
  String sentenceDelimiters;
  
  public AvgSentenceLength() {
    this.minWordLength = DEFAULT_MIN_WORD_LENGTH;
    this.fileLocation = "";
    this.sentenceDelimiters = DEFAULT_DELIMETERS_LIST;
  }

  public AvgSentenceLength(String fileLocation) {
    this.minWordLength = DEFAULT_MIN_WORD_LENGTH;
    this.fileLocation = fileLocation;
    this.sentenceDelimiters = DEFAULT_DELIMETERS_LIST;
  }

  public int getMinWordLength() {
    return minWordLength;
  }

  public void setMinWordLength(int minWordLength) {
    this.minWordLength = minWordLength;
  }

  public void setMinWordLength(String minWordLength) {
    this.minWordLength = Integer.parseInt(minWordLength);
  }

  public String getSentenceDelimiters() {
    return sentenceDelimiters;
  }

  public void setSentenceDelimiters(String delimiters) {
    String sentenceDelimiters = "";
    delimiters = delimiters.replaceAll("\\s+","");
    
    if (delimiters == null || delimiters.equals("")) {
      sentenceDelimiters = DEFAULT_DELIMETERS_LIST;
      System.out.printf("Your delimiters failed, defautling to: %s\n\n", ".?!");
    } else {
      String[] delimitersList = delimiters.split("");
      
      if (delimitersList.length > 0) 
        sentenceDelimiters += "\\" + delimitersList[0] + "\\s*";

      for (int i=1; i < delimitersList.length; i++)
        sentenceDelimiters += "|\\" + delimitersList[i] + "\\s*";
      
      System.out.printf("Splitting lines using delimiters: %s\n", delimiters);
    }
    
    this.sentenceDelimiters = sentenceDelimiters;
  }
  
  public File getFile() {
    return new File(fileLocation);
  }
  
  public void setFile(File file) {
      setFileLocation(file.getPath());
  }

  public String getFileLocation() {
    return fileLocation;
  }

  public void setFileLocation(String fileLocation) {
    this.fileLocation = fileLocation;
  }

  public List<Integer> getLengthOfEachSentence(String[] sSentences) {
    List<Integer> lengthOfEachSentence = new ArrayList<Integer>();
    
    for (String iterSentence : sSentences) {
      Sentence sentence = new Sentence();
      sentence.setSentence(iterSentence.replaceAll(this.sentenceDelimiters, ""), minWordLength);
      lengthOfEachSentence.add(sentence.length());
    }
    
    return lengthOfEachSentence;
  }

  public String[] getArrayOfSentences(String sInputDataAsOneString) {
    return sInputDataAsOneString.split(this.sentenceDelimiters);
  }

  public String getInputDataAsOneString() throws IOException {
    if (fileLocation.isEmpty())
      return "";
    System.out.println("fileLocation: "+ fileLocation);
    String sInputDataAsOneString = "";
	BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
	String line;
	while ((line = reader.readLine()) != null)
	  sInputDataAsOneString += line + " ";
	  
	reader.close();
    
	return sInputDataAsOneString;
  }

  public int computeAverageSentenceLength() {
    if (this.fileLocation.equals(null) || this.fileLocation.equals(""))
      return 0;

    String fullFileString = "";
    try {
    	fullFileString = getInputDataAsOneString();
    } catch (IOException e) {
		e.printStackTrace();
	}
    
    String[] sentences = getArrayOfSentences(fullFileString);
    List<Integer> lengthOfEachSentence = getLengthOfEachSentence(sentences);

    int dAverageLengthPerSentence = 0;

    for (int lengthOfSentence : lengthOfEachSentence)
      dAverageLengthPerSentence += lengthOfSentence;

    if (lengthOfEachSentence.size() > 0)
      dAverageLengthPerSentence /= lengthOfEachSentence.size();

    return dAverageLengthPerSentence;
  }

  public void printAverageLengthPerSentence() {
    System.out.println("Average length of sentence is " + computeAverageSentenceLength() + " words.");
  }

	public static boolean checkArgs(String[] args) {
		try {
			checkPath(args);
		} catch (IncorrectFilePathException e) {
			System.out.println("You did not enter the correct path to the text file.");
			return false;
		} catch (TXTFilesOnlyException e) {
			System.out.println("Only Text files are allowed, please start over.\n\n");
			return false;
		} catch (FileCouldNotBeReadException e) {
			System.out.println("I had trouble reading the data\n\n");
			return false;
		}
		
		try {
			for (int i = 1; i < args.length; i++) {
				switch (args[i]) {
					case LENGTH:
						checkMinLength(args, i+1);
						break;
					case DELIMITERS:
						checkDelimiters(args, i+1);
						break;
				}
			}
		} catch (MissingMinLengthException e) {
			System.out.println("A minimum word length is missing.");
			return false;
		} catch (MissingDelimitersException e) {
			System.out.println("The delimiters are missing.");
			return false;
		} catch (MinLengthNotANumberException e) {
			System.out.println("The minimum word length must be an integer number.");
			return false;
		}
		
		return true;
	}
	
	public static void checkMinLength(String[] args, int i) throws MissingMinLengthException, MinLengthNotANumberException {
		if (args.length==i)
			throw new MissingMinLengthException();
		
		try { 
			Integer.parseInt(args[i]);
	    } catch(NumberFormatException e) { 
	    	throw new MinLengthNotANumberException();
	    }
	}

	public static void checkDelimiters(String[] args, int i) throws MissingDelimitersException {
		if (args.length==i)
			throw new MissingDelimitersException();
	}

	public static void checkPath(String[] args) throws IncorrectFilePathException, TXTFilesOnlyException, FileCouldNotBeReadException {
		if (args.length < 1)
			throw new IncorrectFilePathException();
		
	    if (!(args[0].toString().endsWith("txt")))
	        throw new TXTFilesOnlyException();
	    
	    try {
			new BufferedReader(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			throw new FileCouldNotBeReadException();
		}
	}
}
