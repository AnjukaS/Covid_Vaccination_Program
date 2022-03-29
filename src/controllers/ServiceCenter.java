package controllers;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ServiceCenter{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String customerName = "";
		String fname = "";
		String lname = "";
		
		String[] serviceCenter = new String[7];
		int boothNum = 0;
		
		int numberOfVaccinations = 150;
		
		// initialize booths
		initialise(serviceCenter);
		
		System.out.println("\nCOVID-19 VACCINATION CENTER");
		System.out.println("------------------------");
		String menuSelection = "";
		
		
		do {
			menuSelection = menuSelection();
			
			switch (menuSelection) {
				case "100" :
				case "VVB":
				case "vvb":
					// display all booths in the vaccination center
					viewAllBooths(serviceCenter);
					break;
				case "101" :
				case "VEB":
				case "veb":
					// display all empty booths
					viewAllEmptyBooths(serviceCenter);
					break;
				case "102" :
				case "APB":
				case "apb":
					// add patient to booth
					numberOfVaccinations = addPatientToBooth(serviceCenter, boothNum, fname, lname, customerName, numberOfVaccinations);
					break;
				case "103" :
				case "RPB":
				case "rpb":
					// remove patient to booth
					serviceCenter = removePatientFromBooth(serviceCenter);
					printBooths(serviceCenter);
					break;
				case "104" :
				case "VPS":
				case "vps":
					// sort patient to booth
					bubbleSort(serviceCenter, true);
					break;
				case "105" :
				case "SPD":
				case "spd":
					// save data
					saveData(serviceCenter);
					System.out.println("Saved successfully.");
					break;
				case "106":
				case "LPD":
				case "lpd":
					// load data
					serviceCenter = loadData();
					printBooths(serviceCenter);
					System.out.println("\nLoaded successfully.");
					break;
				case "107":
				case "VRV":
				case "vrv":
					// display remaining vaccinations
					System.out.println("Remaining vaccines : " + numberOfVaccinations);
					break;
				case "108":
				case "AVS":
				case "avs":
					// add vaccines
					System.out.print("Enter the number of vaccines: ");
					while (!sc.hasNextInt()){
						System.out.println("Invalid entry ");
						System.out.print("Enter the number of vaccines: ");
						sc.next();
					}
					int stock = sc.nextInt();
					numberOfVaccinations += addVaccines(stock);
					break;
				case "999":
				case "EXT":
				case "ext":
					System.out.println("\nThank you");
					break;
				
				default:
					System.out.println("\nInvalid operation");
					break;
			}
			
			boolean runFlag = isNumeric(menuSelection);
			if (!runFlag) {
				if (menuSelection.equals("EXT") || menuSelection.equals("ext")) {
					menuSelection = "999";
				} else {
					menuSelection = "0";
				}
			}
		} while (Integer.parseInt(menuSelection) != 999 );
	}
	
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	
	// menu selection
	public static String menuSelection() {
		String menuSelection = "";
		
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("100 or VVB: View all Vaccination Booths");
		System.out.println("101 or VEB: View all Empty Booths");
		System.out.println("102 or APB: Add Patient to a Booth");
		System.out.println("103 or RPB: Remove Patient from a Booth");
		System.out.println("104 or VPS: View Patients Sorted in alphabetical order");
		System.out.println("105 or SPD: Store Program Data into file");
		System.out.println("106 or LPD: Load Program Data from file");
		System.out.println("107 or VRV: View Remaining Vaccinations");
		System.out.println("108 or AVS: Add Vaccinations to the Stock");
		System.out.println("999 or EXT: Exit the Program");
		System.out.println("");
		
		System.out.print("Enter the operation number : ");
		while (!sc.hasNext()) {
			System.out.println("Invalid operation ");
			System.out.print("Enter the operation number : ");
			sc.next();
		}
		menuSelection = sc.next();
	
		return menuSelection;
	}
	
	// to initialize the booths
	private static void initialise( String serviceCenter[] ) {
		for (int x = 0; x < serviceCenter.length; x++ ) serviceCenter[x] = "e";
	}
	
	// to print booth details
	private static void printBooths(String serviceCenter[]) {
		System.out.println("");
		for (int x = 1; x < 7; x++ ) {
			if (serviceCenter[x].equals("e")) {
				System.out.println("booth " + x + " is empty");
			} else if (!serviceCenter[x].equals("e")) {
				System.out.println("booth " + x + " occupied by " + serviceCenter[x]);
			}
		}
	}
	
	// view all booths
	private static void viewAllBooths(String serviceCenter[]) {
		System.out.println("");
		System.out.println("Vaccination Booths\n");
		for (int x = 1; x < serviceCenter.length; x++ ) {
			System.out.println("booth " + x);
		}
	}
	
	// to print * empty booths
	private static void viewAllEmptyBooths(String serviceCenter[]) {
		System.out.println("");
		System.out.println("Empty Vaccination Booths\n");
		for (int x = 1; x < serviceCenter.length; x++ ) {
			if (serviceCenter[x].equals("e")) {
				System.out.println("booth " + x);
			}
		}
	}
	
	// add patient to booth
	private static int addPatientToBooth(String serviceCenter[], int boothNum, String fname,
											String lname, String customerName, int numberOfVaccinations) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("");
		System.out.println("Add patient to booth\n");
		
		// add new patient
		printBooths(serviceCenter);
		System.out.print("Enter booth number (1-6) or 7 to stop: " );
		while (!input.hasNextInt()){
			System.out.print("\nBoothNumber Should be numeric" );
			System.out.print("Enter booth number (1-6) or 7 to stop: " );
			input.next();
		}
		boothNum = input.nextInt();
		
		while ( boothNum < 7 ){
			System.out.print("Enter customer first name for booth " + boothNum +" : " ) ;
			fname = input.next();
			
			System.out.print("Enter customer first name for booth " + boothNum +" : " ) ;
			lname = input.next();
			
			customerName = fname + " " + lname;
			
			
			int vacRequired=0;
			do{
				System.out.println("\nVaccination Types" );
				System.out.println("Enter Press 1 for AstraZeneca: " );
				System.out.println("Enter Press 2 for Sinopharm: " );
				System.out.println("Enter Press 3 for Pfizer: " );
				System.out.print("Enter Vaccination Required: " );
				while ( !input.hasNextInt()){
					System.out.println("\n Vaccination Type should be in integer" );
					System.out.print("Enter Vaccination Required: " );
					input.next();
				}
				vacRequired = input.nextInt();
			} while (vacRequired<1 || vacRequired>3);
			
			if (vacRequired == 1 ){
				if (serviceCenter[1].equals("e") && !serviceCenter[2].equals("e")){
					serviceCenter[1] = customerName ;
				} else if ( !serviceCenter[1].equals("e") && serviceCenter[2].equals("e") ){
					serviceCenter[2] = customerName ;
				} else if (serviceCenter[1].equals("e") && serviceCenter[2].equals("e")) {
					serviceCenter[1] = customerName ;
				} else {
					System.out.println("Vaccination booths are filled for AstraZeneca");
					return numberOfVaccinations;
				}
				
			} else if (vacRequired == 2 ) {
				if (serviceCenter[3].equals("e") && !serviceCenter[4].equals("e")){
					serviceCenter[3] = customerName ;
				} else if ( !serviceCenter[3].equals("e") && serviceCenter[4].equals("e") ){
					serviceCenter[4] = customerName ;
				} else if (serviceCenter[3].equals("e") && serviceCenter[4].equals("e")) {
					serviceCenter[3] = customerName ;
				} else {
					System.out.println("Vaccination booths are filled Sinopharm");
					return numberOfVaccinations;
				}
			} else {
				if (serviceCenter[5].equals("e") && !serviceCenter[6].equals("e")){
					serviceCenter[5] = customerName ;
				} else if ( !serviceCenter[5].equals("e") && serviceCenter[6].equals("e") ){
					serviceCenter[6] = customerName ;
				} else if (serviceCenter[5].equals("e") && serviceCenter[6].equals("e")) {
					serviceCenter[5] = customerName;
				} else {
					System.out.println("Vaccination booths are filled Pfizer");
					return numberOfVaccinations;
				}
			}
			
			printBooths(serviceCenter);
			numberOfVaccinations--;
			
			System.out.println(numberOfVaccinations + " remaining");
			
			System.out.print("Enter booth number (1-6) or 7 to stop: " );
			while (!input.hasNextInt()){
				System.out.print("\nBoothNumber Should be numeric" );
				System.out.print("Enter booth number (1-6) or 7 to stop: " );
				input.next();
			}
			boothNum = input.nextInt();
		}
		
		return numberOfVaccinations;
	}
	
	// remove patient to booth
	private static String[] removePatientFromBooth(String[] serviceCenter) {
		Scanner input = new Scanner(System.in);
		int boothNum = 0;
		
		System.out.println("");
		System.out.println("Remove patient from booth\n");
		
		System.out.print("Enter booth number (1-6): " );
		do{
			while ( !input.hasNextInt() ){
				printBooths(serviceCenter);
				
				System.out.println("\nInvalid booth number" );
				System.out.print("Enter booth number (1-6): " );
				input.next();
			}
			boothNum = input.nextInt();
		} while (boothNum < 1 || boothNum > 6);
		
		
		if (!serviceCenter[boothNum].equals("e")) {
			serviceCenter[boothNum] = "e" ;
		} else {
			System.out.println("Already booth is empty.");
		}
		return serviceCenter;
	}
	
	
	// sort patient names
	public static <T extends Comparable<T>> void bubbleSort(T[]array, boolean asc){
		T [] copyArray = array.clone();
		
		for (int i = 1; i < array.length; i++) {
			for (int j = 1; j < (array.length - (i+1)); j++) {
				if (asc){
					if (array[j].compareTo(array[j+1]) > 0){
						T temp = array[j];
						array[j] = array[j+1];
						array[j+1] = temp;
					}
				}else{
					if (array[j].compareTo(array[j+1] ) < 0){
						T temp = array[j];
						array[j] = array[j+1];
						array[j+1] = temp;
					}
				}
			}
		}
		for (int i=1; i< array.length; i++){
			if (!array[i].equals("e")) {
				System.out.println(array[i] + " is in booth " + Arrays.asList(copyArray).indexOf(array[i]));
			};
		}
	}
	
	// add vaccines
	private static int addVaccines(int stock) {
		System.out.println("\nStock added.");
		return stock;
	}
	
	// save data
	private static void saveData(String[] serviceCente) {
		
		File file = new File("DataSerialisation.txt");
		
		try {
			FileOutputStream fos  = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(serviceCente);
			
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// load data
	private static  <T extends Comparable<T>> T[] loadData() {
		File file = new File("DataSerialisation.txt");
		
		T [] data = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			while(true){
				data= (T[]) ois.readObject();
				break;
			}
			
			ois.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}