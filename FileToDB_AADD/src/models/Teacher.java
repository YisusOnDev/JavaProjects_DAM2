package models;

import java.util.ArrayList;

import javax.security.auth.Subject;

public class Teacher {

	private String nif;
	private String name;
	private String specialty;
	private String phoneNumber;
	private ArrayList<Subject> subjectsList;

	public Teacher(String nif, String name, String specialty, String phoneNumber) {
		super();
		this.nif = nif;
		this.name = name;
		this.specialty = specialty;
		this.phoneNumber = phoneNumber;
	}
	
	public Teacher(String nif, String name, String specialty, String phoneNumber, ArrayList<Subject> subjectsList) {
		super();
		this.nif = nif;
		this.name = name;
		this.specialty = specialty;
		this.phoneNumber = phoneNumber;
		this.subjectsList = subjectsList;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ArrayList<Subject> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(ArrayList<Subject> subjectsList) {
		this.subjectsList = subjectsList;
	}

	
}
