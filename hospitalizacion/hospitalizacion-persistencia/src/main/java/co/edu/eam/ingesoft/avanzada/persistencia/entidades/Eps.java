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
@Table(name="EPS")
@NamedQueries({ 
	@NamedQuery(name = Eps.CONSULTA_LISTAR_EPS, query = "SELECT e FROM Eps e") 
	})
public class Eps implements Serializable{

	public static final String CONSULTA_LISTAR_EPS = "Eps.ListarEps";
	
	/**
	 * id autoincrementable de la eps
	 */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="DESCRIPCION", nullable=false, length=50)
	private String descripcion;

	public Eps() {
		super();
	}

	public Eps(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
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
	
	
	
}
