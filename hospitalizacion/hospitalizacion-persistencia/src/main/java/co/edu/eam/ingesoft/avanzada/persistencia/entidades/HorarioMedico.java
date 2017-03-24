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
@IdClass(HorarioMedicoPK.class)
@Table(name="T_HORARIO_MEDICO")
public class HorarioMedico implements Serializable{
	
	@Id
	@JoinColumn(name="ced_medico")
	@ManyToOne(cascade = {})
	private Medico medico;
	
	@Id
	@JoinColumn(name="id_horario")
	@ManyToOne(cascade = {})
	private Horario horario;
	
	@Column(name="disponible")
	private boolean disponible;

	public HorarioMedico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HorarioMedico(Medico medico, Horario horario, boolean disponible) {
		super();
		this.medico = medico;
		this.horario = horario;
		this.disponible = disponible;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	
}
