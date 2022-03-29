package controllers;

import model.Patient;
import model.VaccineCenter;

import java.util.Scanner;

public class ClassServiceCenter {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		VaccineCenter vaccineCenter = new VaccineCenter();
		vaccineCenter.initialise();
		
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
					System.out.println("\nVaccination Booths");
					System.out.println(vaccineCenter.viewAllBooths());
					break;
				case "101" :
				case "VEB":
				case "veb":
					// display all empty booths
					System.out.println("\nEmpty Vaccination Booths`");
					System.out.println(vaccineCenter.viewAllEmptyBooths());
					break;
				case "102" :
				case "APB":
				case "apb":
					
					// add patient to booth
					
					System.out.println("\nAdd patient to booth\n");
					int boothNum = 0;
					
					do {
						System.out.print("Enter First Name: " );
						String fname = sc.next();
						
						System.out.print("Enter Last Name: " );
						String lname = sc.next();
						
						System.out.print("Enter Age: " );
						while (!sc.hasNextInt()){
							System.out.println("\nAge should be in integer" );
							System.out.print("Enter Age: " );
							sc.next();
						}
						int age = sc.nextInt();
						
						System.out.print("Enter City: " );
						String city= sc.next();
						
						System.out.print("Enter NIC: " );
						String nic = sc.next();
						
						System.out.println("\nVaccination Types" );
						System.out.println("Enter Press 1 for AstraZeneca: " );
						System.out.println("Enter Press 2 for Sinopharm: " );
						System.out.println("Enter Press 3 for Pfizer: " );
						System.out.print("Enter Vaccination Required: " );
						while ( !sc.hasNextInt()){
							System.out.println("\n Vaccination Type should be in integer" );
							System.out.print("Enter Vaccination Required: " );
							sc.next();
						}
						int vacRequired = sc.nextInt();
						
						Patient patient = new Patient(fname, lname, age, city, nic, vacRequired);
						System.out.println("Remaining vaccines: " + vaccineCenter.addPatientToBooth(patient));
						System.out.println("\nVaccination Booths");
						System.out.println(vaccineCenter.viewAllBooths());
						
						
						System.out.print("\nIf you want to add more press any number or press 7 to stop: " );
						while ( !sc.hasNextInt()){
							System.out.println("\nFollow operation input should be in integer" );
							System.out.print("\nIf you want to add more press any number or press 7 to stop: " );;
							 sc.next();
						}
						boothNum = sc.nextInt();
						
					} while (boothNum != 7);
					
					break;
				case "103" :
				case "RPB":
				case "rpb":
					// remove patient to booth
					System.out.println("\nRemove patient from booth\n");
					
					int boothNo=0;
					
					System.out.print("Enter booth number (1-6): " );
					while (!sc.hasNextInt() && (boothNo<1 || boothNo>6)) {
						System.out.print("Booth number should be in 1-6" );
						System.out.print("Enter booth number (1-6): " );
						sc.next();
					}
					boothNo = sc.nextInt();
					vaccineCenter.removePatientFromBooth(boothNo);
					
					break;
				case "104" :
				case "VPS":
				case "vps":
					// sort patient to booth
					System.out.println("\nSorted List\n");
					System.out.println(vaccineCenter.bubbleSort(true));
					break;
				case "105" :
				case "SPD":
				case "spd":
					// save data
					vaccineCenter.saveData(vaccineCenter);
					break;
				case "106":
				case "LPD":
				case "lpd":
					// load data
					System.out.println(vaccineCenter.loadData().toString());
					break;
				case "107":
				case "VRV":
				case "vrv":
					// display remaining vaccinations
					System.out.println("\nRemaining vaccines : " + vaccineCenter.getNumberOfVaccines());
					break;
				case "108":
				case "AVS":
				case "avs":
					// add vaccines
					int numberOfVaccines = 0;
					System.out.print("Enter the vaccine stock: " );
					while (!sc.hasNextInt()) {
						System.out.print("stock number should be in integer" );
						System.out.print("Enter the vaccine stock: " );
						sc.next();
					}
					numberOfVaccines = sc.nextInt();
					
					System.out.println("\nRemaining vaccines : " + vaccineCenter.addVaccines(numberOfVaccines));
					
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
}
