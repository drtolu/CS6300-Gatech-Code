package edu.gatech.seclass;

@SuppressWarnings("serial")
public class PositionOutOfBoundsException extends Exception {
	
	public PositionOutOfBoundsException(int position)
	{
		super(Integer.toString(position));
	}
	
	public String getPosition()
    {
        return ("PositionOutOfBoundsException caught. Incorrect position entered :" + super.getMessage());
    }
}
