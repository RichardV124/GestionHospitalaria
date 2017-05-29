package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="MEDICAMENTO")
@NamedQueries({ 
	@NamedQuery(name = Medicamento.CONSULTA_LISTAR_MEDICAMENTOS, query = "SELECT m FROM Medicamento m") 
	})
public class Medicamento implements Serializable{

	public static final String CONSULTA_LISTAR_MEDICAMENTOS = "Medicamento.ListarMedicamentos";
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="DESCRIPCION", nullable=false, length=50)
	private String descripcion;
	
	@Column(name="FECHA_VENCIMIENTO", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;
	
	@JoinColumn(name="TIPO_MEDICAMENTO_ID")
	@ManyToOne(cascade={})
	private TipoMedicamento tipoMedicamento;
	

	public Medicamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medicamento(int id, String descripcion, Date fechaVencimiento, TipoMedicamento tipoMedicamento) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fechaVencimiento = fechaVencimiento;
		this.tipoMedicamento = tipoMedicamento;
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

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public TipoMedicamento getTipoMedicamento() {
		return tipoMedicamento;
	}

	public void setTipoMedicamento(TipoMedicamento tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}
	
	
}
