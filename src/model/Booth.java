package model;

import java.io.Serializable;

public class Booth implements Serializable, Comparable<Booth>{
	private int boothNo;
	private Patient patient;
	
	public Booth(int boothNo, Patient patient) {
		this.boothNo = boothNo;
		this.patient = patient;
	}
	
	public int getBoothNo() {
		return boothNo;
	}
	
	public void setBoothNo(int boothNo) {
		this.boothNo = boothNo;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	@Override
	public String toString() {
		if (patient != null) return patient.getFirstName() + " " + patient.getSurName();
		return "empty";
	}
	
	@Override
	public int compareTo(Booth o) {
		if (this.getPatient() != null && o.getPatient() != null) {
			return this.getPatient().getFirstName().compareTo(o.patient.getFirstName());
		} else {
			return 0;
		}
	}
}
