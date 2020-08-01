package calendar;
import java.time.LocalDateTime;
import java.util.*;
//TimeInterval Classs to keep tract of starting date, ending date
public class TimeInterval {
	LocalDateTime startingTime;
	LocalDateTime endingTime;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endingTime == null) ? 0 : endingTime.hashCode());
		result = prime * result + ((startingTime == null) ? 0 : startingTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeInterval other = (TimeInterval) obj;
		if (endingTime == null) {
			if (other.endingTime != null)
				return false;
		} else if (!endingTime.equals(other.endingTime))
			return false;
		if (startingTime == null) {
			if (other.startingTime != null)
				return false;
		} else if (!startingTime.equals(other.startingTime))
			return false;
		return true;
	}

	public boolean conflict(Event e1, Event e2)
	{
		if(e1.getTimeInterval().equals(e2.getTimeInterval()))
		{
			return true;
		}
		return false;
	}
	//Getters and setters
	public LocalDateTime getStartingTime() {
		return startingTime;
	}

	public String getStartTime()
	{
		return startingTime.getHour() + ":" + startingTime.getMinute();
	}
	
	public String getEndTime()
	{
		return endingTime.getHour() + ":" + endingTime.getMinute();
	}
	
	public void setStartingTime(LocalDateTime startingTime) {
		this.startingTime = startingTime;
	}

	public LocalDateTime getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(LocalDateTime endingTime) {
		this.endingTime = endingTime;
	}

	public TimeInterval(LocalDateTime startingTime, LocalDateTime endingTime)
	{
		this.startingTime = startingTime;
		this.endingTime = endingTime;
	}
}