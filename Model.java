package calendar;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {
	private LocalDate currentDate;
	private LocalDate today;
	private ArrayList<ChangeListener> listeners;
	
	public Model(LocalDate currentDate, LocalDate today) {
		listeners = new ArrayList<ChangeListener>();
		this.currentDate = currentDate;
		this.today = today;
	}
	public LocalDate getCurrentDate() {
		return currentDate;
	}
	public LocalDate getToday() {
		return today;
	}
	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
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
