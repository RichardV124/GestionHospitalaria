package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;



@Table(name="T_HORARIO_MEDICO")
public class HorarioMedico implements Serializable{

	private String medico;
	
	private int horario;
}
