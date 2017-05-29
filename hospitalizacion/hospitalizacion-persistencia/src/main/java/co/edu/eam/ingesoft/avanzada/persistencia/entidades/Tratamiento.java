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
@Table(name="T_TRATAMIENTO")
@NamedQueries({ 
	@NamedQuery(name = Tratamiento.CONSULTA_LISTAR_TRATAMIENTOS, query = "SELECT t FROM Tratamiento t") 
	})
public class Tratamiento implements Serializable{

	public static final String CONSULTA_LISTAR_TRATAMIENTOS = "Tratamiento.ListarTratamientos";
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nombre",nullable=false,length=50)
	private String nombre;
	
	@Column(name="descripcion",nullable=false,length=200)
	private String descripcion;

	public Tratamiento() {
		super();
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
