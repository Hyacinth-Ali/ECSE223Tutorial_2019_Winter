package java2d;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class BasicEx extends JFrame{

	public BasicEx() {
		iniUI();
	}
	
	public void iniUI() {
		add(new Surface());
		setTitle("Title!");
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
	}
	
	public static void main(String[] args) {	
		
		EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                BasicEx ex = new BasicEx();
                ex.setVisible(true);
            }
        });
	}
}
