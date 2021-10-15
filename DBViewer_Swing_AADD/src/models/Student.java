package models;

public class Student {

	private Integer registrationNumber;
	private String name;
	private String birthdate;
	private String phoneNumber;

	/**
	 * Constructor.
	 */
	public Student(int regNumber, String name, String birth, String phoneNumber) {
		this.registrationNumber = regNumber;
		this.name = name;
		this.birthdate = birth;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the registrationNumber
	 */
	public Integer getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * @param registrationNumber the registrationNumber to set
	 */
	public void setRegistrationNumber(Integer registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the birthdate
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Student [registrationNumber=" + registrationNumber + ", name=" + name + ", birthdate=" + birthdate
				+ ", phoneNumber=" + phoneNumber + "]";
	}

}
