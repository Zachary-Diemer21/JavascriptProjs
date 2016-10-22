/**
	 * 
	 * @author zackymo
	 * Thursday October 20th, 2016 
	 * Below is the code to mimic the basic functions of a refrigerator in terms
	 * of use. There are essentially two classes: 1) an Item class that allows a user 
	 * to create a random item (or a random list of items with an expiration date 
	 * and a barcode and 2) a Refrigerator class which utilizes the Item class and offers 
	 * some additional functions.
	 */
package edu.cuny.csi.csc330.lab4;

import java.util.*;
//import java.text.*;
import java.sql.Timestamp;
import edu.cuny.csi.csc330.lib.Randomizer;


public class Refrigerator2 {
	
	public static class Item {
		
		private String barCode; 
		private Date expDate;
		
		public Item() {
			init(); 
		}

		/**
		 * Item class - initializing an item and/or a list of items with a 
		 * barCode, and expDate - Only gets invoked by constructor 
		 */
		
		private void init(){
			//Generating and setting an item's barCode 
			Randomizer randomizer = new Randomizer(); 
			Integer irand = randomizer.generateInt(11111111, 99999999); 
			this.barCode = new Date().getTime() + ":" + irand.toString(); 
			
			//Generating random timestamps between two designated time periods 
			long offset = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
			long end = Timestamp.valueOf("2016-01-01 00:00:00").getTime();
			long diff = end - offset + 1;
			Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
			this.expDate = rand;	
		}
		
		private static List<Item> generateRandListofItems(int numofItems){
			List<Item> itemsList = new ArrayList<Item>();
			for(int i = 0; i <= numofItems; i++){
				//Create an instance of item and add it to the list 
				Item it = new Item();
				itemsList.add(it);
			}
			return itemsList;
		}

		@Override // Need this general toString to avoid outputting random reference addresses
		public String toString() {
			return "Item [barCode=" + barCode + ", expDate=" + expDate + "]";
		}
		
	}
	/**
	 * Establishing the basic conditions of a general refrigerator based on research findings
	 */
	protected static final double MIN_TEMPERATURE = 33;
	protected static final double MAX_TEMPERATURE = 39;
	protected static final double DEFAULT_TEMPERATURE = 36.5;
	//protected static final String DEFAULT_BAND = Bands.AM.name;
	protected static final int MIN_ITEMS = 0;
	protected static final int MAX_ITEMS = 40;

	//Common refrigerator elements 
	private boolean powerState; //on/off
	private double specificTemperature;
	
	//Used to calculate how long the door is open for
	private boolean doorOpen; //open/close
	//private Date doorOTime;
	//private Date doorCTime;
	//private Date lengthOpen;
	//private SimpleDateFormat doorUsedFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);//need to edit this format to time
	//private boolean shouldBeep;
	
	//Water and Ice Dispenser Variables 
	private boolean dispenserPowerState;
	private boolean dispenseWater;
	
	//Time variables
	private Date firstTimeOn;
	//private Date lastTimeOn;
	
	private List<Item> itemsL = new ArrayList<Item>();

	//private SimpleDateFormat expDateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
	
	//Variables for scanner that reads items and calculates how much time they have to expire 
	//private boolean didExpire;
	//private Date currTime;
	//private Date isExpired;
	
	public Refrigerator2() {
		init(); 
	}
		
		/**
		 * Refrigerator class - initializing a refrigerator 
		 */ 
	
	private void init() {//init in this case can be used like the on function
		Randomizer randomizer = new Randomizer(); 
		Integer powerRand = randomizer.generateInt(0,1);
		Integer itemsRand = randomizer.generateInt(MIN_ITEMS,MAX_ITEMS);
		Date now = new Date();
		/* Check powerState until the user turns on the Refrigerator 
		 * Such a feature could be utilized by a company to check the diagnostics of the device 
		 * In hindsight it would be more efficient to send a signal back to the company when the device
		 * is turned on. 
		 */
		while(powerRand == 0){
			System.out.println("The Refrigerator is off - It may not be plugged in");
			powerRand = randomizer.generateInt(0,1); 
		}
		powerState = true;
		System.out.println("The Refrigerator is now on - The User plugged it in and Turned device on");

		if(firstTimeOn == null){
			firstTimeOn = now;
			//Create a random list of items and put them in the refrigerator 
			itemsL = Item.generateRandListofItems(itemsRand);
			//Set the default conditions of a refrigerator 
			specificTemperature = DEFAULT_TEMPERATURE; 
			doorOpen = false;
			dispenserPowerState = false;
			dispenseWater = true;//This means the dispenser if used would dispense water	
		}	
	}
		
