package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="T_HORARIO")
@NamedQueries({ 
	@NamedQuery(name = Horario.CONSULTA_LISTAR_HORARIOS, query = "SELECT h FROM Horario h") 
	})
public class Horario implements Serializable{
	
	public static final String CONSULTA_LISTAR_HORARIOS = "Horario.ListarHorarios";
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="hora_inicial")
	private int horaInicial;
	
	@Column(name="horaFinal")
	private int horaFinal;

	public Horario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Horario(int id, Date fecha, int horaInicial, int horaFinal) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(int horaInicial) {
		this.horaInicial = horaInicial;
	}

	public int getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(int horaFinal) {
		this.horaFinal = horaFinal;
	}
	
	
}
