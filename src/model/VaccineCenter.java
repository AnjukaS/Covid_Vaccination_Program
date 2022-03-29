package model;

import java.io.*;
import java.util.Arrays;

public class VaccineCenter implements Serializable{
	private int numberOfVaccines;
	private Booth[] booths;
	
	public VaccineCenter() {
		this.numberOfVaccines = 150;
		this.booths = new Booth[7];
	}
	
	public int getNumberOfVaccines() {
		return numberOfVaccines;
	}
	
	public void setNumberOfVaccines(int numberOfVaccines) {
		this.numberOfVaccines = numberOfVaccines;
	}
	
	public Booth[] getBooths() {
		return booths;
	}
	
	public void setBooths(Booth[] booths) {
		this.booths = booths;
	}
	
	public void initialise() {
		for (int x = 0; x < this.booths.length; x++ ) this.booths[x] = new Booth(x, null);
	}
	
	// view all booths
	public String viewAllBooths() {
		String data = "";
		for (int x = 1; x < this.booths.length; x++ ) {
			if (this.booths[x].getPatient() == (null)) {
				data+="\nbooth " + x + " is empty";
				
			} else if (this.booths[x].getPatient() != (null)) {
				data+="\nbooth " + x + " occupied by " + this.booths[x].toString();
			}
		}
		return data;
	}
	
	public String viewAllEmptyBooths() {
		String data = "";
		for (int x = 1; x < this.booths.length; x++ ) {
			if (this.booths[x].getPatient() == (null)) data+="\nbooth " + x + " is empty";
		}
		return data;
	}
	
	// add patient to booth
	public int addPatientToBooth(Patient patient) {
		
		// add new patient
		viewAllBooths();
		int vaccineId = patient.getVaccinationId();
		if (vaccineId == 1 ){
			if (booths[1].getPatient() == null && booths[2].getPatient() != null){
				booths[1].setPatient(patient);
			} else if ( booths[1].getPatient() != null && booths[2].getPatient() == null ){
				booths[2].setPatient(patient);
			} else if (booths[1].getPatient() == null && booths[2].getPatient() == null) {
				booths[1].setPatient(patient);
			} else {
				System.out.println("Vaccination booths are filled for AstraZeneca");
				return numberOfVaccines;
			}
			
		} else if (vaccineId == 2 ) {
			if (booths[3].getPatient() == null && booths[4].getPatient() != null){
				booths[3].setPatient(patient);
			} else if ( booths[3].getPatient() != null && booths[4].getPatient() == null ){
				booths[4].setPatient(patient);
			} else if (booths[3].getPatient() == null && booths[4].getPatient() == null) {
				booths[3].setPatient(patient);
			} else {
				System.out.println("Vaccination booths are filled Sinopharm");
				return numberOfVaccines;
			}
		} else {
			if (booths[5].getPatient() == null && booths[6].getPatient() != null){
				booths[5].setPatient(patient);
			} else if ( booths[5].getPatient() != null && booths[6].getPatient() == null ){
				booths[6].setPatient(patient);
			} else if (booths[5].getPatient() == null && booths[6].getPatient() == null) {
				booths[5].setPatient(patient);
			} else {
				System.out.println("Vaccination booths are filled Pfizer");
				return numberOfVaccines;
			}
		}
		
		this.numberOfVaccines--;

		return this.numberOfVaccines;
	}
	
	// remove patient from booth
	public void removePatientFromBooth(int boothNo){
		if (booths[boothNo].getPatient() == null) {
			System.out.println("Already booth is empty");
		} else {
			booths[boothNo].setPatient(null);
			System.out.println("Patient has been removed");
		}
	}
	
	// sort patient names
	public String bubbleSort(boolean asc){
		
		Booth[] copyArray = this.booths.clone();
		
		for (int i = 1; i < this.booths.length; i++) {
			for (int j = 1; j < (this.booths.length - (i+1)); j++) {
				if (asc){
					if (this.booths[j].compareTo(this.booths[j+1]) > 0){
						Booth temp = this.booths[j];
						this.booths[j] = this.booths[j+1];
						this.booths[j+1] = temp;
					}
				}else{
					if (this.booths[j].compareTo(this.booths[j+1] ) < 0){
						Booth temp = this.booths[j];
						this.booths[j] = this.booths[j+1];
						this.booths[j+1] = temp;
					}
				}
			}
		}
		
		String data = "";
		for (int i=1; i<7; i++){
			if (this.booths[i].getPatient() != null) {
				data+="\n"+this.booths[i] + " is in booth " + Arrays.asList(copyArray).indexOf(this.booths[i]);
			}
		}
		return data;
	}
	
	// save data
	public void saveData(VaccineCenter vaccineCenter) {
		File file = new File("ObjectDataSerialisation.txt");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(vaccineCenter);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\nSaved Successfully.");
	}
	
	// load data
	public VaccineCenter loadData() {
		File file = new File("ObjectDataSerialisation.txt");
		FileInputStream fis= null;
		ObjectInputStream ois = null;
		
		VaccineCenter vaccineCenter = null;
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			
			while(true){
				vaccineCenter= (VaccineCenter) ois.readObject();
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
		
		this.numberOfVaccines = vaccineCenter.getNumberOfVaccines();
		this.booths = vaccineCenter.getBooths();
		
		System.out.println("\nSaved Successfully.");
		
		return vaccineCenter;
	}
	
	// add vaccines to the stock
	public int addVaccines(int numberOfVaccines) {
		this.numberOfVaccines += numberOfVaccines;
		return this.numberOfVaccines;
	}
	
	
	@Override
	public String toString() {
		return "VaccineCenter{" +
			"numberOfVaccines=" + numberOfVaccines +
			", booths=" + Arrays.toString(booths) +
			'}';
	}
}
