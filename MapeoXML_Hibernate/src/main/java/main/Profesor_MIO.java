package main;

import java.io.Serializable;

public class Profesor_MIO implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
    private String nombre;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	private String apellido;
    private String apellido2;

    public Profesor_MIO(){
    }

    public Profesor_MIO(int id, String nombre) {
        this.id = id;
         this.nombre=nombre;
     }
 }