package ca.mcgill.ecse.btms.view;

import java.awt.Color;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse.btms.controller.BtmsController;
import ca.mcgill.ecse.btms.controller.InvalidInputException;

public class BtmsPage extends JFrame {

	private static final long serialVersionUID = -4426310869335015542L;
	
	// UI elements
	private JLabel errorMessage;
	// driver
	private JTextField driverNameTextField;
	private JLabel driverNameLabel;
	private JButton addDriverButton;
	private JComboBox<String> driverToggleList;
	private JLabel driverToggleLabel;
	private JButton sickButton;
	private JButton deleteDriverButton;
	// route
	private JTextField routeNumberTextField;
	private JLabel routeNumberLabel;
	private JButton addRouteButton;
	// bus
	private JTextField busLicencePlateTextField;
	private JLabel busLicencePlateLabel;
	private JButton addBusButton;
	private JComboBox<String> busToggleList;
	private JLabel busToggleLabel;
	private JButton repairButton;
	// bus assignment
	private JComboBox<String> busList;
	private JLabel busLabel;
	private JComboBox<String> routeList;
	private JLabel routeLabel;
	private JDatePickerImpl assignmentDatePicker;
	private JLabel assignmentDateLabel;
	private JButton assignButton;
	// schedule driver
	// TODO Step i: define UI elements
	// daily overview
	// TODO Step i: define UI elements

	// data elements
	private String error = null;
	// toggle sick status
	// private HashMap<Integer, Integer> drivers;
	// toggle repairs status
	// private HashMap<Integer, String> buses;
	// bus assignment
	// private HashMap<Integer, String> availableBuses;
	// private HashMap<Integer, TORoute> routes;
	// schedule driver
	// TODO Step i: define data elements
	// daily overview
	// TODO Step i: define data elements

	/** Creates new form BtmsPage */
	public BtmsPage() {
		initComponents();
		refreshData();
	}

	/** This method is called from within the constructor to initialize the form.
	 */
	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		// elements for driver
		driverNameTextField = new JTextField();
		driverNameLabel = new JLabel();
		driverNameLabel.setText("Name:");
		addDriverButton = new JButton();
		addDriverButton.setText("Add Driver");
		driverToggleList = new JComboBox<String>(new String[0]);
		driverToggleLabel = new JLabel();
		driverToggleLabel.setText("Select Driver:");
		sickButton = new JButton();
		sickButton.setText("Toggle Sick");
		deleteDriverButton = new JButton();
		deleteDriverButton.setText("Delete");
		
		// elements for route
		routeNumberTextField = new JTextField();
		routeNumberLabel = new JLabel();
		routeNumberLabel.setText("Number:");
		addRouteButton = new JButton();
		addRouteButton.setText("Add Route");
		
		// elements for bus
		busLicencePlateTextField = new JTextField();
		busLicencePlateLabel = new JLabel();
		busLicencePlateLabel.setText("Licence Plate:");
		addBusButton = new JButton();
		addBusButton.setText("Add Bus");
		busToggleList = new JComboBox<String>(new String[0]);
		busToggleLabel = new JLabel();
		busToggleLabel.setText("Select Bus:");
		repairButton = new JButton();
		repairButton.setText("Toggle Repair");
		
		// elements for bus assignment
		busList = new JComboBox<String>(new String[0]);
		busLabel = new JLabel();
		busLabel.setText("Select Bus:");
		routeList = new JComboBox<String>(new String[0]);
		routeLabel = new JLabel();
		routeLabel.setText("Select Route:");
		
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		assignmentDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		assignmentDateLabel = new JLabel();
		assignmentDateLabel.setText("Date:");
		
		assignButton = new JButton();
		assignButton.setText("Assign");
		
		// elements for schedule driver
		// TODO Step ii: initialize UI elements
		
		// elements for daily overview
		// TODO Step ii: initialize UI elements
		
		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Bus Transportation Management System");

