package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CAMA")
@NamedQueries({ 
	@NamedQuery(name = Cama.CONSULTA_LISTAR_CAMAS, query = "SELECT c FROM Cama c") 
	})
public class Cama implements Serializable {

	public static final String CONSULTA_LISTAR_CAMAS = "Cama.ListarCamas";
	
	@Id
	@Column(name="ID")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="PISO",nullable=false,length=50)
	private String piso;
	
	@Column(name="HABITACION",nullable=false,length=50)
	private String habitacion;
	
	@Column(name="DISPONIBLE",nullable=false)
	private boolean disponible;
	
	@Column(name="DESCRIPCION",nullable=false,length=50)
	private String descripcion;
	
	public Cama() {
		super();
		disponible=false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}

	
	
}
