package edu.gatech.seclass.prj1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;

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
    public void testGetWord1() throws IllegalArgumentException, PositionOutOfBoundsException {
        sentence.setSentence("This is a short sentence");
        String word = sentence.getWord(2);
        assertEquals("is", word);
    }

	@Test(expected=IllegalArgumentException.class)
    public void testGetWord2() throws IllegalArgumentException, PositionOutOfBoundsException {
    	sentence.setSentence("This is another short sentence");
    	sentence.getWord(0);
    }

    @Test
    public void testGetWords1() throws IllegalArgumentException, PositionOutOfBoundsException {
        sentence.setSentence("I have a sentence for you to use.");
        String[] fragment = sentence.getWords(4, 7);
        String[] testFrag = {"sentence", "for", "you", "to"};
        assertArrayEquals(fragment, testFrag);
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void testGetWords2() throws IllegalArgumentException, PositionOutOfBoundsException {
        sentence.setSentence("Another short sentence");
        sentence.getWords(3, 4);
    }

    @Test
    public void testGetLength1() {
        sentence.setSentence("I have a question for you.");
        assertEquals(6, sentence.length());
    }

    @Test
    public void testGetLength2() {
        sentence.setSentence("What is the question that you have for me?");
        assertNotEquals(6, sentence.length());
    }
    
    @Test
    public void testGetLength3() {
        sentence.setSentence("");
        assertEquals(0, sentence.length());
    }

    @Test
    public void testIndexOf1() {
        sentence.setSentence("This is a short sentence");
        assertEquals(5, sentence.indexOf("sentence"));
    }

    @Test
    public void testIndexOf2() {
        sentence.setSentence("This is a short sentence");
        assertNotEquals(0, sentence.indexOf("This"));
    }

    @Test
    public void testReverse1() {
        sentence.setSentence("This is a short sentence");
        sentence.reverse();
        assertEquals("sentence short a is This", sentence.getSentence());
    }

    @Test
    public void testReverse2() {
    	sentence.setSentence("This is a short sentence");
        sentence.reverse();
        assertNotEquals("This is a short sentence", sentence.getSentence());
    }
}