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
@Table(name="TIPO_MEDICO")
@NamedQueries({ 
	@NamedQuery(name = TipoMedico.CONSULTA_LISTAR_TIPO_MEDICO, query = "SELECT tm FROM TipoMedico tm") 
	})
public class TipoMedico implements Serializable{

	public static final String CONSULTA_LISTAR_TIPO_MEDICO = "TipoMedico.ListarTipoMedico";
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="descripcion",nullable=false,length=50)
	private String descripcion;

	public TipoMedico() {
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
