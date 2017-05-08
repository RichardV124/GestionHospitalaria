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
@Table(name="T_TIPO_CIRUGIA")
@NamedQueries({ 
	@NamedQuery(name = TipoCirugia.CONSULTA_LISTAR_TIPO_CIRUGIA, query = "SELECT tc FROM TipoCirugia tc") 
	})
public class TipoCirugia implements Serializable{

	public static final String CONSULTA_LISTAR_TIPO_CIRUGIA = "TipoCirugia.ListarTipoCirugia";
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="descripcion",nullable=false,length=50)
	private String descripcion;

	public TipoCirugia() {
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
	
	
}
