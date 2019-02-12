package ca.mcgill.ecse.btms.application;

import java.sql.Date;
import java.util.Calendar;
import ca.mcgill.ecse.btms.model.*;

public class BtmsApplication {
	
	private static BTMS btms;

	private static int counter = 1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Step 1
		BTMS btms = new BTMS();
		printBtmsStatistics(btms);
		
		// Step 2
		btms.addVehicle("MYBUS09", false);
		btms.addRoute(1);
		btms.addDriver("Jo", false);
		printBtmsStatistics(btms);
		
		// Step 3
		BusVehicle bus1 = btms.getVehicle(0);
		Route route1 = btms.getRoute(0);
		Driver driver1 = btms.getDriver(0);
		printBtmsStatistics(btms);
		
		// Step 4
		BusVehicle bus2 = new BusVehicle("MYBUS10", false, btms);
		Route route2 = new Route(2, btms);
		Driver driver2 = new Driver("Alan", false, btms);
		printBtmsStatistics(btms);
		
		// Step 5
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		Date date = new Date(currentDate.getTime());
		RouteAssignment agmnt1 = new RouteAssignment(date, bus1, route1, btms);
		RouteAssignment agmnt2 = new RouteAssignment(date, bus2, route2, btms);
		printBtmsStatistics(btms);
		
		// Step 6
		DriverSchedule schedule1 = new DriverSchedule(DriverSchedule.Shift.Afternoon, driver1, agmnt1, btms);
		DriverSchedule schedule2 = new DriverSchedule(DriverSchedule.Shift.Morning, driver2, agmnt2, btms);
		printBtmsStatistics(btms);
		
		// Step 7
		// Umple prevents the removal of schedule1 from driver1 with checks in the removeDriverSchedule() method, 
		// because removing a schedule from a driver also means removing the driver from the schedule. However, a 
		// schedule must have a driver (multiplicity is 1), and hence removeDriverSchedule() must not work in this case.
		driver1.removeDriverSchedule(schedule1);
		printBtmsStatistics(btms);
		
		// Step 8
		// Umple prevents the removal of schedule2 from driver1 with checks in the removeDriverSchedule() method, 
		// because driver1 is not the driver of schedule2. driver2 is the driver of schedule2, and hence 
		// removeDriverSchedule() must not work in this case.
		driver1.removeDriverSchedule(schedule2);
		printBtmsStatistics(btms);
		
		// Step 9
		schedule1.setDriver(driver2);
		printBtmsStatistics(btms);
		
		// Step 10
		schedule1.delete();
		printBtmsStatistics(btms);
	}
	
	public static int increaseCounter() {
		return counter++;
	}
	
	private static void printBtmsStatistics(BTMS btms) {
		System.out.println("===== BTMS: Step " + increaseCounter() +" =====");
		System.out.println("Date: " + btms.getCurrentDate());
		System.out.println("Number of buses: " + btms.getVehicles().size());
		System.out.println("Number of routes: " + btms.getRoutes().size());
		System.out.println("Number of drivers: " + btms.getDrivers().size());
		System.out.println("Number of assignments: " + btms.getAssignments().size());
		System.out.println("Number of schedules: " + btms.getSchedule().size());
		for (BusVehicle bus : btms.getVehicles()) {
			System.out.println("Bus: " + bus.getLicencePlate() + " (in repair: " + bus.getInRepairShop() + ") / #Assignments: " + bus.getRouteAssignments().size());
		}
		for (Route route : btms.getRoutes()) {
			System.out.println("Route: " + route.getNumber() + " / #Assignments: " + route.getRouteAssignments().size());
		}
		for (Driver driver : btms.getDrivers()) {
			System.out.println("Driver: " + driver.getName() + " (sick: " + driver.getOnSickLeave() + ") / #Schedules: " + driver.getDriverSchedules().size());
		}
		for (RouteAssignment assignment : btms.getAssignments()) {
			System.out.println("Assignment: " + assignment.getBus().getLicencePlate() + ", " + assignment.getRoute().getNumber() + ", Date: " + assignment.getDate() +
					" / #Schedules: " + assignment.getDriverSchedules().size());
		}
		for (DriverSchedule schedule : btms.getSchedule()) {
			System.out.println("Schedule: " + schedule.getDriver().getName() + ", " + schedule.getAssignment().getBus().getLicencePlate() + 
					", " + schedule.getAssignment().getRoute().getNumber() + ", Date: " + schedule.getAssignment().getDate() + " " + schedule.getShift().toString());
		}
		System.out.println("========================");
	}

	public static BTMS getBtms() {
		if (btms == null) {
			btms = new BTMS();
		}
 		return btms;
	}
	
}
