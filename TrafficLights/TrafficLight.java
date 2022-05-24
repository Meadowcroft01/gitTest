package norfolkOutreach2022.TrafficLights;

 /* 
 * TrafficLight.java: Displays a rotatable traffic light
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This is the Traffic Light class
 */
public class TrafficLight extends JFrame implements ActionListener {
	/*
	 * GUI components
	 */
	private JFrame jframe;
	private JPanel jpanel;
	private JButton jbtRotate,jbtExit;
	private TrafficPanel trafficPanel;

	/**
	 * This constructor initialize the state of the traffic light.
	 */
	public TrafficLight() {
		jframe = new JFrame("Traffic Light");
		jpanel = new JPanel();
		jbtRotate = new JButton("Rotate");
		jbtExit = new JButton("Exit");
	}

	/**
	 * Method to add the GUI components to the frame, arrange them,
	 * and makes the frame visible to the user.
	 */
	public void launchApp()	{	
		jframe.setSize(200,350);

		// Centering the screen on the desktop
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = jframe.getSize();
		jframe.setLocation(((screenSize.width - frameSize.width) / 2),
							((screenSize.height - frameSize.height) / 2));		

		// Adding button controls to panel
		
		jpanel.add(jbtExit);

		// Register Listeners with buttons
		
		jbtExit.addActionListener(this);

		// Adding the traffic light
		trafficPanel = new TrafficPanel();
		jframe.getContentPane().add(trafficPanel);

		// Adding panel to lower frame
		jframe.getContentPane().add(jpanel, BorderLayout.SOUTH);

		// Default close action		
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);

		JOptionPane.showMessageDialog(this, 
			"Traffic light" + 
			"\nchanges" ,
			"Welcome!", JOptionPane.INFORMATION_MESSAGE);
		trafficPanel.changeColor();

		trafficPanel.schedule();
	}

	/**
	 * This the driver program
	 */
	public static void main(String args[]) {
		TrafficLight trafficLight = new TrafficLight();
		trafficLight.launchApp();
		
	}

	/**
	 * This method traps the button click events
	 */
	public void actionPerformed(ActionEvent e) {
		// Rotate button is clicked
		if (e.getSource() == jbtRotate) {
			// Change the color displayed
			trafficPanel.changeColor();
		}	

		// Exit button is clicked
		if (e.getSource() == jbtExit) {
			JOptionPane.showMessageDialog(this, 
				"Goodbye and have a nice day!",
				"Leaving Traffic Light", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}	
	}
	

	
	
} 
/**
 * The Traffic Panel class
 */
class TrafficPanel extends JPanel {

	/** Variable to store the current state of the traffic light.
	 * @ lightState = 1 (Red)
	 * @ lightState = 2 (Yellow)
	 * @ lightState = 3 (Green)
	 */
	private int lightState = 1;	 

	public void schedule() {
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				changeColor();
			}
		}, 1, 5, TimeUnit.SECONDS);
	}



	/**
	 * This method repaints the light status
	 */
	public void changeColor() {		
		lightState++;

		if (lightState > 3) {
			lightState = 1;
		}	
		repaint();	
	}

	/**
	 * This method draws the traffic light on the screen
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);		

		// Draws the traffic light
		// Draw out white frame
		g.setColor(new Color(255,255,255));
		g.fillRoundRect(35,15,120,225,30,30);

		// Draw inner black frame
		g.setColor(new Color(0,0,0));
		g.fillRoundRect(50,30,90,195,30,30);
		g.drawRoundRect(35,15,120,225,30,30);

		// RED bulb dim		
		g.setColor(new Color(100,0,0));
		g.fillOval(70,40,50,50);		

		// YELLOW bulb dim
		g.setColor(new Color(100,100,0));
		g.fillOval(70,100,50,50);

		// GREEN bulb dim
		g.setColor(new Color(0,100,0));
		g.fillOval(70,160,50,50);

		

		switch(lightState) {
		case 1:
			// RED bulb glows
			g.setColor(new Color(255,0,0));
			g.fillOval(70,40,50,50);				
			break;

		case 2:
			// YELLOW bulb glows
			g.setColor(new Color(255,255,0));
			g.fillOval(70,100,50,50);		
			break;

		case 3:
			// GREEN bulb glows
			g.setColor(new Color(0,255,0));
			g.fillOval(70,160,50,50);		
			break;		
		}
	}
} // End of class Traffic Panel 
