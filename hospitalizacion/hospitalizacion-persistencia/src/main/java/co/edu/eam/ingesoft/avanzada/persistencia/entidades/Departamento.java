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
@Table(name="DEPARTAMENTO")
@NamedQueries({ 
	@NamedQuery(name = Departamento.CONSULTA_LISTAR_DEPARTAMENTOS, query = "SELECT d FROM Departamento d") 
	})
public class Departamento implements Serializable{

	public static final String CONSULTA_LISTAR_DEPARTAMENTOS = "Departamento.ListarDepartamentos";
	
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="DESCRIPCION", nullable=false, length=50)
	private String descripcion;

	public Departamento() {
		super();
		// TODO Auto-generated constructor stub
	}	
	

	public Departamento(int id, String descripcion) {
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
