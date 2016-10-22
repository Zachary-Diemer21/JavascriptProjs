package edu.cuny.csi.csc330.lab2;

import java.util.Arrays;
import java.lang.Math;


public class NumericAnalyzer {
	
	private int [] numbers;
	
	private int mean;
	private int min;
	private int max;
	private int median;
	private int range;
	private int sum;
	private int variance;
	private int stdDev;
	 
	// 
	public NumericAnalyzer(int [] numbers) {
		this.numbers = numbers; 
		Arrays.sort(this.numbers); 
	}
	
	protected void deriveMin() {
		this.min = this.numbers[0];
	}
	
	protected void deriveSum() {
		this.sum = 0; 
		
		for(int i = 0 ; i < numbers.length ; ++i ) {
			this.sum += numbers[i]; 
		}
	}
	
	protected void deriveMean() {
		
		this.mean = this.sum / numbers.length; 
	}
	
	/**
	 * subtract the mean from each data value, then square each value, the  derive the average of the derived squares ...  
	 */
	protected void deriveVariance() {
		int runningSum = 0; 
		for(int i = 0 ; i < this.numbers.length ; ++i )  {
			int var = this.numbers[i] - this.mean; 
			runningSum += Math.pow(var, 2 ); 
		}	
		this.variance = runningSum / this.numbers.length;  
	}
	
	/**
	 * sqrt of the Variance ... 
	 */
	protected void deriveStdDev() {
		this.stdDev = (int) Math.sqrt(this.variance); 
		
	}
	
	protected void deriveMax() {
		this.max = this.numbers[this.numbers.length-1];
	}
	
	protected void deriveRange() {

		this.range = this.max - this.min; 
	}
	
	protected void deriveMedian() {
		int mindex = numbers.length / 2; 
		median = numbers[mindex];
	}



	public void display() {
		
		for(int i = 0 ; i < numbers.length ; ++i ) {
			System.out.printf("%,d\t", numbers[i]); 
		}
		System.out.println("\n"); 
		
		String fmtString = "%-20s %,15d\n";
		
		System.out.printf(fmtString, "Size:",  numbers.length);
		System.out.printf(fmtString, "Min:",  min); 
		System.out.printf(fmtString, "Max:",  max); 
		System.out.printf(fmtString, "Range:",  range); 
		System.out.printf(fmtString, "Sum:",  sum); 
		System.out.printf(fmtString, "Mean:",  mean); 
		System.out.printf(fmtString, "Median:",  median);  
		System.out.printf(fmtString, "Variance:",  variance); 
		System.out.printf(fmtString, "Standard Deviation:", stdDev); 
		
		System.out.println();
		
		

		
	}
	
	public void performCalculations() {
		
		deriveSum(); 
		
		deriveMin();
		
		deriveMax(); 
 
		deriveRange();
		
		deriveMean();
		 
		deriveMedian();
		
		deriveVariance();

		deriveStdDev();
		
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// reject incorrect usage by user 
		if(args.length == 0 ) {
			System.err.println("Pass in 1 or more positive integer number values.");
			System.exit(1); 
		}
		// create an int array 
		int [] numbers = new int[args.length]; 
		for(int i = 0 ; i < args.length ; ++i ) {
			numbers[i] = Integer.parseInt(args[i]); 
		}
		// create instance of NumericAnalyzer - passing array into the constructor 
		NumericAnalyzer analyzer = new NumericAnalyzer(numbers); 
		// go number-crunch 
		analyzer.performCalculations(); 
		// display results 
		analyzer.display(); 
		
	}

}