package java2d;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Surface extends JPanel {
	
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawString("Java 2D", 150, 200);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

}
