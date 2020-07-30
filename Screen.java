package calendar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.*;

public class Screen extends JFrame{
	private MonthNavigator monthNav;
	private DateNavigator todayButton;
	private DateNavigator rightNav;
	private DateNavigator leftNav;
	private Model model;
	private Controller controller;
	private LocalDate currentDate;
	Screen(Model model, Controller controller) {
		super();
		setLayout(new GridLayout(1,2));
		setSize(800, 400);
		setBackground(Color.BLACK);;
		this.model = model;
		this.controller = controller;
		CalendarView calendarView = new CalendarView(model, controller);
		model.attach(calendarView);
		
		add(calendarView,BorderLayout.CENTER);
		JPanel right = new JPanel();
		
		
		JPanel viewPanel = new JPanel();
		JPanel differentViews = new JPanel();
		viewPanel.setPreferredSize(new Dimension(385,320));
		JLabel dayText = new JLabel("DayView");
	//	viewPanel.add(dayText);
		
		differentViews.setLayout(new GridLayout(1,5));
		JButton dayView = new JButton("Day");
		JPanel dayViewPanel = new JPanel();	
		JButton weekView = new JButton("Week");
		JButton monthView = new JButton("Month");
		JButton AgendaView = new JButton("Agenda");	
		JButton create = new JButton("Create");
			
		differentViews.add(dayView);
		differentViews.add(weekView);
		differentViews.add(monthView);
		differentViews.add(AgendaView);
		differentViews.add(create);
		
		right.add(differentViews, BorderLayout.NORTH);
		
		//for viewPanel's Default day view.
		viewPanel.setBackground(Color.white);
		viewPanel.setOpaque(true);
		viewPanel.add(new JLabel("This is Day View"));
	
		right.add(viewPanel);
		
		add(right);
		//add(viewPanel, BorderLayout.CENTER);
		
	//	JLabel text = new JLabel("Day View");
		/**
		 * ActionListener for the dayView button
		 */
		dayView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewPanel.removeAll();
				viewPanel.setBackground(Color.white);
				viewPanel.setOpaque(true);
				viewPanel.add(new JLabel("This is Day View"));
				viewPanel.setBackground(Color.lightGray);
				viewPanel.setOpaque(true);
				
				viewPanel.revalidate();
				viewPanel.repaint();
				//viewPanel.add(dayViewPanel);
				
				
			}
		});
		
		weekView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewPanel.removeAll();
				viewPanel.setBackground(Color.white);
				viewPanel.setLayout(new FlowLayout());
				viewPanel.setOpaque(true);
				viewPanel.add(new JLabel("This is Week View"));
				viewPanel.revalidate();
				viewPanel.repaint();
				//viewPanel.add(dayViewPanel);
				
				
			}
		});
		MonthView month = new MonthView(model, controller);
		model.attach(month);
		
		monthView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewPanel.removeAll();
				viewPanel.setLayout(new FlowLayout());
				
			//	viewPanel.setBackground(Color.white);
			//	viewPanel.setOpaque(true);
			//	viewPanel.add(new JLabel("This is Month View"));
				month.setPreferredSize(new Dimension(350,270));
				viewPanel.add(month);
				
				
				viewPanel.revalidate();
				viewPanel.repaint();
				//viewPanel.add(dayViewPanel);
				
				
			}
		});
		
		JLabel StartDate = new JLabel("Start Date: ");
		JLabel EndDate = new JLabel("End Date: ");
		JPanel AgendaPanel = new JPanel();
		//JButton ok = new JButton("OK");
		AgendaView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*
				JOptionPane pane = new JOptionPane();
				pane.showInputDialog("Enter Start Date: ");
				JTextField sd= new JTextField();
				pane.showInputDialog(sd);
				pane.showInputDialog("Enter End Date");
				JTextField ed= new JTextField();
				pane.showInputDialog(ed);
			*/
				viewPanel.removeAll();
				viewPanel.setBackground(Color.lightGray);
				viewPanel.setOpaque(true);
				//viewPanel.setLayout(new GridLayout());
				viewPanel.setLayout(new FlowLayout());
				JPanel datePanel = new JPanel();
				
				datePanel.add(StartDate);
				JTextField first = new JTextField();
				first.setPreferredSize(new Dimension(60,20));
				datePanel.add(first);
				datePanel.add(EndDate);
				JTextField second = new JTextField();
				second.setPreferredSize(new Dimension(60,20));
				datePanel.add(second);
				datePanel.add(new JButton("Enter"));
				//AgendaPanel.setBackground();
				
				AgendaPanel.setPreferredSize(new Dimension(350,270));
				viewPanel.add(datePanel,BorderLayout.NORTH);
				AgendaPanel.add(new JLabel("Agenda List"));
				viewPanel.add(AgendaPanel, BorderLayout.CENTER);
				viewPanel.revalidate();
				viewPanel.repaint();
			}
			
		});
		
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewPanel.removeAll();
				viewPanel.setBackground(Color.lightGray);
				//viewPanel.setBackground(new Color(0,0,0));
				viewPanel.setOpaque(true);
				
				JLabel blank = new JLabel("       ");
			//	viewPanel.add(blank);
				
				JPanel eventPanel = new JPanel();
				eventPanel.setLayout(new GridLayout(5,1));
				JPanel eventName = new JPanel();
				JTextField eventNametf = new JTextField();
				eventName.add(new JLabel("Enter Event Name: "));
				eventNametf.setPreferredSize(new Dimension(150,20));
				eventName.add(eventNametf);
			//	viewPanel.add(blank);
				
				eventPanel.add(eventName);
				
				JPanel date = new JPanel();
				JTextField datetf = new JTextField();
				date.add(new JLabel("Enter Event Date:   "));
				datetf.setPreferredSize(new Dimension(150,20));
				date.add(datetf);
				eventPanel.add(date);
				
				JPanel startTime = new JPanel();
				JTextField startTimetf = new JTextField();
				startTime.add(new JLabel("Enter StartTime:    "));
				startTimetf.setPreferredSize(new Dimension(150,20));
				startTime.add(startTimetf);
				eventPanel.add(startTime);
				
				JPanel endTime = new JPanel();
				JTextField endTimetf = new JTextField();
				endTime.add(new JLabel("Enter EndTime:      "));
				endTimetf.setPreferredSize(new Dimension(150,20));
				endTime.add(endTimetf);
				eventPanel.add(endTime);
				
				JPanel submit = new JPanel();
				submit.add(new JButton("Enter"), BorderLayout.CENTER);
				//viewPanel.add(blank);
				//viewPanel.add(blank);
				
				eventPanel.add(submit);
				viewPanel.add(new JLabel("Create a new Event "), BorderLayout.NORTH);
				viewPanel.add(eventPanel, BorderLayout.CENTER);
				viewPanel.revalidate();
				viewPanel.repaint();
				//viewPanel.add(dayViewPanel);
				
				
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
