
package calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {
	private LocalDate viewDate;
	private LocalDate currentDate;
	private LocalDate today;
	private Map<LocalDate, ArrayList<Event>> oneTime;
	private Map<String, ArrayList<Event>> recurringTime;
	private ArrayList<ChangeListener> listeners;
	
	public Model(LocalDate currentDate, LocalDate today) {
		listeners = new ArrayList<ChangeListener>();
		this.oneTime = new HashMap<LocalDate, ArrayList<Event>>();
		this.recurringTime = new HashMap<String, ArrayList<Event>>();
		String days = "S/M/T/W/H/F/A";
		String[] daySplit = days.split("/", 7);
		for(int i = 0; i < 7; i++)
		{
			recurringTime.put(daySplit[i], new ArrayList<Event>());
		}
		this.viewDate = currentDate;
		this.currentDate = currentDate;
		this.today = today;
	}
	
	public LocalDate getViewDate() {
		return viewDate;
	}
	
	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public LocalDate getToday() {
		return today;
	}
	
	public void setViewDate(LocalDate viewDate) {
		this.viewDate = viewDate;
		update();
	}
	
	public Map<LocalDate, ArrayList<Event>> getOneTime() {
		return oneTime;
	}
	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
		this.viewDate = currentDate;
		update();
	}
	
	public void addOneTime(LocalDate dateKey, Event dateTime) {
		if(oneTime.get(dateKey) == null)
		{
			oneTime.put(dateKey, new ArrayList<Event>());
		}
		oneTime.get(dateKey).add(dateTime);
		update();
	}
	
	public void recTime(String dateKey, Event dateTime) {
		recurringTime.get(dateKey).add(dateTime);
		update();
	}
	
	public void attach(ChangeListener c){
		listeners.add(c);
    }

   /**
    * Notify views of the changes
   */
	public void update(){
		for (ChangeListener l : listeners){
			l.stateChanged(new ChangeEvent(this));
	    }
	}
}