		// listeners for driver
		addDriverButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addDriverButtonActionPerformed(evt);
			}
		});
		sickButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sickButtonActionPerformed(evt);
			}
		});
		deleteDriverButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteDriverButtonActionPerformed(evt);
			}
		});
		
		// listeners for route
		addRouteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addRouteButtonActionPerformed(evt);
			}
		});

		// listeners for bus
		addBusButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addBusButtonActionPerformed(evt);
			}
		});
		repairButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				repairButtonActionPerformed(evt);
			}
		});
		
		// listeners for bus assignment
		assignButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				assignButtonActionPerformed(evt);
			}
		});
		
		// listeners for schedule driver
		// TODO Step ii: add action listeners for UI elements
		
		// listeners for daily overview
		// TODO Step ii: add action listeners for UI elements
		
		// horizontal line elements
		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineMiddle1 = new JSeparator();
		JSeparator horizontalLineMiddle2 = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addComponent(horizontalLineTop)
				.addComponent(horizontalLineMiddle1)
				.addComponent(horizontalLineMiddle2)
				.addComponent(horizontalLineBottom)
				//TODO Step iii: update horizontal layout (add overviewScrollPane)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(driverNameLabel)
								.addComponent(driverToggleLabel)
								.addComponent(busLabel)
								//TODO Step iii: update horizontal layout (add driverLabel)
								)
						.addGroup(layout.createParallelGroup()
								.addComponent(driverNameTextField, 200, 200, 400)
								.addComponent(addDriverButton)
								.addComponent(driverToggleList)
								.addGroup(layout.createSequentialGroup()
										.addComponent(sickButton, 110, 110, 220)
										.addComponent(deleteDriverButton, 70, 70, 140))
								.addComponent(busList)
								// TODO Step iii: update horizontal layout (add driverList)
								)
						.addGroup(layout.createParallelGroup()
								.addComponent(routeNumberLabel)
								.addComponent(busToggleLabel)
								.addComponent(routeLabel)
								// TODO Step iii: update horizontal layout (add assignmentLabel)
								)
						.addGroup(layout.createParallelGroup()
								.addComponent(routeNumberTextField, 200, 200, 400)
								.addComponent(addRouteButton)
								.addComponent(busToggleList)
								.addComponent(repairButton)
								.addComponent(routeList)
								// TODO Step iii: update horizontal layout (add assignmentList)
								)
						.addGroup(layout.createParallelGroup()
								.addComponent(busLicencePlateLabel)
								.addComponent(assignmentDateLabel)
								// TODO Step iii: update horizontal layout (add shiftLabel)
								// TODO Step iii: update horizontal layout (add overviewDateLabel)
								)
						.addGroup(layout.createParallelGroup()
								.addComponent(busLicencePlateTextField, 200, 200, 400)
								.addComponent(addBusButton)
								.addComponent(assignmentDatePicker)
								.addComponent(assignButton)
								// TODO Step iii: update horizontal layout (add shiftList)
								// TOOD Step iii: update horizontal layout (add scheduleButton)
								// TODO Step iii: update horizontal layout (add overviewDatePicker)
								))
				);

		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {assignButton, assignmentDatePicker});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {repairButton, routeNumberTextField});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addDriverButton, driverNameTextField});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addRouteButton, routeNumberTextField});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addBusButton, busLicencePlateTextField});
		//TODO Step iii: control size of UI elements
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(driverNameLabel)
						.addComponent(driverNameTextField)
						.addComponent(routeNumberLabel)
						.addComponent(routeNumberTextField)
						.addComponent(busLicencePlateLabel)
						.addComponent(busLicencePlateTextField))		
				.addGroup(layout.createParallelGroup()
						.addComponent(addDriverButton)
						.addComponent(addRouteButton)
						.addComponent(addBusButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(horizontalLineTop))
				.addGroup(layout.createParallelGroup()
						.addComponent(driverToggleLabel)
						.addComponent(driverToggleList)
						.addComponent(busToggleLabel)
						.addComponent(busToggleList))
				.addGroup(layout.createParallelGroup()
						.addComponent(sickButton)
						.addComponent(deleteDriverButton)
						.addComponent(repairButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(horizontalLineMiddle1))
				.addGroup(layout.createParallelGroup()
						.addComponent(busLabel)
						.addComponent(busList)
						.addComponent(routeLabel)
						.addComponent(routeList)
						.addComponent(assignmentDateLabel)
						.addComponent(assignmentDatePicker))
				.addComponent(assignButton)
				.addGroup(layout.createParallelGroup()
						.addComponent(horizontalLineMiddle2))
				// TODO Step iii: update vertical layout (add parallel group for driverLabel, driverList, assignmentLabel, 
				// assignmentList, shiftLabel, and shiftList)
				// TODO Step iii: update vertical layout (add scheduleButton)
				.addGroup(layout.createParallelGroup()
						.addComponent(horizontalLineBottom))
				// TODO Step iii: update vertical layout (add parallel group for overviewDateLabel and overviewDatePicker)
				// TODO Step iii: update vertical layout (add overviewScrollPane)
				);
		
		pack();
	}

	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			// populate page with data
			// driver
			driverNameTextField.setText("");
			// route
			routeNumberTextField.setText("");
			
			// TODO Step iv: refresh data (update method body)
		}
		
		// daily overview
		refreshDailyOverview();

		// this is needed because the size of the window changes depending on whether an error message is shown or not
		pack();
	}

	private void refreshDailyOverview() {
		// TODO Step iv: refresh daily overview (add whole method body)
	}

	private void addDriverButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = null;
		
		// call the controller
		try {
			BtmsController.createDriver(driverNameTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// update visuals
		refreshData();
	}
	
	private void sickButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int selectedSickDriver = driverToggleList.getSelectedIndex();
		if (selectedSickDriver < 0)
			error = "Driver needs to be selected to toggle sick status!";
		
		if (error.length() == 0) {
			// call the controller
			// TODO Step v: complete this ”ActionPerformed” method
		}
		
		// update visuals
		refreshData();
	}
	
	private void deleteDriverButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int selectedSickDriver = driverToggleList.getSelectedIndex();
		if (selectedSickDriver < 0)
			error = "Driver needs to be selected for deletion!";
		
		if (error.length() == 0) {
			// call the controller
			// TODO Step v: complete this ”ActionPerformed” method
		}
		
		// update visuals
		refreshData();
	}

	private void addRouteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int routeNumber = 0;
		try {
			routeNumber = Integer.parseInt(routeNumberTextField.getText());
		}
		catch (NumberFormatException e) {
			error = "Route number needs to be a numerical value!";
		}
		
		if (error.length() == 0) {
			// call the controller
			try {
				BtmsController.createRoute(routeNumber);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		// update visuals
		refreshData();
	}
	
	private void addBusButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = null;

		// call the controller
		// TODO Step v: complete this ”ActionPerformed” method

		// update visuals
		refreshData();
	}
	
	private void repairButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int selectedRepairBus = busToggleList.getSelectedIndex();
		if (selectedRepairBus < 0)
			error = "Bus needs to be selected to toggle repair status!";

		if (error.length() == 0) {
			// call the controller
			// TODO Step v: complete this ”ActionPerformed” method
		}
		
		// update visuals
		refreshData();
	}
	
	private void assignButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int selectedBus = busList.getSelectedIndex();
		if (selectedBus < 0)
			error = "Bus needs to be selected for assignment! ";
		int selectedRoute = routeList.getSelectedIndex();
		if (selectedRoute < 0)
			error = error + "Route needs to be selected for assignment! ";
		if (assignmentDatePicker.getModel().getValue() == null)
			error = error + "Date needs to entered for assignment!";
		error = error.trim();

		if (error.length() == 0) {
			// call the controller
			// TODO Step v: complete this ”ActionPerformed” method
		}

		// update visuals
		refreshData();			
	}
	
	// TODO Step v: implement the ”ActionPerformed” method for "Schedule Driver"

}
