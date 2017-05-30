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
@Table(name="PATOLOGIA")
@NamedQueries({ 
	@NamedQuery(name = Patologia.CONSULTA_LISTAR_PATOLOGIAS, query = "SELECT p FROM Patologia p") 
	})
public class Patologia implements Serializable{


	public static final String CONSULTA_LISTAR_PATOLOGIAS = "Patologia.ListarPatologias";
	
	
	@Id
	@Column(name="ID")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="DESCRIPCION", nullable=false, length=50)
	private String descripcion;
	
	@Column(name="TIPO", nullable=false, length=50)
	private String tipo;
	
	@Column(name="SINTOMAS", nullable=false, length=200)
	private String sintomas;
	

	public Patologia() {
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}
	
	
	
	
}
