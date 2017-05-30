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
@Table(name="TIPO_MEDICAMENTO")
@NamedQueries({ 
	@NamedQuery(name = TipoMedicamento.CONSULTA_LISTAR_TIPO_MEDICAMENTO, query = "SELECT tm FROM TipoMedicamento tm") 
	})
public class TipoMedicamento implements Serializable{
	
	public static final String CONSULTA_LISTAR_TIPO_MEDICAMENTO = "TipoMedicamento.ListarTipoMedicamento";
	
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="DESCRIPCION", nullable=false, length=50)
	private String descripcion;

	public TipoMedicamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoMedicamento(int id, String descripcion) {
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
