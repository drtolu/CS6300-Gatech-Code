package edu.gatech.seclass;

public class Sentence implements SentenceInterface{
	
	private String SingleSentence;
	private String[] vSingleSentence;
	
	
	/**
     * Sets the value of the current sentence.
     * 
     * @param sentence
     *            The value to be set
     */
    public void setSentence(String sentence)
    {
    	SingleSentence = sentence;	
    	
    	if(SingleSentence != null)
    		vSingleSentence = SingleSentence.split("\\s");
    }

    /**
     * Returns the current sentence
     * 
     * @return Current sentence
     */
    public String getSentence()
    {
    	return SingleSentence;
    	
    }
    
    /**
     * Returns the number of words in the current sentence
     * 
     * @return Number of words in the current sentence
     */
    public int length()
    {
    	if(vSingleSentence == null)
    		return 0;
    	else
    		return vSingleSentence.length;
    }

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
    public String getWord(int position) throws IllegalArgumentException, PositionOutOfBoundsException
    {
    	if (position <=0)
    		throw new IllegalArgumentException("The position of the word needs to be equal to or greater than 1");
    	
    	if(position > length())
    		throw new PositionOutOfBoundsException(position);
    	
    	return vSingleSentence[position-1];
    }
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
            throws IllegalArgumentException, PositionOutOfBoundsException
    {
    	if(startPosition <=0)
    		throw new IllegalArgumentException("Ivalid input for start position");
    	
    	if(endPosition <=0)
    		throw new IllegalArgumentException("Ivalid input for end position");
    	
    	if(startPosition > endPosition)
    		throw new IllegalArgumentException("Ivalid input for because startPosition is greater than endPosition");
    	
    	if(endPosition > length())
    		throw new PositionOutOfBoundsException(endPosition);
    	
    	String [] filterData = new String[endPosition-startPosition+1];
    	int j=0;
    	
    	for (int i = startPosition-1; i<endPosition;i++, j++)
    		filterData[j] = vSingleSentence[i];
    		
        return filterData;
    }
    

    /**
     * Returns the position of the first complete and case sensitive occurrence
     * of word word in the sentence and -1 if the word is not present
     * 
     * @param word
     *            Word whose position must be returned
     * @return The position of the first occurrence of "word" in the sentence or
     *         -1, if "word" does not appear in the sentence
     */
    public int indexOf(String word)
    {
    	int position = -1;
    	
    	for(int i=0; i<length(); i++)
    	{
    		if(word.equals(vSingleSentence[i]))
    		{
    			position = i+1;
    			break;
    		}
    	}
    	return position;
    }
    
    
    /**
     * Reverses the positions of the words in the sentence, so that the first
     * word becomes the last one, the second word becomes the one before the
     * last word, and so on.
     */
    public void reverse()
    {
    	if (SingleSentence != null)
    	{
    		for (int i =0; i<(length())/2; i++)
    	    {
    			String temp = vSingleSentence[i];
    		    vSingleSentence[i] = vSingleSentence[length()-i-1];
    		    vSingleSentence[length()-i-1] = temp;	
    	    }
    		
    		SingleSentence = "";
    		
    		for (int i=0; i<length(); i++)
    		{
    			if ( i !=0)
    				SingleSentence = SingleSentence + " " + vSingleSentence[i];
    			else
    				SingleSentence = vSingleSentence[i];
    		}
    	}
    	
    }
    
}
