package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
	@ManyToOne(cascade = {})
	@JoinColumns({ @JoinColumn(name = "HORARIO_MEDICO_HORARIO_ID", referencedColumnName = "ced_medico"),
		@JoinColumn(name = "HORARIO_MEDICO_MEDICO_CEDULA", referencedColumnName = "id_horario") })
	private HorarioMedico horarioMedico;
	
	
	@Column(name="estado")
	private String estado;

	public PedirCita() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
