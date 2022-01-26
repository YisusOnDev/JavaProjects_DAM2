// default package
// Generated Jan 13, 2022, 11:14:57 PM by Hibernate Tools 5.5.7.Final

package main;

/**
 * Profesor generated by hbm2java
 */
public class Profesor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8127882756449025444L;
	@Override
	public String toString() {
		return "Profesor [id=" + id + ", ape1=" + ape1 + ", ape2=" + ape2 + ", nombre=" + nombre + ", tipoFuncionario="
				+ tipoFuncionario + "]";
	}

	private int id;
	private String ape1;
	private String ape2;
	private String nombre;
	private String tipoFuncionario;

	public Profesor() {
	}

	public Profesor(int id) {
		this.id = id;
	}

	public Profesor(int id, String ape1, String ape2, String nombre, String tipoFuncionario) {
		this.id = id;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.nombre = nombre;
		this.tipoFuncionario = tipoFuncionario;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApe1() {
		return this.ape1;
	}

	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}

	public String getApe2() {
		return this.ape2;
	}

	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoFuncionario() {
		return this.tipoFuncionario;
	}

	public void setTipoFuncionario(String tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

}