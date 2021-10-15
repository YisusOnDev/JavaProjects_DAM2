package schooler;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Model class of alumno.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class AlumnoSchooler implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** nummatricula. */
	private Integer nummatricula;

	/** nombre. */
	private String nombre;

	/** fechanacimiento. */
	private Date fechanacimiento;

	/** telefono. */
	private String telefono;

	/** The set of recibe. */
	private Set<RecibeSchooler> recibeSet;

	/**
	 * Constructor.
	 */
	public AlumnoSchooler() {
		this.recibeSet = new HashSet<RecibeSchooler>();
	}

	/**
	 * Set the nummatricula.
	 * 
	 * @param nummatricula
	 *            nummatricula
	 */
	public void setNummatricula(Integer nummatricula) {
		this.nummatricula = nummatricula;
	}

	/**
	 * Get the nummatricula.
	 * 
	 * @return nummatricula
	 */
	public Integer getNummatricula() {
		return this.nummatricula;
	}

	/**
	 * Set the nombre.
	 * 
	 * @param nombre
	 *            nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Get the nombre.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Set the fechanacimiento.
	 * 
	 * @param fechanacimiento
	 *            fechanacimiento
	 */
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	/**
	 * Get the fechanacimiento.
	 * 
	 * @return fechanacimiento
	 */
	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	/**
	 * Set the telefono.
	 * 
	 * @param telefono
	 *            telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Get the telefono.
	 * 
	 * @return telefono
	 */
	public String getTelefono() {
		return this.telefono;
	}

	/**
	 * Set the set of the recibe.
	 * 
	 * @param recibeSet
	 *            The set of recibe
	 */
	public void setRecibeSet(Set<RecibeSchooler> recibeSet) {
		this.recibeSet = recibeSet;
	}

	/**
	 * Add the recibe.
	 * 
	 * @param recibe
	 *            recibe
	 */
	public void addRecibe(RecibeSchooler recibe) {
		this.recibeSet.add(recibe);
	}

	/**
	 * Get the set of the recibe.
	 * 
	 * @return The set of recibe
	 */
	public Set<RecibeSchooler> getRecibeSet() {
		return this.recibeSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nummatricula == null) ? 0 : nummatricula.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AlumnoSchooler other = (AlumnoSchooler) obj;
		if (nummatricula == null) {
			if (other.nummatricula != null) {
				return false;
			}
		} else if (!nummatricula.equals(other.nummatricula)) {
			return false;
		}
		return true;
	}

}