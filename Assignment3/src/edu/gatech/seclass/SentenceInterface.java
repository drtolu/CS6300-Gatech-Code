package edu.gatech.seclass;

/**
 * This is an interface for a simple class that represents a sentence, defined
 * as a whitespace-separated sequence of one or more words. You can assume that
 * words consist of contiguous ASCII characters.
 * 
 * @author Rufus
 * 
 */
public interface SentenceInterface {

    /**
     * Sets the value of the current sentence.
     * 
     * @param sentence
     *            The value to be set
     */
    public void setSentence(String sentence);

    /**
     * Returns the current sentence
     * 
     * @return Current sentence
     */
    public String getSentence();

    /**
     * Returns the number of words in the current sentence
     * 
     * @return Number of words in the current sentence
     */
    public int length();

    /**
     * Returns the word at position "position" in the sentence, with 1 being the
     * first position
     * 
     * @param position
     *            Position of the word to return
     * @return The word at position "position"
     * @throws IllegalArgumentException
     *             If "position" is invalid (i.e., "position" <= 0)
     * @throws PositionOutOfBoundsException
     *             If the sentence has less than "position" words in it
     */
    public String getWord(int position) throws IllegalArgumentException,
            PositionOutOfBoundsException;

    /**
     * Returns the words from position "startPosition" to position "endPosition"
     * in the sentence, with 1 being the first position
     * 
     * @param startPosition
     *            Position of the first word to return
     * @param endPosition
     *            Position of the last word to return
     * @return
     * @throws IllegalArgumentException
     *             If either "startPosition" or "endPosition" are invalid (i.e.,
     *             "startPosition" <= 0, "endPosition" <= 0, or "startPosition"
     *             > "endPosition")
     * @throws PositionOutOfBoundsException
     *             If the sentence has less than "endPosition" words in it
     */
    public String[] getWords(int startPosition, int endPosition)
            throws IllegalArgumentException, PositionOutOfBoundsException;

    /**
     * Returns the position of the first complete and case sensitive occurrence
     * of word word in the sentence and -1 if the word is not present
     * 
     * @param word
     *            Word whose position must be returned
     * @return The position of the first occurrence of "word" in the sentence or
     *         -1, if "word" does not appear in the sentence
     */
    public int indexOf(String word);

    /**
     * Reverses the positions of the words in the sentence, so that the first
     * word becomes the last one, the second word becomes the one before the
     * last word, and so on.
     */
    public void reverse();
}
