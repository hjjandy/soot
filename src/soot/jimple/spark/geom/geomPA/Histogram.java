/*
 * Please attach the following author information if you would like to redistribute the source code:
 * Developer: Xiao Xiao
 * Address: Room 4208, Hong Kong University of Science and Technology
 * Contact: frogxx@gmail.com
 */
package soot.jimple.spark.geom.geomPA;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sun.org.apache.xml.internal.utils.NSInfo;

/**
 * A helper class for plotting the evaluation results in histogram form.
 * 
 * @author xiao
 *
 */
public class Histogram {
	// private static Histogram instance;

	//
	private int[] limits;
	
	private int count = 0;
	
	private int[] results = null;

	public Histogram(int[] limits) 
	{
		int i;
		
		this.limits = limits;
		results = new int[limits.length +1];
		
		for ( i = 0; i <= limits.length; ++i )
			results[i] = 0;
	}
	
	public void printResult ( PrintStream output ){
		if ( count == 0 ) {
			output.println( "No samples are inserted, no output!" );
			return;
		}
		
		output.println( "Samples : " + count );
        for(int i = 0; i < results.length; i++){
        	if(i == 0)
        		output.print("<=" + limits[0] + ": " + results[i]);
        	else if(i == results.length -1){
        		output.print(">" + limits[limits.length - 1] + ": " + results[i]);
        	}else{
        		output.print(limits[i-1] + "< x <=" + limits[i] + ": " + results[i]);
        	}
        	
        	output.printf( ", percentage = %.2f\n", (double)results[i] * 100/count );
        }
        
        output.println();
	}

	public void printResult( PrintStream output, String title )
	{
		output.println( title );
		printResult(output);
	}
	
	/**
	 * This function prints two histograms together for comparative reading.
	 * It requires the two histograms having the same data separators.
	 * 
	 * @param output
	 * @param title
	 * @param other
	 */
	public void printResult (PrintStream output, String title, Histogram other )
	{
		output.println( title );
		
		if ( count == 0 ) {
			output.println( "No samples are inserted, no output!" );
			return;
		}
		
		output.println( "Samples : " + count + " (" + other.count + ")" );
		
        for(int i = 0; i < results.length; i++){
        	if(i == 0)
        		output.printf("<= %d: %d (%d)", limits[0], results[i], other.results[i]);
        	else if(i == results.length -1){
        		output.printf("> %d: %d (%d)", limits[limits.length - 1], results[i], other.results[i]);
        	}else{
        		output.printf("%d < x <= %d: %d (%d)", limits[i-1], limits[i], results[i], other.results[i] );
        	}
        	
        	output.printf( ", percentage = %.2f%% (%.2f%%) \n", 
        			(double)results[i] * 100/count, (double)other.results[i] * 100/other.count );
        }
        
        output.println();
	}
	
	public void addNumber(int num) {
		count ++;
		int i = 0;
		for (i = 0; i < limits.length; i++) {
			if (num <= limits[i]) {
				results[i]++;
				break;
			}
		}
		
		if(i == limits.length){
			results[i]++;
		}
	}
	
	/**
	 * Merge two histograms.
	 * @param other
	 */
	public void merge( Histogram other )
	{
		int i;
		
		for ( i = 0; i <= limits.length; ++i )
			results[i] += other.results[i];
		
		count += other.count;
	}
	
	public int getTotalNumofSamples()
	{
		return count;
	}
	
	/**
	 * Use the current distribution but scale the samples close to the user specified one  
	 * @param usrSamples
	 */
	public void scaleToSamples(int usrSamples)
	{
		double ratio;
		
		ratio = (double)usrSamples/count;
		count = 0;
		
		for ( int i = 0; i <= limits.length; ++i ) {
			results[i] = (int)Math.round( results[i] * ratio );
			count += results[i];
		}
	}
	
	public int getResult( int inx )
	{
		if ( inx >= limits.length )
			return 0;
		
		return results[inx];
	}

}
