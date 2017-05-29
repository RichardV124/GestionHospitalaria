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
@Table(name="CITA")
public class Cita implements Serializable{
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="ATENDIDO", nullable=false)
	private boolean atendido;
	
	@Column(name="ANOTACIONES")
	private String anotaciones;
	
	@JoinColumn(name="CARACTER_ID", nullable=false)
	@ManyToOne(cascade={})
	private CaracterCita caracter;
	
	
	
	@JoinColumn(name="CITA_ID",nullable=true)
	@ManyToOne
	private Cita cita;
	
	@JoinColumn(name = "PACIENTE_CEDULA")
	@ManyToOne(cascade = {})
	private Paciente paciente;
	
	@ManyToOne(cascade = {})
	@JoinColumns({
		    @JoinColumn(name = "HORARIO_MEDICO_HORARIO_ID", referencedColumnName = "HORARIO_ID"),
			@JoinColumn(name = "HORARIO_MEDICO_MEDICO_CEDULA", referencedColumnName = "MEDICO_CEDULA")
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
