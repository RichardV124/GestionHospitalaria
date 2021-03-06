package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(PatologiaTratamientoPK.class)
@Table(name="PATOLOGIA_TRATAMIENTO")
public class PatologiaTratamiento implements Serializable{

	@Id
	@JoinColumn(name="PATOLOGIA_ID")
	@ManyToOne
	private Patologia patologia;
	
	@Id
	@JoinColumn(name="TRATAMIENTO_ID")
	@ManyToOne
	private Tratamiento tratamiento;

	public PatologiaTratamiento() {
		super();
	}

	public Patologia getPatologia() {
		return patologia;
	}

	public void setPatologia(Patologia patologia) {
		this.patologia = patologia;
	}

	public Tratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}
	
	
	
	
}
