package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@IdClass(HorarioMedicoPK.class)
@Table(name="T_HORARIO_MEDICO")
public class HorarioMedico implements Serializable{
	
	@Id
	private Medico medico;
	
	@Id
	private Horario horario;
}
