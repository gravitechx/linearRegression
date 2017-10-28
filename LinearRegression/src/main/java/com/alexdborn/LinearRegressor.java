package com.alexdborn;

public class LinearRegressor {
	final double learningRate; // Rate of convergence
	/* y = mx + b */
	final double initialM; // Initial slope
	final double initialB; // Initial y intercept
	double m, b;
	final double ittr;
	final double[][] XYSeries = new double[2][];

	
	LinearRegressor(double learningRate, double initialM, double initialB, double ittr, double[][] data){
		this.learningRate = learningRate;
		this.initialM = initialM;
		this.initialB = initialB;
		XYSeries[0] = data[0];
		XYSeries[1] = data[1];
		this.ittr = ittr;
		m = initialM;
		b = initialB;
	}
	
	void run() {
		System.out.printf("Gradient Descent - M:%.2f B:%.2f\nError: %.2f\n", initialM, initialB, error());
		gradientDescent();
		System.out.printf("Model Equation: %.2fX + %.2f\nFinal Error: %.2f\n", m, b, error());
	}
	
	private void gradientDescent() {
		for(int i = 0; i < 100; i++) {
			gradientStep();
		}
	}
	
	private void gradientStep() {
		double b_gradient = 0.0;
		double m_gradient = 0.0;
		int tot = XYSeries[0].length;
		for	(int i = 0; i < tot; i++) {
			b_gradient += -1 * (2/ (double) tot) * (XYSeries[0][i] - (m * XYSeries[1][i] + b));
			m_gradient += -1 * (2/ (double) tot) * (XYSeries[0][i] - (m * XYSeries[1][i] + b));
		}
		b = b - (learningRate * b_gradient);
		m = m - (learningRate * m_gradient);
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
	
}
