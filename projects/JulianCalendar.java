package edu.cuny.csi.csc330.lab1;

/**
 * Generates a non-leap year perpetual Julian Calendar 
 * @author lji
 *
 */
public class JulianCalendar {
	
	// Max number of Days in a month 
	static private  final int MAX_DAY = 31; 
	
	// abbreviated Month names 
	static private  final String [] MONTH_NAMES = {
		"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
	};
	
	// day length of each month 
	static private final  int [] MONTH_SIZES = {
		31, 28, 31,30,31, 30, 31, 31, 30, 31, 30, 31
	};

	/**
	 * display Month names between Day .... Day
	 */
	static private void displayHeading() {
		System.out.printf("%5s", "Day");
		
			
		for(int i = 0 ; i < MONTH_NAMES.length ; ++i )  {
			System.out.printf("%5s", MONTH_NAMES[i]);
		}
		
		System.out.printf("%5s\n", "Day");
	}
	

	static public void display() {
		displayHeading(); // display heading 
		for(int i = 0; i < MAX_DAY; i++){
			int count = 1 + i;
			System.out.printf("%5d  ", i + 1);
			for(int j = 0; j < MONTH_NAMES.length; j++){
				if (i < MONTH_SIZES[j]){
					System.out.printf("%03d  ", count);
				}
				else
					System.out.printf("%03d  ", 0);
				count += MONTH_SIZES[j];
			}
			System.out.printf("%d\n", i + 1);	
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// invoke display method 
		display(); 
	}
}