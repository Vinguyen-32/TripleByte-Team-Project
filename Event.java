package calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//Keep track of name of event, days of event, and TimeInterval object that stores time
public class Event implements Comparable<Event> { 
	private String name;
	private TimeInterval time;

	/**
	 * String of name, day, and TimeInterval to keep track of dates, time
	 * @param name
	 * @param time
	 * @param days
	 */
	public Event(String name, TimeInterval time)
	{
		this.name = name;
		this.time = time;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}


	//Getters and setters
	public TimeInterval getTimeInterval()
	{
		return time;
	}
	
	public String getName()
	{
		return this.name;
	}

	public void setTime(TimeInterval time) {
		this.time = time;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*
	  @Override
	    public int compare(Event mcc1, Event2 mcc2) {
		  	mcc = time.startingTime.
	        return mcc1.getTimestamp().compareTo(mcc2.getTimestamp());
	    }
*/
	

	@Override
	public int compareTo(Event o) {
		int compareValue = time.getStartingTime().compareTo(o.getTimeInterval().getStartingTime());
		//System.out.println(time.getStartingTime());
		//System.out.println(o.getTimeInterval().getStartingTime());
		//System.out.println(compareValue);
		return compareValue;
		
/*
        int compareValue = time.getStartingTime().toString().compareTo(o.getTimeInterval().getStartingTime().toString());
		if(compareValue > 0)
		{
			return 1;
		}
		else if(compareValue < 0)
		{
			return -1;
		}
		else if(compareValue == 0)
		{
			return 0;
		}
		return 0;
		*/
	}
}