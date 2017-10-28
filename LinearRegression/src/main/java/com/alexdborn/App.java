package com.alexdborn;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		// Construct window
		JFrame mainWindow = new JFrame();
		
		// Set window name
		mainWindow.setTitle("Linear Regressor");
		
		// Set default closing behavior to quitting the program
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// Set layout to border
		mainWindow.setLayout(new BorderLayout());
		
		// Get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = (int) screenSize.getHeight();
		int screenWidth  = (int) screenSize.getWidth();
		
		double data[][] = {{1.0, 2.0, 3.2}, {-1.0, 3.1, 4.2}};
		
		LinearRegressor lr = new LinearRegressor(.0001, 0.0, 0.0, 3, data);
		lr.run();
		
		// Set Location and Size 
		mainWindow.setSize(screenWidth, screenHeight);
		mainWindow.setLocation(0, 0);
		mainWindow.setVisible(false);
    }
}
