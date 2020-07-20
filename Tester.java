package calendar;

import java.time.LocalDate;

import javax.swing.JFrame;

public class Tester {
	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setSize(300, 300);
		
		LocalDate today = LocalDate.now();
		
		Model model = new Model(today, today);
		Controller controller = new Controller(model);
		CalendarView calendarView = new CalendarView(model, controller);
		model.attach(calendarView);
		
//		frame.add(calendarView);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
		
		
		Screen screen = new Screen(model, controller);
		
		
	}
}
