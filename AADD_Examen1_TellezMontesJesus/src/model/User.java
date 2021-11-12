package model;

public class User {
	private final String Name;
	private final String Lastname;
	private final String Username;

	public User(String name, String lastName) {
		this.Name = name;
		this.Lastname = lastName;
		this.Username = GetUsernameFromFullName(this.Name, this.Lastname);
	}

	/**
	 * Método que genera el Username concatenando la cadena "2DAM" con el primer
	 * caracter de su nombre y de su apellido.
	 * 
	 * @param name     el nombre del User
	 * @param lastName el apellido del User
	 * @return el Username generado
	 */
	public String GetUsernameFromFullName(String name, String lastName) {
		return "2DAM" + name.charAt(0) + lastName.charAt(0);
	}

	/**
	 * @return name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @return lastname
	 */
	public String getLastname() {
		return Lastname;
	}

	/**
	 * @return username
	 */
	public String getUsername() {
		return Username;
	}
}