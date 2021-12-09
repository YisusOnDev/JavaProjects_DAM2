package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "SUPERUSUARIOS")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	
	@Column(name="NOMBRE")
	private String Name;
	

	@Column(name="APELLIDOS")
	private String Lastname;
	
	@Column(name="USER")
	private String Username;
	
	public User() {
		super();
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
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return Lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return Username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		Username = username;
	}
	
	
}