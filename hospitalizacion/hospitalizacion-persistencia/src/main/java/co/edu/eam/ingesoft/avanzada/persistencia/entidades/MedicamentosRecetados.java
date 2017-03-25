package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name="T_MEDICAMENTOS_RECETADOS")
@IdClass(MedicamentosRecetadosPK.class)
@Entity
public class MedicamentosRecetados implements Serializable{

	@Id
	@ManyToOne
	@JoinColumn(name="MEDICAMENTO_ID")
	private Medicamento medicamento;
	
	@Id
	@ManyToOne
	@JoinColumn(name="CITA_ID")
	private Cita cita;
	
	@Column(name="CANTIDAD_RECETADA")
	private int cantidadRecetada;
	
	@Column(name="DOSIS")
	private String dosis;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="FECHA_RECLAMO")
	@Temporal(TemporalType.DATE)
	private Date fechaReclamo;
	
	@Column(name="RECLAMADO")
	private boolean reclamado;
	
	@JoinColumn(name="MEDI_ENTREGADOS_ID")
	@ManyToOne
	private MedicamentosEntregados medicEntregados;

	public MedicamentosRecetados() {
		super();
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public int getCantidadRecetada() {
		return cantidadRecetada;
	}

	public void setCantidadRecetada(int cantidadRecetada) {
		this.cantidadRecetada = cantidadRecetada;
	}

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaReclamo() {
		return fechaReclamo;
	}

	public void setFechaReclamo(Date fechaReclamo) {
		this.fechaReclamo = fechaReclamo;
	}

	public boolean isReclamado() {
		return reclamado;
	}

	public void setReclamado(boolean reclamado) {
		this.reclamado = reclamado;
	}

	public MedicamentosEntregados getMedicEntregados() {
		return medicEntregados;
	}

	public void setMedicEntregados(MedicamentosEntregados medicEntregados) {
		this.medicEntregados = medicEntregados;
	}
	
	
}
