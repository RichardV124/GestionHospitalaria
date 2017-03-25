package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_CITA_PATOLOGIA_TRATAMIENTO")
public class CitaPatologiaTratamiento implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name="ID_PATOLOGIA_TRATAMIENTO")
	@ManyToOne
	private PatologiaTratamiento patologiaTratamiento;
	
	@JoinColumn(name="ID_CITA")
	@ManyToOne
	private Cita cita;

	public CitaPatologiaTratamiento() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PatologiaTratamiento getPatologiaTratamiento() {
		return patologiaTratamiento;
	}

	public void setPatologiaTratamiento(PatologiaTratamiento patologiaTratamiento) {
		this.patologiaTratamiento = patologiaTratamiento;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	
	
}