	public double checkTemperature() {
		return specificTemperature;
	}
	
	public void setSpecificTemperature(double specificTemperature) {
		if(specificTemperature >= MIN_TEMPERATURE && specificTemperature <= MAX_TEMPERATURE)
			this.specificTemperature = specificTemperature;
		else 
			System.out.println("The temperature you have entered is invalid, please enter another temperature");
	}
	
	public boolean isDoorOpen() {
		return doorOpen;
	}
	
	public void setDoorOpen(boolean doorOpen) {
		if(doorOpen == true){
			this.doorOpen = doorOpen;
			//doorOTimeStart();//Can be used to calculate how long the door was open for - it does not make sense to have these function calls within this function
			System.out.println("Thank you for opening the door. Welcome to your food sanctuary. You can now look at the items!\nAfter you are done looking/adding/taking food would you please close the door!");
			//System.out.println(itemsL);
			//doorOTimeEnd();
			//checkForExpItems(itemsL);
			//System.out.println("Wow, you looked very quickly! Do not forget to close the door");
			}
		else 
			System.out.println("Thank you for closing the door!\n");
			this.doorOpen = doorOpen;
	}
	
	public void removeItem(int indexNum) {
		itemsL.remove(indexNum);
	}
	
	//Can also overload this function as well to take an index and an element 
	public void addItem(Item i){
		itemsL.add(i);
	}
	
	public void countItems(){
		itemsL.size();
	}
	
	/*TO FURTHER THIS PROJECT IN THE FUTURE(DOES NOT NEED TO BE VIEWED BUT CAN BE:
	 * Have the refrigerator check the items and see if they are expired and remove them 
	 * How could you do this, access the expDate in each item, compare it to a specific date by 
	 * parsing the date var for the time, and compare it to the given day, then flag it 
	 * Then remove all flagged items in the itemsL
	 * public void checkForExpItems(List<Item> its){
		long offset = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
		long end = Timestamp.valueOf("2016-01-01 00:00:00").getTime();
		long diff = end - offset + 1;
		Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
		 * 
		 
		SimpleDateFormat expDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);//Was changed from "MMMM d, yyyy"
		//Date expDat = expDateFormat.parse(string); //Need to have this variable read in from main
		for(int i = 0; i <= its.size(); i++){
			Date expDate = expDateFormat.parse(its.get(i));
		}
		
		try{
			String string = "January 2, 2017";
			String string2 = "January 3, 2010";
			Date currTime = new Date();
			//Do variables 
			SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
			Date date = format.parse(string);
			Date date2 = format.parse(string2);
			System.out.println(date); 
			System.out.println(date2); 
			if(currTime.before(date))
				System.out.println("yes");
			} catch (java.text.ParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
			}
	}
	
	public List<Item> removeExpItems(List<Item> its){
			
		return its;
	}
	
	public void doorOTimeStart(){
		Date now = new Date();
		this.doorOTime = now;
	}
	
	public void doorOTimeEnd(){
		Date now = new Date();
		this.doorCTime = now;
	}
	*/
	
	public boolean isDispenserPowerStateOn() {
		return dispenserPowerState;
	}
	
	public void setDispenserPowerState(boolean dispenserPowerState) {
		this.dispenserPowerState = dispenserPowerState;
	}
	
	public boolean getDispenseWater() {
		return dispenseWater;
	}
	
