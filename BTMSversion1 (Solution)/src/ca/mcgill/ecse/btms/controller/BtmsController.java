package ca.mcgill.ecse.btms.controller;

import java.sql.Date;
import java.util.Calendar;

import ca.mcgill.ecse.btms.application.BtmsApplication;
import ca.mcgill.ecse.btms.model.BTMS;
import ca.mcgill.ecse.btms.model.BusVehicle;
import ca.mcgill.ecse.btms.model.Driver;
import ca.mcgill.ecse.btms.model.DriverSchedule.Shift;
import ca.mcgill.ecse.btms.model.Route;
import ca.mcgill.ecse.btms.model.RouteAssignment;

public class BtmsController {

	public BtmsController() {
	}
	
	public static void createDriver(String name) throws InvalidInputException {
		BTMS btms = BtmsApplication.getBtms();
		try {
			btms.addDriver(name, false);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void deleteDriver(int id) {
		Driver driver = findDriver(id);
		if (driver != null) {
			driver.delete();
		}
	}

	public static void createRoute(int number) throws InvalidInputException {
		String error = "";
		if (number <= 0) {
			error = "The number of a route must be greater than zero. ";
		}
		if (number > 9999) {
			error = "The number of a route cannot be greater than 9999. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		BTMS btms = BtmsApplication.getBtms();
		try {
			btms.addRoute(number);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("Cannot create due to duplicate number")) {
				error = "A route with this number already exists. Please use a different number.";
			}
			throw new InvalidInputException(error);
		}		
	}
	
	public static void createBus(String licencePlate) throws InvalidInputException {
		BTMS btms = BtmsApplication.getBtms();
		try {
			btms.addVehicle(licencePlate, false);
		}
		catch (RuntimeException e) {
			String error = e.getMessage();
			if (error.equals("Cannot create due to duplicate licencePlate")) {
				error = "A bus with this licence plate already exists. Please use a different licence plate.";
			}
			throw new InvalidInputException(error);
		}
	}
	
	public static void toggleSickStatus(int id) throws InvalidInputException {
		Driver driver = findDriver(id);
		if (driver == null) {
			throw new InvalidInputException("An existing driver must be specified to toggle the driver's status.");
		}
		driver.setOnSickLeave(!driver.getOnSickLeave());
	}

	public static void toggleRepairStatus(String licencePlate) throws InvalidInputException {
		BusVehicle bus = BusVehicle.getWithLicencePlate(licencePlate);
		if (bus == null) {
			throw new InvalidInputException("An existing bus must be specified to toggle the bus' status.");
		}
		bus.setInRepairShop(!bus.getInRepairShop());
	}
	
	public static void assign(String licencePlate, int number, Date date) throws InvalidInputException {
		String error = "";
		date = cleanDate(date);
		if (!isWithin365DaysFromToday(date)) {
			error = "The date must be within a year from today. ";
		}
		BusVehicle bus = BusVehicle.getWithLicencePlate(licencePlate);
		if (bus == null) {
			error = error + "A bus must be specified for the assignment. ";
		}
		Route route = Route.getWithNumber(number);
		if (route == null) {
			error = error + "A route must be specified for the assignment.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		BTMS btms = BtmsApplication.getBtms();
		try {
			RouteAssignment assignment = bus.getAssignmentOnDate(date);
			if (assignment == null) {
				btms.addAssignment(date, bus, route);				
			}
			else {
				assignment.setRoute(route);
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void schedule(int driverID, Date date, int number, String licencePlate, String shiftName) throws InvalidInputException {
		String error = "";
		Driver driver = findDriver(driverID);
		if (driver == null) {
			error = "A driver must be specified for the schedule. ";
		}
		RouteAssignment assignment = findRouteAssignment(date, number, licencePlate);
		if (assignment == null) {
			error = error + "An assignment must be specified for the schedule. ";
		}
		Shift shift = null;
		try {
			shift = Shift.valueOf(shiftName);
		}
		catch (IllegalArgumentException e) {
			error = error + "A shift must be specified for the schedule.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		BTMS btms = BtmsApplication.getBtms();
		try {
			btms.addSchedule(shift, driver, assignment);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	private static Date cleanDate(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeInMillis(date.getTime());
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    java.util.Date tempCleanedDate = cal.getTime();
	    java.sql.Date cleanedDate = new java.sql.Date(tempCleanedDate.getTime());
	    return cleanedDate;
	}

	private static boolean isWithin365DaysFromToday(Date date) {
		java.util.Date tempToday = BtmsApplication.getBtms().getCurrentDate();
		java.util.Date tempInOneYear = new java.util.Date(tempToday.getTime() + 366*24*60*60*1000L);
		java.sql.Date inOneYear = new java.sql.Date(tempInOneYear.getTime());
		return date.before(inOneYear);
	}
	
	private static Driver findDriver(int id) {
		Driver foundDriver = null;
		for (Driver driver : BtmsApplication.getBtms().getDrivers()) {
			if (driver.getId() == id) {
				foundDriver = driver;
				break;
			}
		}
		return foundDriver;
	}

	private static RouteAssignment findRouteAssignment(Date date, int number, String licencePlate) {
		RouteAssignment foundAssignment = null;
		for (RouteAssignment assignment : BtmsApplication.getBtms().getAssignments()) {
			if (assignment.getDate().equals(date) && assignment.getRoute().getNumber() == number && assignment.getBus().getLicencePlate().equals(licencePlate)) {
				foundAssignment = assignment;
				break;
			}
		}
		return foundAssignment;
	}
	
}
