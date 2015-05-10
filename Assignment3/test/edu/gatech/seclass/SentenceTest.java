package edu.gatech.seclass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SentenceTest {

    private Sentence sentence;

    @Before
    public void setUp() throws Exception {
        sentence = new Sentence();
    }

    @After
    public void tearDown() throws Exception {
        sentence = null;
    }

    @Test
    public void testGetWord1() throws IllegalArgumentException,
            PositionOutOfBoundsException {
        sentence.setSentence("This is a short sentence");
        String word = sentence.getWord(2);
        assertEquals("is", word);
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void testGetWord2() throws IllegalArgumentException,
            PositionOutOfBoundsException {
        sentence.setSentence("This is a short sentence");
        String word=sentence.getWord(6);
    }

    @Test
    public void testGetWords1() throws IllegalArgumentException,
            PositionOutOfBoundsException {
        sentence.setSentence("This is a short sentence");
        String [] vWords = sentence.getWords(3, 5);
        
        assertEquals(vWords[0], "a");
        assertEquals(vWords[1], "short");
        assertEquals(vWords[2], "sentence");
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void testGetWords2() throws IllegalArgumentException,
            PositionOutOfBoundsException {
        sentence.setSentence("Another short sentence");
        sentence.getWords(3, 4);
    }

    @Test
    public void testGetLength1() {
        sentence.setSentence("This is a short sentence");
        int length = sentence.length();
        assertEquals(5, length);
    }

    @Test
    public void testGetLength2() {
        sentence.setSentence(null);
        int length = sentence.length();
        assertEquals(0, length);
    }

    @Test
    public void testIndexOf1() {
        sentence.setSentence("This is a short sentence");
        assertEquals(5, sentence.indexOf("sentence"));
    }

    @Test
    public void testIndexOf2() {
        sentence.setSentence("This is a short sentence");
        assertEquals(-1, sentence.indexOf("Short")); // note that the check is case-sensitive and Short != short
    }

    @Test
    public void testReverse1() {
        sentence.setSentence("This is a short sentence");
        sentence.reverse();
        assertEquals("sentence short a is This", sentence.getSentence());
    }

    @Test
    public void testReverse2() {
        sentence.setSentence("Namit is           a good boy");  //a sentence with multiple number of spaces
        
        String Orig_sentence = sentence.getSentence();
        
        sentence.reverse();
 
        String new_sentence = sentence.getSentence();
        assertEquals("boy good a           is Namit", new_sentence);
        
        sentence.reverse();
        assertEquals(Orig_sentence, sentence.getSentence());  //double reversal should return back the original sentence.
    }
}
