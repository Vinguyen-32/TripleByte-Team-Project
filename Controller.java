package calendar;

import java.time.LocalDate;

public class Controller {
	private Model model;
	public Controller(Model model) {
		this.model = model;
	}
	public void increaseDay(int amount) {
		LocalDate currentDate = model.getCurrentDate();
		model.setCurrentDate(currentDate.plusDays(amount));
	}
	public void decreaseDay(int amount) {
		LocalDate currentDate = model.getCurrentDate();
		model.setCurrentDate(currentDate.minusDays(amount));
	}
	public void increaseMonth(int amount) {
		LocalDate currentDate = model.getCurrentDate();
		model.setCurrentDate(currentDate.plusMonths(amount));
	}
	public void decreaseMonth(int amount) {
		LocalDate currentDate = model.getCurrentDate();
		model.setCurrentDate(currentDate.minusMonths(amount));
	}
	public void increaseYear(int amount) {
		LocalDate currentDate = model.getCurrentDate();
		model.setCurrentDate(currentDate.plusYears(amount));
	}
	public void decreaseYear(int amount) {
		LocalDate currentDate = model.getCurrentDate();
		model.setCurrentDate(currentDate.minusYears(amount));
	}
}