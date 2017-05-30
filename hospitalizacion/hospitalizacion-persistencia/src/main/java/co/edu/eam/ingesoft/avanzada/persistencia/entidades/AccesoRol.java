package co.edu.eam.ingesoft.avanzada.persistencia.entidades;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ACCESO_ROL")
@IdClass(AccesoRolPK.class)
public class AccesoRol implements Serializable{

	/*
	 * Atributos
	 */

	@Id
	@ManyToOne(cascade={})
	@JoinColumn(name = "ROL_ID", nullable = false)
	private Rol rol;
	
	@Id
	@ManyToOne(cascade={})
	@JoinColumn(name = "ACCESO_ID", nullable = false)
	private Acceso acceso;

	/*
	 * Constructor
	 */
	
	
	public AccesoRol(Rol rol, Acceso acceso) {
		super();
		this.rol = rol;
		this.acceso = acceso;
	}

	public AccesoRol() {

	}

	/*
	 * Getters and setters
	 */
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Acceso getAcceso() {
		return acceso;
	}

	public void setAcceso(Acceso acceso) {
		this.acceso = acceso;
	}

	@Override
	public String toString() {
		return "AccesoRol [rol=" + rol + ", acceso=" + acceso + "]";
	}
	
	
	
}