package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(PatologiaSintomaPK.class)
@Table(name="T_PATOLOGIA_SINTOMA")
public class PatologiaSintoma implements Serializable{

	@Id
	@JoinColumn(name="ID_PATOLOGIA")
	@ManyToOne
	private Patologia patologia;
	
	@Id
	@JoinColumn(name="ID_SINTOMA")
	@ManyToOne
	private Sintoma sintoma;

	public PatologiaSintoma() {
		super();
	}

	public Patologia getPatologia() {
		return patologia;
	}

	public void setPatologia(Patologia patologia) {
		this.patologia = patologia;
	}

	public Sintoma getSintoma() {
		return sintoma;
	}

	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}
	
	
	
}
