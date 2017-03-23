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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_MEDICAMENTO")
public class Medicamento implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="descripcion", nullable=false, length=50)
	private String descripcion;
	
	@Column(name="fecha_vencimiento", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;
	
	@JoinColumn(name="tipo_medicamento")
	@ManyToOne(cascade={})
	private TipoMedicamento tipoMedicamento;
	
	@JoinColumn(name="farmacia")
	@ManyToOne(cascade={})
	private Farmacia farmacia;

	public Medicamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medicamento(int id, String descripcion, Date fechaVencimiento, TipoMedicamento tipoMedicamento,
			Farmacia farmacia) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fechaVencimiento = fechaVencimiento;
		this.tipoMedicamento = tipoMedicamento;
		this.farmacia = farmacia;
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

	public Farmacia getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
	
	
}
