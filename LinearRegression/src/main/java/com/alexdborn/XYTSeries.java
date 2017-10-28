package com.alexdborn;

public class XYTSeries {
	final double points[][] = new double[2][];
	
	XYTSeries(double[] x, double[] y) {
		points[0] = x;
		points[1] = y;
	}
	
	XYTSeries(double points[][]){
		points
	}
}
