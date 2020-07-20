package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CalendarView extends JPanel implements ChangeListener{
	private JPanel navigationSection;
	private JPanel calendarSection;	
	private LocalDate currentDate;
	private LocalDate today;
	private JLabel text;
	private Model model;
	private Controller controller;
	
	private MonthNavigator rightNav;
	private MonthNavigator leftNav;
	
	// When an object is created, the constructor will be called only once
	public CalendarView(Model model, Controller controller) {
		super();
		setLayout(new BorderLayout());
		setSize(400, 400);
		
		this.model = model;
		this.controller = controller;
		
		navigationSection = new JPanel();
		setLayout(new BorderLayout());
		text = new JLabel();
		
		JPanel navBox = new JPanel();
		navBox.setLayout(new BorderLayout());
		leftNav = new MonthNavigator("<"); 
		leftNav.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.decreaseMonth(1);
			}	
		});
		rightNav = new MonthNavigator(">");
		rightNav.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.increaseMonth(1);
			}	
		});
		navBox.add(leftNav, BorderLayout.WEST);
		navBox.add(rightNav, BorderLayout.EAST);
		
		navigationSection.add(text, BorderLayout.WEST);
		navigationSection.add(navBox, BorderLayout.EAST);
		
		
		calendarSection = new JPanel();
		calendarSection.setLayout(new GridLayout(7, 7));
		
		
		add(navigationSection, BorderLayout.NORTH);
		add(calendarSection, BorderLayout.CENTER);
		
		
		// render is recalled many times when month or day changed
		refresh();
	}
	
	public void render() {
		LocalDate firstDateOfMonth = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), 1);
		int date = firstDateOfMonth.getDayOfWeek().getValue()%7;
		// Empty string + integer then java will try to cast integer into string
		text.setText("" + currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + currentDate.getYear());
		// clear calendarSection before adding updating the content, otherwise it keeps adding 
		// to the current calendar
		calendarSection.removeAll();
		// draw day string
		String[] headers = {"S", "M", "T", "W", "T", "F", "S"};
		for(int i = 0; i < headers.length; i++) {
			JLabel label = new CalendarDate(headers[i]);
			label.setHorizontalAlignment(JLabel.CENTER);
		    label.setVerticalAlignment(JLabel.CENTER);   
		    calendarSection.add(label);
		}
		// front padding
		LocalDate previousMonth = currentDate.minusMonths(1);
		for(int i = date - 1; i >= 0; i--) {
			JLabel label = new CalendarDate("" + (previousMonth.getMonth().maxLength() - i));
			label.setHorizontalAlignment(JLabel.CENTER);
		    label.setVerticalAlignment(JLabel.CENTER); 
		    label.setForeground(Color.gray);
			calendarSection.add(label);
		}
		// draw dates
		for(int i = 1; i <= currentDate.getMonth().maxLength(); i++) {
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
		    calendarSection.add(label);
		}
		// rear padding
		int rearPaddingCount = 49 - currentDate.getMonth().maxLength() - headers.length - date;
		for(int i = 0; i < rearPaddingCount; i++) {
			JLabel label = new CalendarDate(""+ (i+1));
			label.setHorizontalAlignment(JLabel.CENTER);
		    label.setVerticalAlignment(JLabel.CENTER); 
		    label.setForeground(Color.gray);
			calendarSection.add(label);
			
		}
	}
	private void getData() {
		currentDate = model.getCurrentDate();
		today = model.getToday();
	}
	
	private void refresh() {
		// Lock buttons
		rightNav.setEnabled(false);
		leftNav.setEnabled(false);
		
		getData();
		render();
		
		// Unlock buttons
		rightNav.setEnabled(true);
		leftNav.setEnabled(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		refresh();
	}
}
