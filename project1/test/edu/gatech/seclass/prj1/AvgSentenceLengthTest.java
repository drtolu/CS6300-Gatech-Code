package edu.gatech.seclass.prj1;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// trivial methods - not getting test coverage
// getMinWordLength()
// getSentenceDelimiters()
// getFile()
// getFileLocation()
// printAverageLengthPerSentence()
// displayHelp() 

// trivial methods getting limited (less than 3) test coverage
// setFileLocation()
// getArrayOfSentences()
// setMinWordLength()
// testSetFile()

public class AvgSentenceLengthTest {
	
    private AvgSentenceLength asl;
    private String fileDir;

    @Before
    public void setUp() throws Exception {
        asl = new AvgSentenceLength();
        fileDir = "test" + File.separator + "inputfiles" + File.separator;

    }
    @After
    public void tearDown() throws Exception {
        asl = null;
        fileDir = null;
    }
  

    
    
  /*
   * setMinWordLength() tests
   */
  
  @Test
  public void testSetMinWordLength1() {
	  String comment = "Testing that it works with an integer";
	  asl.setMinWordLength(5);
	  assertEquals(comment, 5, asl.getMinWordLength(), 0);
    
  }
  
  @Test
  public void testSetMinWordLength2() {
	  String comment = "Testing that it works with a string";
	  asl.setMinWordLength("5");
	  assertEquals(comment, 5, asl.getMinWordLength(), 0);
  }
  
  @Test
  public void testSetMinWordLength3() {
	  String comment = "Testing that it works with a negative integer";
	  asl.setMinWordLength(-5);
	  assertEquals(comment, -5, asl.getMinWordLength(), 0);
    
  }
  /*
   * setSentenceDelimiters() tests
   */
  
  @Test
  public void testSetSentenceDelimiters1() {
	  String comment = "Testing spaces as delimiters";
	  asl.setFile(new File(fileDir + "input.txt"));
	  asl.setSentenceDelimiters(" ");
	  assertEquals(comment, 7, asl.computeAverageSentenceLength(), 0);
  }

  @Test
  public void testSetSentenceDelimiters2() {
	  String comment = "Testing spaces between delimiters";
	  asl.setFile(new File(fileDir + "input.txt"));
	  asl.setSentenceDelimiters("? .");
	  assertEquals(comment, 7, asl.computeAverageSentenceLength(), 0);
  }

  @Test
  public void testSetSentenceDelimiters3() {
	  String comment = "Testing how it handles two of the same delimiter";
	  asl.setFile(new File(fileDir + "input.txt"));
	  asl.setSentenceDelimiters("..");
	  assertEquals(comment, 7, asl.computeAverageSentenceLength(), 0);
  }
  
  /*
   * setFile() tests
   */
  
  @Test
  public void testSetFile1() {
	  String comment = "Testing that it sets the file location";
	  asl.setFile(new File(fileDir + "input.txt"));
	  assertEquals(comment, fileDir + "input.txt", asl.getFileLocation());
  }
  
  @Test
  public void testSetFile2() {
	  // Testing that it catches the exception when non TXT file passed
	  asl.setFile(new File(fileDir + "input.rtf"));
  }
  
  /*
   * setFileLocation() tests
   */
  
    @Test(expected=TXTFilesOnlyException.class)
    public void testSetFileLocation1() throws IncorrectFilePathException, TXTFilesOnlyException, FileCouldNotBeReadException
    {
      // Test that it throws exception when non txt files are used
      String[] args = {fileDir + "input.rtf"};
      AvgSentenceLength.checkPath(args);
    }
  
  /*
   * getLengthOfEachSentence() tests
   */
  
  @Test
  public void testGetLengthOfEachSentence1() {
	  String comment = "Testing that it returns length of each sentence with default minWordLength";
    
	  String[] sentences = new String[3];
	  sentences[0] = "This luxuriant hair conditioner is for curly hair."; 
	  sentences[1] = "What kind of monster puts artisanal butter in the freezer?";
	  sentences[2] = "Animals.";
    
	  List<Integer> lengthOfEachSentence = new ArrayList<Integer>();
	  lengthOfEachSentence.add(7);
	  lengthOfEachSentence.add(8);
	  lengthOfEachSentence.add(1);
    
	  assertEquals(comment, lengthOfEachSentence , asl.getLengthOfEachSentence(sentences));
  }
  
  @Test
  public void testGetLengthOfEachSentence2() {
	  String comment = "Testing that it works with empty array of strings";
	  String[] sentences = new String[0];
	  List<Integer> lengthOfEachSentence = new ArrayList<Integer>();
	  assertEquals(comment, lengthOfEachSentence , asl.getLengthOfEachSentence(sentences));
  }
  
  @Test
  public void testGetLengthOfEachSentence3() {
	  String comment = "Testing that it works when all words below minWordLength";
    
	  String[] sentences = new String[1];
	  sentences[0] = "I am an ox";
    
	  List<Integer> lengthOfEachSentence = new ArrayList<Integer>();
	  lengthOfEachSentence.add(0);
    
	  assertEquals(comment, lengthOfEachSentence , asl.getLengthOfEachSentence(sentences));
  }
  

  /*
   * getInputDataAsOneString() tests
   */
  
  // probably should have a test that it handles trouble reading error, but because we
  // call System.exit() this won't work - consider refactor?? - instead throw
  // exception and catch in main where you do System.exit()
  // http://stackoverflow.com/questions/309396/java-how-to-test-methods-that-call-system-exit
  
