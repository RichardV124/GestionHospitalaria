package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="T_MUNICIPIO")
@NamedQueries({ 
	@NamedQuery(name = Municipio.CONSULTA_LISTAR_MUNICIPIOS, query = "SELECT m FROM Municipio m") 
	})
public class Municipio implements Serializable{

	public static final String CONSULTA_LISTAR_MUNICIPIOS = "Municipio.ListarMunicipios";
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="descripcion", nullable=false, length=50)
	private String descripcion;
	
	@JoinColumn(name="departamento")
	@ManyToOne(cascade = {})
	private Departamento departamento;

	public Municipio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Municipio(int id, String descripcion, Departamento departamento) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.departamento = departamento;
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

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
}
