package com.alexdborn;

public class LinearRegressor {
	final double learningRate; // Rate of convergence
	/* y = mx + b */
	private final double initialM; // Initial slope
	private final double initialB; // Initial y intercept
	private final double ittr;
	private Double[][] XYSeries = new Double[2][];
	private Boolean hasModel = false;
	
	private double m, b;
	
	LinearRegressor(double learningRate, double initialM, double initialB, double ittr, Double[][] data){
		this.learningRate = learningRate;
		this.initialM = initialM;
		this.initialB = initialB;
		XYSeries[0] = data[0];
		XYSeries[1] = data[1];
		this.ittr = ittr;
		m = initialM;
		b = initialB;
	}
	
	LinearRegressor(double learningRate, double initialM, double initialB, double ittr){
		this.learningRate = learningRate;
		this.initialM = initialM;
		this.initialB = initialB;
		XYSeries[0] = null;
		XYSeries[1] = null;
		this.ittr = ittr;
		m = initialM;
		b = initialB;
	}
	
	public double compute(double x) {
		return m*x + b;
	}
	
	void run() {
		System.out.printf("Gradient Descent - M:%.2f B:%.2f\nError: %.2f\n", initialM, initialB, error());
		gradientDescent();
		hasModel = true;
		System.out.printf("Model Equation: %.4fx + %.2f\nFinal Error: %.4f\n", m, b, error());
		System.out.printf("Mean squared error: %f", meanSquaredError());
	}
	
	private void gradientDescent() {
		for(int i = 0; i < ittr; i++) {
			gradientStep();
		}
	}
	
	private void gradientStep() {
		double b_gradient = 0.0;
		double m_gradient = 0.0;
		double tot = XYSeries[0].length;
		
		for	(int i = 0; i < XYSeries[0].length; i++) {
			// No X
			m_gradient +=  XYSeries[0][i] * (XYSeries[1][i] - (m * XYSeries[0][i] + b));
			b_gradient +=  (XYSeries[1][i] - (m * XYSeries[0][i] + b));
		}
		
		m_gradient *= -1 * (2/tot);
		b_gradient *= -1 * (2/tot); // Prob Positive
		
		b -= (learningRate * b_gradient);
		m -= (learningRate * m_gradient);
	}
	
	private double error() {
		assert XYSeries[0].length == XYSeries[1].length: "Arrays have to contain same numbers of points";
		// Sum squared error
		double error = 0.0;
		for (int n = 0; n < XYSeries[0].length; n++) {
			error += Math.pow((XYSeries[1][n] - m * XYSeries[0][n] + b), 2);
		}
		// Return average error
		return error / XYSeries[0].length;
	}
	
	private double meanSquaredError() {
		return Math.sqrt(error());
	}

	public double getM() {
		return m;
	}

	public double getB() {
		return b;
	}
	
	public void setData(Double[][] data) {
		this.XYSeries = data;
	}
	
	public Boolean hasModel() {
		return hasModel;
	}
}
