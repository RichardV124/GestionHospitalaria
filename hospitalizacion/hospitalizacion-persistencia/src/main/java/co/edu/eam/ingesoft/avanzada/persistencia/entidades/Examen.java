package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_EXAMEN")
public class Examen implements Serializable{

	@Id
	@Column(name="nombre",nullable=false,length=50)
	private String nombre;
	
	@Column(name="descripcion",nullable=false,length=50)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="id_tipoexamen")
	private TipoExamen tipoExamen;

	public Examen() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoExamen getTipoExamen() {
		return tipoExamen;
	}

	public void setTipoExamen(TipoExamen tipoExamen) {
		this.tipoExamen = tipoExamen;
	}
	
	
	
}
