package edu.gatech.seclass.prj1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sentence implements SentenceInterface {
	
	List<String> sentenceList = new ArrayList<String>();

	@Override
	public void setSentence(String sentence) {
		setSentence(sentence, 0);
	}
	
	@Override
	public void setSentence(String sentence, int minWordLength) {
		if (sentence==null || sentence.equals(""))
			return;
		
		for (String word : sentence.split("\\s+"))
			if (word.length() >= minWordLength) 
				this.sentenceList.add(word);
			
	}

	@Override
	public String getSentence() {
		String sentence = "";
		int size = this.sentenceList.size();
		for (int i=1; i<size; i++)
			sentence += this.sentenceList.get(i-1) + " ";
		
		sentence += this.sentenceList.get(size-1);
		return sentence;
	}

	@Override
	public int length() {
		return this.sentenceList.size();
	}

	@Override
	public String getWord(int position) throws IllegalArgumentException, PositionOutOfBoundsException {
		return this.getWords(position, position)[0];
	}

	@Override
	public String[] getWords(int startPosition, int endPosition) throws IllegalArgumentException, PositionOutOfBoundsException {
		if (startPosition <= 0 || endPosition <= 0 || startPosition > endPosition)
			throw new IllegalArgumentException();
		if (endPosition > this.sentenceList.size())
			throw new PositionOutOfBoundsException();
		
		List<String> fragment = new ArrayList<String>();
		for (int i=startPosition; i<=endPosition; i++)
			fragment.add(this.sentenceList.get(i-1));
			
		return fragment.toArray(new String[fragment.size()]);
	}

	@Override
	public int indexOf(String word) {
		int index = this.sentenceList.indexOf(word);
		return (index < 0) ? index : index+1;
	}

	@Override
	public void reverse() {
		Collections.reverse(this.sentenceList);
	}

}