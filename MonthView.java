package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MonthView extends JPanel implements ChangeListener {
	private  JPanel calendarSection;
	private Model model;
	private Controller controller;
	private LocalDate today;
	private LocalDate currentDate;
	private JPanel[][] datePanels;
	private JPanel monthSlots;

	public MonthView(Model model, Controller controller) {
		datePanels = new JPanel[6][7];
		setLayout(new BorderLayout());
		setSize(400, 400);
		this.model = model;
		this.controller = controller;
		calendarSection = new JPanel();
		calendarSection.setLayout(new GridLayout(7, 7));
		add(calendarSection);
		refresh();
	}
	
	public void render() {
		int rowControl = 0;
		int columnControl = 0;
		int calendarIndex = 1;
		LocalDate firstDateOfMonth = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), 1);
		int date = firstDateOfMonth.getDayOfWeek().getValue()%7;
		//System.out.println(date);
		// Empty string + integer then java will try to cast integer into string
		//text.setText("" + currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + currentDate.getYear());
		// clear calendarSection before adding updating the content, otherwise it keeps adding 
		// to the current calendar
		calendarSection.removeAll();
		// draw day string
		String[] headers = {"S", "M", "T", "W", "T", "F", "S"};
		for(int i = 0; i < headers.length; i++) {
			JPanel panel = new JPanel();
			JLabel label = new CalendarDate(headers[i]);
			label.setHorizontalAlignment(JLabel.CENTER);
		    label.setVerticalAlignment(JLabel.CENTER);  
		    //calendarIndex++;
		    //datePanels[0][i] = panel;
		    //panel.putClientProperty(i, panel);
		    calendarSection.add(label);
		}
		// front padding
		LocalDate previousMonth = currentDate.minusMonths(1);
		for(int i = date - 1; i >= 0; i--) {
			monthSlots = new JPanel();
			monthSlots.setLayout(new BorderLayout());
			JLabel label = new CalendarDate("" + (previousMonth.getMonth().maxLength() - i));
			label.setHorizontalAlignment(JLabel.CENTER);
		    label.setVerticalAlignment(JLabel.CENTER); 
		    label.setForeground(Color.gray);
		    monthSlots.add(label, BorderLayout.NORTH);
		    JTextArea dateInfo = new JTextArea();
		    monthSlots.add(dateInfo, BorderLayout.CENTER);
		    if(calendarIndex % 7 == 0)
		    {
		    	datePanels[rowControl][columnControl] = monthSlots;
		    	//System.out.println("r" + rowControl);
		    	//System.out.println("c" + columnControl);
		    	rowControl++;
		    	columnControl = 0;
			    calendarIndex++;
		    }
		    else if(calendarIndex % 7 != 0)
		    {
		    	datePanels[rowControl][columnControl] = monthSlots;
			    calendarIndex++;
			    columnControl++;
			    System.out.println("r" + rowControl);
		    	System.out.println("c" + columnControl);
		    }
		    //calendarIndex++;
		    calendarSection.add(monthSlots);
		    
		}
		// draw dates
		for(int i = 1; i <= currentDate.getMonth().maxLength(); i++) {
			monthSlots = new JPanel();
			monthSlots.setLayout(new BorderLayout());
			JLabel label = new CalendarDate("" + i);
			label.setHorizontalAlignment(JLabel.CENTER);
		    label.setVerticalAlignment(JLabel.CENTER);    	    
		    if(
	    		i == currentDate.getDayOfMonth() && 
	    		currentDate.getMonth().getValue() == today.getMonth().getValue() &&
	    		currentDate.getYear() == today.getYear()
		    ) {
		    	Border border = BorderFactory.createLineBorder(Color.gray, 2);
		    	label.setBorder(border);
		    	label.setBackground(Color.LIGHT_GRAY);
		    	label.setOpaque(true);
		    }
		    monthSlots.add(label, BorderLayout.NORTH);
		    JTextArea dateInfo = new JTextArea();
		    monthSlots.add(dateInfo, BorderLayout.CENTER);
		    //calendarSection.add(panel);
		    if(calendarIndex % 7 == 0)
		    {
		    	datePanels[rowControl][columnControl] = monthSlots;
		    	System.out.println("r" + rowControl);
		    	System.out.println("c" + columnControl);
		    	rowControl++;
		    	columnControl = 0;
			    calendarIndex++;
		    }
		    else if(calendarIndex % 7 != 0)
		    {
		    	datePanels[rowControl][columnControl] = monthSlots;
			    calendarIndex++;
			    columnControl++;
			    System.out.println("r" + rowControl);
		    	System.out.println("c" + columnControl);

		    }
		    //System.out.println(calendarIndex);
		    calendarSection.add(monthSlots);
		    //calendarIndex++;
		    //rowControl = 1;
			//columnControl = 0;
			//calendarIndex = 1;
		}
		// rear padding
		int rearPaddingCount = 49 - currentDate.getMonth().maxLength() - headers.length - date;
		for(int i = 0; i < rearPaddingCount; i++) {
			monthSlots = new JPanel();
			monthSlots.setLayout(new BorderLayout());
			JLabel label = new CalendarDate(""+ (i+1));
			label.setHorizontalAlignment(JLabel.CENTER);
		    label.setVerticalAlignment(JLabel.CENTER); 
		    label.setForeground(Color.gray);
		    monthSlots.add(label, BorderLayout.NORTH);
		    JTextArea dateInfo = new JTextArea();
		    monthSlots.add(dateInfo, BorderLayout.CENTER);
		    /*
		    if(calendarIndex % 7 == 0)
		    {
		    	datePanels[rowControl][columnControl] = monthSlots;
		    	rowControl++;
		    	columnControl = 0;
			    calendarIndex++;
		    }
		    else
		    {
		    	datePanels[rowControl][columnControl] = monthSlots;
			    calendarIndex++;
		    }
		    //System.out.println(calendarIndex);
		   */
			calendarSection.add(monthSlots);
		}
		/*
		JPanel write = datePanels[3][3];
		JTextArea dateInfo = new JTextArea("Hello");
		monthSlots.add(dateInfo, BorderLayout.CENTER);
		write.add(dateInfo, BorderLayout.CENTER);
		*/
		
	}
	
	
	private void getData() {
		currentDate = model.getCurrentDate();
		today = model.getToday();
	}
	
	private void refresh() {
		getData();
		render();
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		refresh();
	}
}
