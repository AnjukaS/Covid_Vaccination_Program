package model;

import java.io.Serializable;

public class Patient implements Serializable, Comparable<Patient> {
	private String firstName;
	private String surName;
	private int age;
	private String city;
	private String nic;
	private String vaccinationRequested;
	private int vaccinationId;
	
	public Patient() {
	}
	
	public Patient(String firstName, String surName, int age, String city, String nic, int vaccinationRequested) {
		this.firstName = firstName;
		this.surName = surName;
		this.age = age;
		this.city = city;
		this.nic = nic;
		this.vaccinationRequested = vaccinationRequested == 1 ? "AstraZeneca" : vaccinationRequested == 2 ? "Sinopharm" : "Pfizer";
		this.vaccinationId = vaccinationRequested;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getSurName() {
		return surName;
	}
	
	public void setSurName(String surName) {
		this.surName = surName;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getNic() {
		return nic;
	}
	
	public void setNic(String nic) {
		this.nic = nic;
	}
	
	public int getVaccinationId() {
		return vaccinationId;
	}
	
	public String getVaccinationRequested() {
		return vaccinationRequested;
	}
	
	public void setVaccinationRequested(int vaccinationRequested) {
		this.vaccinationRequested = vaccinationRequested == 1 ? "AstraZeneca" : vaccinationRequested == 2 ? "Sinopharm" : "Pfizer";
	}
	
	@Override
	public int compareTo(Patient o) {
		return this.firstName.compareTo(o.firstName);
	}
	
	@Override
	public String toString() {
		return "Patient{" +
			"First Name='" + firstName + '\'' +
			", Sur Name='" + surName + '\'' +
			", Age=" + age +
			", City='" + city + '\'' +
			", NIC='" + nic + '\'' +
			", Vaccination Requested='" + vaccinationRequested + '\'' +
			'}';
	}
}
