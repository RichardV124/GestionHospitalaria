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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_CITA")
public class Cita implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="atendido", nullable=false)
	private boolean atendido;
	
	@Column(name="anotaciones")
	private String anotaciones;
	
	@JoinColumn(name="caracter_cita", nullable=false)
	@ManyToOne(cascade={})
	private CaracterCita caracter;
	
	
	
	@JoinColumn(name="cita",nullable=true)
	@ManyToOne
	private Cita cita;
	
	@JoinColumn(name = "cedula_paciente")
	@ManyToOne(cascade = {})
	private Paciente paciente;
	
	@ManyToOne(cascade = {})
	@JoinColumns({
		    @JoinColumn(name = "HORARIO_MEDICO_HORARIO_ID", referencedColumnName = "id_horario"),
			@JoinColumn(name = "HORARIO_MEDICO_MEDICO_CEDULA", referencedColumnName = "ced_medico")
	})
	private HorarioMedico horarioMedico;

	public Cita() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cita(int id, boolean atendido, String anotaciones, CaracterCita caracter, Cita cita) {
		super();
		this.id = id;
		this.atendido = atendido;
		this.anotaciones = anotaciones;
		this.caracter = caracter;
		this.cita = cita;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAtendido() {
		return atendido;
	}

	public void setAtendido(boolean atendido) {
		this.atendido = atendido;
	}

	public String getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(String anotaciones) {
		this.anotaciones = anotaciones;
	}

	public CaracterCita getCaracter() {
		return caracter;
	}

	public void setCaracter(CaracterCita caracter) {
		this.caracter = caracter;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

}