	public void setDispenseWater(boolean dispenseWater) {
		if(this.dispenserPowerState = true){
			this.dispenseWater = dispenseWater;
			if(dispenseWater = true)//should add other conditions here for validity checking)
				System.out.println("Dispensing water. Make sure it does not overflow");
			else
				System.out.println("Dispensing Ice from Antartica ;)");
			}
		else
			System.out.println("Please turn on the dispenser and try again");
	}
	
	public Date getFirstTimeOn() {
		return firstTimeOn;
	}
	
	/*public void setFirstTimeOn(Date firstTimeOn) {
		this.firstTimeOn = firstTimeOn;
	}*/
	
	/*public Date getCurrTime() {
		return currTime;
	}
	
	public void setCurrTime(Date currTime) {
		this.currTime = currTime;
	}*/
	
	@Override
	public String toString() {
		return "Refrigerator [powerState=" + powerState + ", specificTemperature=" + specificTemperature + ", doorOpen="
				+ doorOpen + ", dispenserPowerState=" + dispenserPowerState + ", dispenseWater=" + dispenseWater + ", firstTimeOn=" + firstTimeOn
				+ ", itemsL=" + itemsL ;
	}
	
	public static void main(String[] args) {
		
		Refrigerator2 fridge = new Refrigerator2();
		Item ketchup = new Item();
		System.out.println("\nNew Instance\n" + fridge + "\n");
		
		fridge.setDoorOpen(true);
		fridge.setSpecificTemperature(37.2);
		System.out.println("\nAfter Door was opened\n" + fridge + "\n");
		
		fridge.addItem(ketchup);
		System.out.println("\nAfter adding an item to the refrigerator\n" + fridge + "\n");
		
		fridge.removeItem(5);
		System.out.println("\nItems in the fridge after you remove an item\n" + fridge + "\n");
		fridge.setDoorOpen(false);
		
		fridge.setDispenserPowerState(true);
		fridge.setDispenseWater(false);
		System.out.println("\nGetting some ice\n" + fridge + "\n");
		
		fridge.setSpecificTemperature(34.55);
		System.out.println("\nAh, I am now ready to go to sleep :)\n" + fridge + "\n");	
		
	}
}

		//DOES NOT NEED TO BE VIEWED BY YOU, PROFESSOR

		//List<Item> exList = fridge.itemsL;
		//All test code to make sure different libraries, functions, and misc. items worked beforehand 
		/*int size = 5;
		double from = 10.0;
		double to = 1000.0;
		Randomizer randomizer = new Randomizer(); 
		double [] presets = new double[size];
		
		for(int i = 0 ; i  < presets.length ; i++ ) {
			presets[i] = randomizer.generateInt(from, to);
			System.out.print(presets[i]);
			}
		
		try{
		String string = "January 2, 2017";
		String string2 = "January 3, 2010";
		Date currTime = new Date();
		//Do variables 
		SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		Date date = format.parse(string);
		Date date2 = format.parse(string2);
		
		System.out.println(date); 
		System.out.println(date2); 
		if(currTime.before(date))
			System.out.println("yes");
		} catch (java.text.ParseException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
		}
		
		//I want the random date range to be between 3 months before today and 3 months after 
		Date currTime = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		String scurrTime = df.format(currTime);
		
		//How to create random timestamps 
		long offset = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
		long end = Timestamp.valueOf("2016-01-01 00:00:00").getTime();
		long diff = end - offset + 1;
		Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
		System.out.println(rand);
		
		//should create a function called create new item
		List<Item> itemsInFridge = new ArrayList<Item>();
		
		Item mofo = new Item();
		System.out.println(mofo.expDate);
		System.out.println(mofo.barCode);
		
		itemsInFridge.add(mofo);
		
		System.out.println(itemsInFridge.toString());//does not work, only prints address, Why? 
		
		//The Logic Here works great 
		boolean powerState;
		Randomizer randomizer1 = new Randomizer(); 
		Integer irand = randomizer1.generateInt(0,1); 
		while(irand == 0){
			System.out.println("The Refrigerator is off - It may not be plugged in");
			irand = randomizer1.generateInt(0,1); 
		}
		powerState = true;
		System.out.println("The Refrigerator is now on");*/
