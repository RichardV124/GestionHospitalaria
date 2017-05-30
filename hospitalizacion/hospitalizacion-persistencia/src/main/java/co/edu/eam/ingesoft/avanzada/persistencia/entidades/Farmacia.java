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
@Table(name="FARMACIA")
@NamedQueries({ 
	@NamedQuery(name = Farmacia.CONSULTA_LISTAR_FARMACIAS, query = "SELECT f FROM Farmacia f") 
	})
public class Farmacia implements Serializable{
	
	public static final String CONSULTA_LISTAR_FARMACIAS = "Farmacia.ListarFarmacias";
	
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="DESCRIPCION",nullable=false,length=50)
	private String descripcion;
	
	@Column(name="DIRECCION",nullable=false,length=50)
	private String direccion;

	public Farmacia() {
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
}
