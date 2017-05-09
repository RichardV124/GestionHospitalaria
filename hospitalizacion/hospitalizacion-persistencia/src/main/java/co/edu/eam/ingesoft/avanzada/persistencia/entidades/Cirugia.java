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
@Table(name="T_CIRUGIA")
@NamedQueries({ 
	@NamedQuery(name = Cirugia.CONSULTA_LISTAR_CIRUGIAS, query = "SELECT c FROM Cirugia c") 
	})
public class Cirugia implements Serializable{

	public static final String CONSULTA_LISTAR_CIRUGIAS = "Cirugia.ListarCirugias";
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="descripcion",nullable=false,length=50)
	private String descripcion;

	@JoinColumn(name="id_tipocirugia")
	@ManyToOne
	private TipoCirugia tipoCirugia;
	
	@Column(name="tiempo_estimado")
	private int tiempoEstimado;

	public Cirugia() {
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

	public int getTiempoEstimado() {
		return tiempoEstimado;
	}

	public void setTiempoEstimado(int tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}
	
	
	
}
