package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(CitaCirugiaPK.class)
@Table(name = "CITA_CIRUGIA")
public class CitaCirugia implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name="CITA_ID")
	private Cita cita;

	@Id
	@ManyToOne
	@JoinColumn(name="CIRUGIA_ID")
	private Cirugia cirugia;

	@Column(name = "FECHA")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@JoinColumn(name = "QUIROFANO_ID")
	@ManyToOne
	private Quirofano quirofano;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "HORARIO_MEDICO_HORARIO_ID", referencedColumnName = "MEDICO_CEDULA"),
			@JoinColumn(name = "HORARIO_MEDICO_MEDICO_CEDULA", referencedColumnName = "HORARIO_ID") })
	private HorarioMedico horarioMedico;

	public CitaCirugia() {
		super();
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public Cirugia getCirugia() {
		return cirugia;
	}

	public void setCirugia(Cirugia cirugia) {
		this.cirugia = cirugia;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Quirofano getQuirofano() {
		return quirofano;
	}

	public void setQuirofano(Quirofano quirofano) {
		this.quirofano = quirofano;
	}

	public HorarioMedico getHorarioMedico() {
		return horarioMedico;
	}

	public void setHorarioMedico(HorarioMedico horarioMedico) {
		this.horarioMedico = horarioMedico;
	}

	
	
	
}
