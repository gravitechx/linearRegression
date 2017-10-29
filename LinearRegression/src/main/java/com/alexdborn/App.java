package com.alexdborn;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    		/* SET UP THE MAIN WINDOW */
    
		// Construct window
		JFrame mainWindow = new JFrame();
		
		// Set window name
		mainWindow.setTitle("Linear Regressor");
		
		// Set default closing behavior to quitting the program
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// Set layout to border
		mainWindow.setLayout(new BorderLayout());
		
		// Set Location and Size 
		mainWindow.setSize(800, 200);
		mainWindow.setLocation(0, 0);
		
		/*  CONSTRUCT UI OBJECTS */
		
		// Top Panel
		JPanel topPanel = new JPanel();
		mainWindow.add(topPanel, BorderLayout.NORTH);
		
		// Text for file field
		JLabel filePathLabel = new JLabel();
		filePathLabel.setText("File Path: ");
		topPanel.add(filePathLabel);
		
		// Text field to get file path
		final JTextField fileField = new JTextField("/Users/alexnrob/CompSci/eclipse/linearRegression/LinearRegression/data.csv");
		topPanel.add(fileField);
		
		// Input Number
		final JTextField guessField = new JTextField("3.0");
		topPanel.add(guessField, BorderLayout.WEST);
		
		// Output Text
		final JLabel outputField = new JLabel();
		outputField.setText("OUTPUT");
		topPanel.add(outputField);
		
		// Bottom Panel
		JPanel bottomPanel = new JPanel();
		mainWindow.add(bottomPanel);
		
		// Button for train
		JButton trainButton = new JButton();
		trainButton.setText("Train");
		bottomPanel.add(trainButton);
		
		// Button for compute new point
		JButton computeButton = new JButton();
		computeButton.setText("Compute Point");
		bottomPanel.add(computeButton);
		
		// Create linear regressor object
		final LinearRegressor lr = new LinearRegressor(.00005, 0.0, -20.0, 500000);
		
		// Train Button Listener
		trainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Get data from CSV file
				CSVReader cv = new CSVReader(fileField.getText(), ",");
				Double data[][] = cv.readToDoubleArray();
				
				lr.setData(data);
				
				// Run Linear Regression
				lr.run();
			}
		});
		
		// Compute new point listener
		computeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lr.hasModel()) {
					double number = Double.parseDouble(guessField.getText());
					outputField.setText(Double.toString(lr.compute(number)));
				}
			}
		});
		
		// Print data
		/*for(int f = 0; f < data[0].length; f++) {
			System.out.println(Double.toString(data[0][f]) + " " + Double.toString(data[1][f]));
		}*/
		

		
		/* SHOW MAIN WINDOW */
		mainWindow.setVisible(true);
    }
}
