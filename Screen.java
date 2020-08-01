package calendar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;

public class Screen extends JFrame{
	private MonthNavigator monthNav;
	private DateNavigator todayButton;
	private DateNavigator rightNav;
	private DateNavigator leftNav;
	private Model model;
	private Controller controller;
	private LocalDate currentDate;
	LocalDateTime startingTime;
	LocalDateTime endingTime;
	LocalDate key;
	private String name;
	private String monthDate;
	private String strtT;
	private String endT;
	private String fileT;
	private String readValue;
	private BufferedReader br;
	
	final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	Screen(Model model, Controller controller) {
		super();
		setLayout(new GridLayout(1,2));
		setSize(1600, 800);//400
		setBackground(Color.BLACK);;
		this.model = model;
		this.controller = controller;
		CalendarView calendarView = new CalendarView(model, controller);
		model.attach(calendarView);
		
		add(calendarView,BorderLayout.CENTER);
		JPanel right = new JPanel();
		
		
		JPanel viewPanel = new JPanel();
		JPanel differentViews = new JPanel();
		viewPanel.setPreferredSize(new Dimension(1600, 800));//385,320));
		JLabel dayText = new JLabel("DayView");
	//	viewPanel.add(dayText);
		
		differentViews.setLayout(new GridLayout(1,5));
		JButton dayView = new JButton("Day");
		JPanel dayViewPanel = new JPanel();	
		JButton weekView = new JButton("Week");
		JButton monthView = new JButton("Month");
		JButton AgendaView = new JButton("Agenda");	
		JButton create = new JButton("Create");
		JButton file = new JButton("File");
		
		differentViews.add(dayView);
		differentViews.add(weekView);
		differentViews.add(monthView);
		differentViews.add(AgendaView);
		differentViews.add(create);
		differentViews.add(file);
		
		right.add(differentViews, BorderLayout.NORTH);
		
		//for viewPanel's Default day view.
		viewPanel.setBackground(Color.white);
		viewPanel.setOpaque(true);
		viewPanel.add(new JLabel("This is Day View"));
	
		right.add(viewPanel);
		
		add(right);
		
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
				
				month.setPreferredSize(new Dimension(700,600));//320
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

				
				AgendaPanel.setPreferredSize(new Dimension(350,270));
				viewPanel.add(datePanel,BorderLayout.NORTH);
				AgendaPanel.add(new JLabel("Agenda List"));
				viewPanel.add(AgendaPanel, BorderLayout.CENTER);
				viewPanel.revalidate();
				viewPanel.repaint();
			}
			
		});
		JLabel eventLabel = new JLabel("Create a new Event ");
		
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewPanel.removeAll();
				viewPanel.setBackground(Color.lightGray);
				viewPanel.setOpaque(true);
				viewPanel.add(new JLabel("Create a new Event "), BorderLayout.NORTH);
				JLabel blank = new JLabel("       ");
			//	viewPanel.add(blank);
				
				JPanel eventPanel = new JPanel();
				eventPanel.setLayout(new GridLayout(5,1));
				JPanel eventName = new JPanel();
				JTextField eventNametf = new JTextField("Any Text");
				eventName.add(new JLabel("Enter Event Name: "));
				eventNametf.setPreferredSize(new Dimension(150,20));
				eventName.add(eventNametf);
			//	viewPanel.add(blank);
				
				eventPanel.add(eventName);
				
				JPanel date = new JPanel();
				JTextField datetf = new JTextField("Form: YYYY-MM-DD");
				date.add(new JLabel("Enter Event Date:   "));
				datetf.setPreferredSize(new Dimension(150,20));
				date.add(datetf);
				eventPanel.add(date);
				
				JPanel startTime = new JPanel();
				JTextField startTimetf = new JTextField("Format :HH");
				startTime.add(new JLabel("Enter StartTime:    "));
				startTimetf.setPreferredSize(new Dimension(150,20));
				startTime.add(startTimetf);
				eventPanel.add(startTime);
				
				JPanel endTime = new JPanel();
				JTextField endTimetf = new JTextField("Format :HH");
				endTime.add(new JLabel("Enter EndTime:      "));
				endTimetf.setPreferredSize(new Dimension(150,20));
				endTime.add(endTimetf);
				eventPanel.add(endTime);
				JPanel submit = new JPanel();
				JButton enter = new JButton("Enter");

				enter.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
						name = eventNametf.getText();
						monthDate = datetf.getText();
						String[] mDate = monthDate.split("-", 3);
						String year = mDate[0];
						String mon = mDate[1];
						String day = mDate[2];
						strtT = startTimetf.getText();
						endT = endTimetf.getText();
						if(mDate[1].length() < 2)
						{
							mon = "0" + mDate[1];
						}
						if(mDate[2].length() < 2)
						{
							day = "0" + mDate[2];
						}
						if(strtT.length() < 2)
						{
							strtT = "0" + strtT;
						}
						if(endT.length() < 2)
						{
							endT = "0" + endT;
						}
						String parseStart;;
						String parseEnd;
						System.out.println("strt" + strtT);
						System.out.println("I am day" + day);
						System.out.println("end" + endT);
						System.out.println("year" + year);
						String keyDate;
						keyDate = year + "-" + mon + "-" + day; 
						key = LocalDate.parse(keyDate, DATE_TIME);
						parseStart = year + "-" + mon + "-" + day + " " + strtT + ":" + "01";
						parseEnd = year + "-" + mon + "-" + day + " " +  endT + ":" + "01";
						System.out.println("start" + parseStart);
						System.out.println("end" + parseEnd);

						startingTime = LocalDateTime.parse(parseStart, DATE_TIME_FORMATTER);
						LocalDateTime startingTimeTemp = LocalDateTime.parse(parseStart, DATE_TIME_FORMATTER);
						endingTime = LocalDateTime.parse(parseEnd, DATE_TIME_FORMATTER);
						boolean timeConflict = false;
						LocalDateTime updatedCheckTime = startingTime;
						ArrayList<LocalDateTime> checkTime = new ArrayList<LocalDateTime>();
						while(!updatedCheckTime.isEqual(endingTime))
						{
							checkTime.add(updatedCheckTime);
							updatedCheckTime = updatedCheckTime.plusMinutes(1);
							System.out.println("updated" + updatedCheckTime);
						}
						if(model.getOneTime().get(key) != null)
						{
							ArrayList<Event> currentEvents = model.getOneTime().get(key);
						for(Event event: currentEvents)
						{
							LocalDateTime tempHoldOriginal = event.getTimeInterval().getStartingTime();
							LocalDateTime changeDateList = event.getTimeInterval().getStartingTime();
							ArrayList<LocalDateTime> times = new ArrayList<LocalDateTime>();
							while(!changeDateList.isEqual(event.getTimeInterval().getEndingTime()))
							{
								times.add(changeDateList);
								changeDateList = changeDateList.plusMinutes(1);
								System.out.println("List time" + changeDateList);
							}
							for(LocalDateTime timeInList: times)
							{
								for(LocalDateTime timeInsert: checkTime)
								{
									if(timeInList.isEqual(timeInsert))
									{
										timeConflict = true;
									}
								}
							}
							//currentEvents.clear();
						}
						}
							if(timeConflict == false)
							{
							TimeInterval eventTime = new TimeInterval(startingTime, endingTime);
							Event selectEvent = new Event(name, eventTime);
							controller.addOneTime(key, selectEvent);
							eventPanel.removeAll();
							eventPanel.add(new JTextField("Successful"));
							eventPanel.setBackground(Color.lightGray);
							eventPanel.setOpaque(true);
							eventPanel.revalidate();
							eventPanel.repaint();
							}
							else
							{
								eventPanel.removeAll();
								viewPanel.remove(eventLabel);
								eventPanel.add(new JTextField("Time Conflict"));
								eventPanel.revalidate();
								eventPanel.repaint();
								eventPanel.setVisible(true);
							}
							} catch(Exception e1)
							{
								eventPanel.removeAll();
								viewPanel.remove(eventLabel);
								eventPanel.add(new JTextField("Invalid format hit create again"));
								eventPanel.revalidate();
								eventPanel.repaint();
								eventPanel.setVisible(true);
							
							}
						}
					});
				
				submit.add(enter, BorderLayout.CENTER);			
				eventPanel.add(submit);
				viewPanel.add(eventPanel, BorderLayout.CENTER);
				viewPanel.revalidate();
				viewPanel.repaint();			
			}
		});
		/**
		 * viewPanel.removeAll();
				viewPanel.setLayout(new FlowLayout());
				
				month.setPreferredSize(new Dimension(350,270));
				viewPanel.add(month);
				
				
				viewPanel.revalidate();
				viewPanel.repaint()
		 */
		file.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewPanel.removeAll();
				viewPanel.setBackground(Color.lightGray);
				viewPanel.setOpaque(true);
				viewPanel.add(new JLabel("Insert File Path"), BorderLayout.NORTH);
				JButton save = new JButton("Save");
				JPanel eventName = new JPanel();
				JTextField eventNametf = new JTextField("File Directory Path");
				save.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						fileT = eventNametf.getText();
						try {
							br = new BufferedReader(new FileReader(fileT));
						} catch (FileNotFoundException e1) {
							System.out.println("Put a valid file path");
						}
						try {
							while((br.ready()))
							{
								readValue = br.readLine();
								String[] dateValues = readValue.split(";", 7);
								String year = dateValues[1];
								String month = dateValues[2];
								String monthEnd = dateValues[3];
								Integer monthInt = Integer.parseInt(monthEnd);
								if(monthInt == 12)
								{
									monthInt = 1;
								}
								else
								{
									monthInt++;
								}
								monthEnd = Integer.toString(monthInt);
								String recDays = dateValues[4];
								String strTime = dateValues[5];
								String endTime = dateValues[6];
								
								if(dateValues[2].length() < 2)
								{
									month = "0" + dateValues[2];
								}
								if(monthEnd.length() < 2)
								{
									monthEnd = "0" + dateValues[3];
								}
								if(strTime.length() < 2)
								{
									strTime = "0" + strTime;
								}if(endTime.length() < 2)
								{
									endTime = "0" + endTime;
								}
								name = dateValues[0];
								strTime = strTime.substring(0,2);
								endTime = endTime.substring(0,2);
								String day = "01";
								String start = year + "-" + month + "-" + day;
								String end = year + "-" + monthEnd + "-" + day;
								System.out.println("start" + start);
								System.out.println("end" + end);
								LocalDate starter = LocalDate.parse(start, DATE_TIME);
								LocalDate ender = LocalDate.parse(end, DATE_TIME);
								System.out.println("I came here");
								boolean holder = starter.isEqual(ender);
								System.out.println("I am holder" + holder);
								ArrayList<String> days = new ArrayList<String>();
								if(recDays.contains("M"))
								{
									String mon = "MONDAY";
									days.add(mon);
								}
								if(recDays.contains("T"))
								{
									String tue = "TUESDAY";
									days.add(tue);
								}
								if(recDays.contains("W"))
								{
									String wed = "WEDNESDAY";
									days.add(wed);
								}
								if(recDays.contains("H"))
								{
									String thur = "THURSDAY";
									days.add(thur);
								}
								if(recDays.contains("F"))
								{
									String fri = "FRIDAY";
									days.add(fri);
								}
								if(recDays.contains("A"))
								{
									String sat = "SATURDAY";
									days.add(sat);
								}
								if(recDays.contains("S"))
								{
									String sun = "SUNDAY";
									days.add(sun);
								}
								while(!starter.isEqual(ender))
								{
									for(String s: days)
									{
									  if(starter.getDayOfWeek().toString().equals(s))
									  {
										  System.out.println("I entered");
											String currYear = Integer.toString(starter.getYear());
											System.out.println("HELLO");
											System.out.println("I am changing" + currYear);
											String currDay = Integer.toString(starter.getDayOfMonth());
											String currMonth = Integer.toString(starter.getMonthValue());
											if(currDay.length() < 2)
											{
												currDay = "0" + currDay; 
											}
											if(currMonth.length() < 2)
											{
												currMonth = "0" + currMonth; 
											}
											String keyTemp = currYear + "-" + currMonth + "-" + currDay;
											LocalDate key = LocalDate.parse(keyTemp, DATE_TIME);
											String parseStart = currYear + "-" + currMonth + "-" + currDay + " " + strTime + ":" + "01";
											System.out.println("Hello" + parseStart);
											String parseEnd = currYear + "-" + currMonth + "-" + currDay + " " +  endTime + ":" + "01";
											startingTime = LocalDateTime.parse(parseStart, DATE_TIME_FORMATTER);
											System.out.println("I AM START" + startingTime);
											endingTime = LocalDateTime.parse(parseEnd, DATE_TIME_FORMATTER);
											System.out.println("I AM END" + endingTime);
											TimeInterval eventTime = new TimeInterval(startingTime, endingTime);
											Event selectEvent = new Event(name, eventTime);
											controller.addOneTime(key, selectEvent);
									  	}
									}	
									starter = starter.plusDays(1);
								}
							}
							} catch (IOException e2) {
								
							}
					
					}
				});
				eventNametf.setPreferredSize(new Dimension(375,20));
				eventName.add(eventNametf);
				eventName.add(save);
				viewPanel.add(eventName, BorderLayout.CENTER);
				viewPanel.revalidate();
				viewPanel.repaint();	
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
