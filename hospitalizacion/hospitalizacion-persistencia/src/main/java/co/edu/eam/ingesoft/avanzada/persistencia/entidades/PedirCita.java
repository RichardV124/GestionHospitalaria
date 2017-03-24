package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(PedirCitaPK.class)
@Table(name="T_PEDIR_CITA")
public class PedirCita implements Serializable{

	@Id
	@JoinColumn(name="cedula_paciente")
	@ManyToOne(cascade={})
	private Paciente paciente;
	
	@Id
	@JoinColumn(name="horario_id")
	@ManyToOne(cascade = {})
	private HorarioMedico horarioMedicoHor;
	
	@Id
	@JoinColumn(name="medico_ced")
	@ManyToOne(cascade = {})
	private HorarioMedico horarioMedicoMed;
	
	@Column(name="estado")
	private String estado;

	public PedirCita() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PedirCita(Paciente paciente, HorarioMedico horarioMedicoHor, HorarioMedico horarioMedicoMed, String estado) {
		super();
		this.paciente = paciente;
		this.horarioMedicoHor = horarioMedicoHor;
		this.horarioMedicoMed = horarioMedicoMed;
		this.estado = estado;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public HorarioMedico getHorarioMedicoHor() {
		return horarioMedicoHor;
	}

	public void setHorarioMedicoHor(HorarioMedico horarioMedicoHor) {
		this.horarioMedicoHor = horarioMedicoHor;
	}

	public HorarioMedico getHorarioMedicoMed() {
		return horarioMedicoMed;
	}

	public void setHorarioMedicoMed(HorarioMedico horarioMedicoMed) {
		this.horarioMedicoMed = horarioMedicoMed;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
