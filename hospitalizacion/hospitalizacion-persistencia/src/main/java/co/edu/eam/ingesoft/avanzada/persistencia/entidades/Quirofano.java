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
@Table(name="QUIROFANO")
@NamedQueries({ 
	@NamedQuery(name = Quirofano.CONSULTA_LISTAR_QUIROFANOS, query = "SELECT q FROM Quirofano q") 
	})
public class Quirofano implements Serializable{

	public static final String CONSULTA_LISTAR_QUIROFANOS = "Quirofano.ListarQuirofanos";
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="NOMBRE",nullable=false,length=50)
	private String nombre;
	
	@Column(name="PISO",nullable=false,length=50)
	private String piso;
	
	@Column(name="DESCRIPCION",nullable=false,length=50)
	private String descripcion;
	
	@Column(name="DISPONIBLE",nullable=false)
	private boolean disponible;

	public Quirofano() {
		super();
		disponible=false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}
	
	
	
}
