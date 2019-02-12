package ca.mcgill.ecse.btms.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import ca.mcgill.ecse.btms.controller.TOBusStop;
import ca.mcgill.ecse.btms.controller.TORoute;

class BusRouteVisualizer extends JPanel {

	private static final long serialVersionUID = 5765666411683246454L;

	// UI elements
	private List<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
	private static final int LINEX = 30;
	private static final int LINETOPY = 30; 
	int lineHeight;
	private static final int RECTWIDTH = 40;
	private static final int RECTHEIGHT = 20;
	private static final int SPACING = 10;
	private static final int MAXNUMBEROFBUSSTOPSSHOWN = 10;

	// data elements
	private TORoute route;
	private HashMap<Rectangle2D, TOBusStop> busStops;
	private int selectedBusStop;
	private int firstVisibleBusStop;

	public BusRouteVisualizer() {
		super();
		init();
	}

	private void init() {
		route = null;
		busStops = new HashMap<Rectangle2D, TOBusStop>();
		selectedBusStop = 0;
		firstVisibleBusStop = 0;
		// TODO (Step 2.iv) add a mouse listener to detect if a rectangle was clicked; if yes, save the corresponding bus stop in selectedBusStop;
		// call repaint() to redraw the graphics
	}

	public void setRoute(TORoute route) {
		// TODO (Step 2.iii) initialize the route transfer object and all other fields; call repaint() to redraw the graphics
	}

	public void moveUp() {
		// TODO (Step 2.v) decrease the firstVisibleBusStop by one unless it is already 0; call repaint() to redraw the graphics
	}

	public void moveDown() {
		// TODO (Step 2.v) increase the firstVisibleBusStop by one unless the last bus stop is already shown; call repaint() to redraw the graphics
	}

	private void doDrawing(Graphics g) {
		// TODO (Step 2.iii) replace the current implementation with the following:
		// - only proceed if the route transfer object is not null
		// - get the number of bus stops from the route transfer object
		// - draw a line that is long enough to show all bus stops
		// - use the already provided getBusStopsForRoute() in the BtmsController to loop through a list of bus stop transfer objects
		// - draw a rectangle for each bus stop, evenly spaced from its neighboring rectangles
		// - draw a string for the actual number of the bus stop inside each rectangle
		// - get the number from the bus stop transfer object
		// - while doing so setup the HashMap to keep track of which rectangle corresponds to which bus stop
		// - also add each rectangle to the list of rectangles
		// TODO (Step 2.iv)
		// - draw a string next to the rectangle corresponding to the selectedBusStop to show the minutes from the first stop
		// - get the minutes from the bus stop transfer object
		// TODO (Step 2.v)
		// - limit the number of bus stops shown to MAXNUMBEROFBUSSTOPSSHOWN
		// - only show the bus stops from the firstVisibleBusStop
		Graphics2D g2d = (Graphics2D) g.create();
		BasicStroke thickStroke = new BasicStroke(4);
		g2d.setStroke(thickStroke);
		lineHeight = 300;
		g2d.drawLine(LINEX, LINETOPY, LINEX, LINETOPY + lineHeight);

		BasicStroke thinStroke = new BasicStroke(2);
		g2d.setStroke(thinStroke);
		Rectangle2D rectangle = new Rectangle2D.Float(LINEX - RECTWIDTH / 2, LINETOPY - RECTHEIGHT / 2, RECTWIDTH, RECTHEIGHT);
		g2d.setColor(Color.WHITE);
		g2d.fill(rectangle);
		g2d.setColor(Color.BLACK);
		g2d.draw(rectangle);
		
		g2d.drawString("1", LINEX - RECTWIDTH / 4, LINETOPY + RECTHEIGHT / 4);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

}	