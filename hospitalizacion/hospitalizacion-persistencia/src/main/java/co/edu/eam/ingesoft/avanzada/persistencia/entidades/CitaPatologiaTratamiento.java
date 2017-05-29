package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CITA_PATOLOGIA_TRATAMIENTO")
public class CitaPatologiaTratamiento implements Serializable{
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumns({ @JoinColumn(name = "PATO_TRATA_PATO_ID", referencedColumnName = "PATOLOGIA_ID"),
		@JoinColumn(name = "PATO_TRATA_TRATA_ID", referencedColumnName = "TRATAMIENTO_ID") })
	@ManyToOne
	private PatologiaTratamiento patologiaTratamiento;
	
	@JoinColumn(name="CITA_ID")
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