  @Test
  public void testGetInputDataAsOneString1() throws IOException {
	  String comment = "Testing That it returns all items";
	  asl.setFile(new File(fileDir + "input.txt"));
	  assertEquals(comment, 180 , asl.getInputDataAsOneString().length(), 0);
  }
  
  
  @Test
  public void testGetInputDataAsOneString2() throws FileCouldNotBeReadException, IOException {
	  String comment = "Testing that it works with no file provided";
	  assertEquals(comment, "" , asl.getInputDataAsOneString());
  }
  
  @Test
  public void testGetInputDataAsOneString3() throws IOException {
	  String comment = "Testing that it works with non-latin characters";
	  asl.setFile(new File(fileDir + "chinese.txt"));
	  assertEquals(comment, 713 , asl.getInputDataAsOneString().length());
  }
  
  /*
   * getArrayOfSentences() tests
   */

  @Test
  public void testGetArrayOfSentences1() {
	  String comment = "Testing that contents of the array and objects are the same";
	  asl.setSentenceDelimiters(".,%");
	  String[] returned =asl.getArrayOfSentences("This. Here. Is% 555, Sentences%");
	  String[] strings = {"This", "Here", "Is", "555", "Sentences"};
	  assertArrayEquals(returned, strings);
	  assertEquals(comment, returned[2], strings[2]);
  }
  
  @Test
  public void testGetArrayOfSentences2()
  {
	  /*
	   * This is showing that if you use a ' as a delimeter
	   * it will break a single word into another sentence
	   */
	  String comment = "Testing that delimeters break no matter what";
	  asl.setFile(new File(fileDir + "input.txt"));
	  asl.setSentenceDelimiters(".'");
	  String[] sentences = new String[1];
	  sentences[0] = "My Name is Miles O'Brien.";
	  String[] strings ={"My Name is Miles O","Brien"};
	  assertArrayEquals(strings,asl.getArrayOfSentences(sentences[0]));
	  assertEquals(2,asl.getArrayOfSentences(sentences[0]).length);
  }
  
  
  @Test
  public void testGetArrayOfSentences3()
  {
	  String comment = "Testing that even without spaces the array returns whatever the delimiter specifies.";
	  asl.setFile(new File(fileDir + "input.txt"));
	  String[] sentences = new String[1];
	  sentences[0] = "This.is.one.string.that.should.come.back.as.11.sentences.";
	  String[] strings ={"This", "is", "one", "string", "that", "should", "come", "back", "as", "11", "sentences"};
	  assertArrayEquals(strings,asl.getArrayOfSentences(sentences[0]));
	  assertEquals(11,asl.getArrayOfSentences(sentences[0]).length);
  }
  /*
   * computeAverageSentenceLength() tests
   */
  
    @Test
    public void testComputeAverageSentenceLength1() {
        String comment = "Testing sentences that span multiple lines";
        asl.setFile(new File(fileDir + "input.txt"));
        assertEquals(comment, 7, asl.computeAverageSentenceLength(), 0);
    }
    @Test
    public void testComputeAverageSentenceLength2() {
        String comment = "Testing customized delimiters";
        asl.setFile(new File(fileDir + "input.txt"));
        asl.setSentenceDelimiters("%.");
        assertEquals(comment, 3, asl.computeAverageSentenceLength(), 0);
    }
    @Test
    public void testComputeAverageSentenceLength3() {
        String comment = "Testing customized minimal word length";
        asl.setFile(new File(fileDir + "input.txt"));
        asl.setMinWordLength(5);
        assertEquals(comment, 3, asl.computeAverageSentenceLength(), 0);
    }
	@Test
	public void testComputeAverageSentenceLength4()	{
		String comment = "Testing for nulls";
        assertEquals(comment, 0, asl.computeAverageSentenceLength(), 0);
	}
	
	/*
	 * checkArgs() tests
	 * 
	 */
	
	@Test(expected=IncorrectFilePathException.class)
	public void checkArgsExceptionsTest1() throws IncorrectFilePathException, TXTFilesOnlyException, FileCouldNotBeReadException {
		String[] args = {};
	    AvgSentenceLength.checkPath(args);
	}
	
	@Test(expected=TXTFilesOnlyException.class)
	public void checkArgsExceptionsTest2() throws IncorrectFilePathException, TXTFilesOnlyException, FileCouldNotBeReadException {
		String[] args = {fileDir + "input.rtf"};
	    AvgSentenceLength.checkPath(args);
	}
	
	@Test(expected=FileCouldNotBeReadException.class)
	public void checkArgsExceptionsTest3() throws IncorrectFilePathException, TXTFilesOnlyException, FileCouldNotBeReadException {
		String[] args = {fileDir + "fakefile.txt"};
	    AvgSentenceLength.checkPath(args);
	}
	
	@Test(expected=MissingMinLengthException.class)
	public void checkArgsExceptionsTest4() throws MissingMinLengthException, MinLengthNotANumberException {
		String[] args = {fileDir + "input.txt","-l"};
	    AvgSentenceLength.checkMinLength(args, args.length);
	}
	
	@Test(expected=MissingMinLengthException.class)
	public void checkArgsExceptionsTest5() throws MissingMinLengthException, MinLengthNotANumberException {
		String[] args = {fileDir + "input.txt","-d", ".", "-l"};
	    AvgSentenceLength.checkMinLength(args, args.length);
	}
	
	@Test(expected=MinLengthNotANumberException.class)
	public void checkArgsExceptionsTest6() throws MissingMinLengthException, MinLengthNotANumberException {
		String[] args = {fileDir + "input.txt","-l", "-d"};
	    AvgSentenceLength.checkMinLength(args, args.length-1);
	}
	
	@Test(expected=MissingDelimitersException.class)
	public void checkArgsExceptionsTest7() throws MissingDelimitersException {
		String[] args = {fileDir + "input.txt","-d"};
	    AvgSentenceLength.checkDelimiters(args, args.length);
	}
	
}
